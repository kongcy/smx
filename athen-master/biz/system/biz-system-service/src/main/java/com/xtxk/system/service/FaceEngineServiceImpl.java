package com.xtxk.system.service;

import cn.hutool.core.codec.Base64;
import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.google.common.collect.Lists;
import com.xtxk.core.exception.Assert;
import com.xtxk.core.exception.ServiceException;
import com.xtxk.core.util.A;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.system.api.DTO.FileDTO;
import com.xtxk.system.api.DTO.SysUserFaceDTO;
import com.xtxk.system.api.model.SysUser;
import com.xtxk.system.api.model.enums.ErrorCodeEnum;
import com.xtxk.system.api.service.FaceEngineService;
import com.xtxk.system.factory.FaceEngineFactory;
import com.xtxk.system.repository.SysUserFaceInfoMapper;
import com.xtxk.system.repository.SysUserMapper;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
/**
 * 人脸引擎服务
 * @author chenying
 * @date 2022/11/8 11:13 AM
 * @time 11:13 AM
 * @since 1.0.0
 **/
@RestController
public class FaceEngineServiceImpl implements FaceEngineService {
    @Value("${config.arcface-sdk.sdk-lib-path}")
    public String sdkLibPath;
    @Value("${config.arcface-sdk.app-id}")
    public String appId;
    @Value("${config.arcface-sdk.sdk-key}")
    public String sdkKey;
    private Integer passRate = 80;
    @Autowired
    private SysUserFaceInfoMapper userFaceInfoMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Value("${config.arcface-sdk.thread-pool-size}")
    public Integer threadPoolSize;
    private ExecutorService executorService;
    private GenericObjectPool<FaceEngine> faceEngineObjectPool;

