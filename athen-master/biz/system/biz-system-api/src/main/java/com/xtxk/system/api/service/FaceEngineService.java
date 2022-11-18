package com.xtxk.system.api.service;

import com.arcsoft.face.toolkit.ImageInfo;
import com.xtxk.core.annotation.NoToken;
import com.xtxk.core.annotation.NoTokenVerify;
import com.xtxk.system.api.DTO.FileDTO;
import com.xtxk.system.api.DTO.SysUserFaceDTO;
import com.xtxk.system.api.model.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 人脸识别引擎
 * @author chenying
 * @date 2022/11/8 11:07 AM
 * @time 11:07 AM
 * @since 1.0.0
 **/
@NoTokenVerify
@FeignClient(name = "biz-system-service")
public interface FaceEngineService {
    /**
     * 查找人脸
     * @param file 文件
     */
    @NoToken
    @RequestMapping(value = "/system/search", method = RequestMethod.POST)
    SysUser search(@RequestBody FileDTO file);

    /**
     * 获取人脸特征
     * @param base64Str 图片字符串
     * */
    @RequestMapping(value = "/system/getFaceFeature", method = RequestMethod.POST)
    byte[] getFaceFeature(@RequestBody FileDTO file);

    /**
     * 提取人脸特征
     * @param imageInfo
     * @return
     */
    @RequestMapping(value = "/system/extractFaceFeature", method = RequestMethod.POST)
    byte[] extractFaceFeature(@RequestBody ImageInfo imageInfo) throws InterruptedException;

    /**
     * 人脸比对
     * @param faceFeature
     * @return
     */
    @RequestMapping(value = "/system/compareFaceFeature", method = RequestMethod.POST)
    List<SysUserFaceDTO> compareFaceFeature(@RequestBody byte[] faceFeature) throws InterruptedException, ExecutionException;
}
