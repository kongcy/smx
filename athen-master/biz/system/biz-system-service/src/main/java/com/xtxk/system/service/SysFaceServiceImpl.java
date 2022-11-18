package com.xtxk.system.service;

import com.xtxk.core.util.A;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.system.api.DTO.FileDTO;
import com.xtxk.system.api.DTO.SysUserFaceImageDTO;
import com.xtxk.system.api.model.SysUserFaceInfoWithBLOBs;
import com.xtxk.system.api.service.FaceEngineService;
import com.xtxk.system.api.service.SysFaceService;
import com.xtxk.system.repository.SysUserFaceInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 更新用户头像
 * @author chenying
 * @date 2022/11/14 9:57 AM
 * @time 9:57 AM
 * @since 1.0.0
 **/
@Service
public class SysFaceServiceImpl implements SysFaceService {
    @Autowired
    private SysUserFaceInfoMapper userFaceInfoMapper;
    @Autowired
    private FaceEngineService faceEngineService;
    /**
     * 更新用户头像
     */
    @Override
    public int updateUserImage() {
        List<SysUserFaceImageDTO> userFaces =userFaceInfoMapper.findFaceFeature();
        if(A.isEmpty(userFaces)){
            return 0;
        }
        int i =0;
        for(SysUserFaceImageDTO face:userFaces){
            if(U.isNotBlank(face.getPhoneBase64())&&U.isBlank(face.getFaceFeature())){
                byte[] bytes = faceEngineService.getFaceFeature(new FileDTO(face.getPhoneBase64()));
                SysUserFaceInfoWithBLOBs record = new SysUserFaceInfoWithBLOBs();
                record.setFaceFeature(bytes);
                record.setId(face.getId());
                int count=userFaceInfoMapper.updateByPrimaryKeySelective(record);
                if(count>0){
                    LogUtil.LOG.info("========================用户："+face.getLoginName()+" 头像信息已经成功更新========================");
                    i++;
                }
            }
        }
        return i;
    }

    @Override
    public int updateUserInfo() {
        return 0;
    }
}