    @PostConstruct
    public void init() {
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(threadPoolSize);
        poolConfig.setMaxTotal(threadPoolSize);
        poolConfig.setMinIdle(threadPoolSize);
        poolConfig.setLifo(false);
        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);
        faceEngineObjectPool = new GenericObjectPool(new FaceEngineFactory(sdkLibPath, appId, sdkKey, engineConfiguration), poolConfig);//底层库算法对象池
    }

    /**
     * 查找人脸
     *
     * @param file 文件
     */
    @Override
    public SysUser search(@RequestBody FileDTO file) {
        Assert.checkNull(file, ErrorCodeEnum.NO_FACE_DETECTED.getDescription());
        Assert.checkNull(file.getFile(), ErrorCodeEnum.NO_FACE_DETECTED.getDescription());
        String base64Str = U.base64Process(file.getFile());
        LogUtil.LOG.info("====================search====================="+base64Str);
        SysUser user = null;
        try {
            byte[] decode = Base64.decode(base64Str);
            ImageInfo imageInfo = ImageFactory.getRGBData(decode);
            //人脸特征获取
            byte[] bytes = extractFaceFeature(imageInfo);
            Assert.checkNull(bytes,ErrorCodeEnum.NO_FACE_DETECTED.getDescription());
            //人脸比对，获取比对结果
            List<SysUserFaceDTO> userFaceInfoList = compareFaceFeature(bytes);
            LogUtil.LOG.info("====================获取对比结果：userFaceInfoList："+A.toStr(userFaceInfoList)+"===============");
            if (A.isNotEmpty(userFaceInfoList)) {
                SysUserFaceDTO faceUserInfo = userFaceInfoList.get(0);
                user = userMapper.selectByPrimaryKey(faceUserInfo.getUserId());
                return user;
            }
        } catch (Exception e){
            LogUtil.LOG.error("===================人脸检测失败！=================");
            throw  new ServiceException("人脸检测失败!",e);
        }
        return null;
    }

    /**
     * 获取人脸特征
     *
     * @param file 图片字符串
     */
    @Override
    public byte[] getFaceFeature(@RequestBody FileDTO file) {
        try{
            byte[]  decode = Base64.decode(U.base64Process(file.getFile()));
            ImageInfo imageInfo = ImageFactory.getRGBData(decode);
            //人脸特征获取
            byte[] bytes = extractFaceFeature(imageInfo);
            return bytes;
        }catch (Exception e){
            LogUtil.LOG.error("======================提取人脸特征失败！===================="+e.getMessage());
        }
        return null;
    }

    /**
     * 人脸特征
     *
     * @param imageInfo
     * @return
     */
    @Override
    public byte[] extractFaceFeature(@RequestBody ImageInfo imageInfo) {
        FaceEngine faceEngine = null;
        try {
            //获取引擎对象
            faceEngine = faceEngineObjectPool.borrowObject();
            //人脸检测得到人脸列表
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
            //人脸检
            int i = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(),
                    imageInfo.getImageFormat(), faceInfoList);
            LogUtil.LOG.info("========================打印人脸检测【detectFaces】返回代码： "+i);
            if (A.isNotEmpty(faceInfoList)) {
                FaceFeature faceFeature = new FaceFeature();
                //提取人脸特征
                int result=faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(),
                        imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
                LogUtil.LOG.info("========================打印提取人脸特征【extractFaceFeature】返回代码： "+result);
                return faceFeature.getFeatureData();
            }
        } catch (Exception e) {
            LogUtil.LOG.error("获取人脸特征异常！", e);
        } finally {
            if (faceEngine != null) {
                //释放引擎对象
                faceEngineObjectPool.returnObject(faceEngine);
            }
        }
        return null;
    }

    /**
     * 人脸比对
     *
     * @param faceFeature 人脸特征流
     * @param
     * @return
     */
    @Override
    public List<SysUserFaceDTO> compareFaceFeature(byte[] faceFeature) throws InterruptedException, ExecutionException {
        List<SysUserFaceDTO> resultFaceInfoList = Lists.newLinkedList();//识别到的人脸列表
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature);
        List<SysUserFaceDTO> faceInfoList = userFaceInfoMapper.findFaceInfo();
        if(A.isEmpty(faceInfoList)){
            return null;
        }
        List<List<SysUserFaceDTO>> faceUserInfoPartList = Lists.partition(faceInfoList, 1000);//分成1000一组，多线程处理
        CompletionService<List<SysUserFaceDTO>> completionService = new ExecutorCompletionService(executorService);
        for (List<SysUserFaceDTO> part : faceUserInfoPartList) {
            completionService.submit(new CompareFaceTask(part, targetFaceFeature));
        }
        for (int i = 0; i < faceUserInfoPartList.size(); i++) {
            List<SysUserFaceDTO> faceUserInfoList = completionService.take().get();
            if (A.isNotEmpty(faceInfoList)) {
                resultFaceInfoList.addAll(faceUserInfoList);
            }
        }
        resultFaceInfoList.sort((h1, h2) -> h2.getSimilarValue().compareTo(h1.getSimilarValue()));//从大到小排序
        return resultFaceInfoList;
    }

    private int plusHundred(Float value) {
        BigDecimal target = new BigDecimal(value);
        BigDecimal hundred = new BigDecimal(100f);
        return target.multiply(hundred).intValue();

    }

    /**
     * 特征对比任务器
     *
     */
    private class CompareFaceTask implements Callable<List<SysUserFaceDTO>> {
        private List<SysUserFaceDTO> faceUserInfoList;
        private FaceFeature targetFaceFeature;
        public CompareFaceTask(List<SysUserFaceDTO> faceUserInfoList, FaceFeature targetFaceFeature) {
            this.faceUserInfoList = faceUserInfoList;
            this.targetFaceFeature = targetFaceFeature;
        }

        @Override
        public List<SysUserFaceDTO> call() throws Exception {
            FaceEngine faceEngine = null;
            List<SysUserFaceDTO> resultFaceInfoList = Lists.newLinkedList();//识别到的人脸列表
            try {
                faceEngine = faceEngineObjectPool.borrowObject();
                for (SysUserFaceDTO faceUserInfo : faceUserInfoList) {
                    FaceFeature sourceFaceFeature = new FaceFeature();
                    sourceFaceFeature.setFeatureData(faceUserInfo.getFaceFeature());
                    FaceSimilar faceSimilar = new FaceSimilar();
                    int result=faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
                    Integer similarValue = plusHundred(faceSimilar.getScore());//获取相似值
                    LogUtil.LOG.info("=======================比较相似值similarValue："+similarValue+"==========================");
                    if (similarValue > passRate) {//相似值大于配置预期，加入到识别到人脸的列表
                        SysUserFaceDTO info = new SysUserFaceDTO();
                        info.setUserId(faceUserInfo.getUserId());
                        info.setSimilarValue(similarValue);
                        resultFaceInfoList.add(info);
                    }
                }
            } catch (Exception e) {
                LogUtil.LOG.error("===================人脸对比失败！====================", e);
            } finally {
                if (faceEngine != null) {
                    faceEngineObjectPool.returnObject(faceEngine);
                }
            }
            return resultFaceInfoList;
        }
    }
}
