package com.xtxk.system.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenying
 * @date 2022/11/14 10:25 AM
 * @time 10:25 AM
 * @since 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserFaceImageDTO implements Serializable {
    private String id;
    private String loginName;
    private byte[] faceFeature;
    private String phoneBase64;
}
