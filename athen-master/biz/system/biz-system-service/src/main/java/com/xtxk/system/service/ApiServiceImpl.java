package com.xtxk.system.service;

import com.xtxk.core.date.DateFormatType;
import com.xtxk.core.date.DateUtil;
import com.xtxk.core.encrypt.Encrypt;
import com.xtxk.core.exception.Assert;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.util.A;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.system.api.DTO.FileDTO;
import com.xtxk.system.api.DTO.SysUserFaceDTO;
import com.xtxk.system.api.DTO.UserInfo;
import com.xtxk.system.api.model.SysUser;
import com.xtxk.system.api.model.SysUserFaceInfoWithBLOBs;
import com.xtxk.system.api.model.enums.Source;
import com.xtxk.system.api.service.ApiService;
import com.xtxk.system.api.service.FaceEngineService;
import com.xtxk.system.repository.SysUserFaceInfoMapper;
import com.xtxk.system.repository.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

import static com.xtxk.core.Constant.ERROR_CODE_402;

/**
 * @author chenying
 * @date 2022/11/3 5:34 PM
 * @time 5:34 PM
 * @since 1.0.0
 **/
@RestController
public class ApiServiceImpl implements ApiService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserFaceInfoMapper faceInfoMapper;
    @Autowired
    private FaceEngineService faceEngineService;
    /**
     * 工具柜上传用户信息
     *
     * @param userInfo
     */
    @Transactional
    @Override
    public int uploadUserInfo(@RequestBody UserInfo userInfo) {
        Assert.checkNull(userInfo, ERROR_CODE_402 + ":请求参数不能为空！");
      //  Assert.checkNull(userInfo.getUcid(), ERROR_CODE_402 + ":员工号不能为空！");
        Assert.checkNull(userInfo.getName(), "员工姓名不能为空！");
        String loginName = U.isBlank(userInfo.getUcid())?userInfo.getName():userInfo.getUcid();
        String base64Photo = U.base64Process(userInfo.getBase64_photo());
        base64Photo = base64Photo.replace(" " ,"+");
        SysUser user = A.first(userMapper.findUserByLoginName(loginName));
        if(user!=null){
            List<SysUserFaceDTO> users = faceInfoMapper.findFaceInfoByLoginName(loginName);
            if(A.isEmpty(users)){
                return saveUserFaceInfo(user,base64Photo);
            }
            return 0;
        }else{
            user = new SysUser();
            user.setId(U.isBlank(userInfo.getUuid()) ? U.uuid() : userInfo.getUuid());
            user.setLoginName(loginName);
            user.setCreateTime(new Date());
            user.setPhone(userInfo.getPhone());
            user.setUserName(userInfo.getName());
            user.setBase64Photo("");
            // user.setBase64Photo(U.base64Process(userInfo.getBase64_photo()));
            user.setImage(userInfo.getFile_photo());
            user.setCompany(userInfo.getCompany());
            user.setCabinLimits(userInfo.getCabin_limits());
            user.setDisstartTime(DateUtil.parse(userInfo.getDisstart_datatime(), DateFormatType.YYYY_MM_DD_HH_MM_SS));
            user.setDisendTime(DateUtil.parse(userInfo.getDisendt_datatime(), DateFormatType.YYYY_MM_DD_HH_MM_SS));
            user.setState(1);
            user.setSource(Source.Android.name());
            String password;
            if (U.isNotBlank(user.getLoginName())) {
                if (user.getLoginName().length() >= 6) {
                    password = user.getLoginName().substring(user.getLoginName().length() - 6);
                } else {
                    password = user.getLoginName();
                }
            } else {
                password = U.DEFAULT_PASSWORD;
            }
            user.setPassword(Encrypt.toMd5(password));
            int count = userMapper.insertSelective(user);
            if(count>0){
                saveUserFaceInfo(user,base64Photo);
            }
            return count;
        }

    }

    @Transactional
    public int  saveUserFaceInfo(SysUser user,String base64Photo){
        LogUtil.LOG.info("==============================saveUserFaceInfo=================={} : {}"+ JsonUtil.toJson(user),base64Photo);
        SysUserFaceInfoWithBLOBs userFaceInfo = new SysUserFaceInfoWithBLOBs();
        userFaceInfo.setUserId(user.getId());
        userFaceInfo.setUserName(user.getUserName());
        userFaceInfo.setId(U.uuid());
        userFaceInfo.setLoginName(user.getLoginName());
        userFaceInfo.setPhoneBase64("");
        userFaceInfo.setCreateTime(new Date());
        userFaceInfo.setFaceFeature(faceEngineService.getFaceFeature(new FileDTO(base64Photo)));
        int count= faceInfoMapper.insertSelective(userFaceInfo);
        return count;
    }


    /**
     * 修改用户信息
     *
     * @param userInfo
     */
    @Override
    public int updateUserInfo(@RequestBody UserInfo userInfo) {
        return 0;
    }
}
