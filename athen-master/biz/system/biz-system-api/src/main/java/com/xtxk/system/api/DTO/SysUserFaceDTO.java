package com.xtxk.system.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenying
 * @date 2022/11/8 2:28 PM
 * @time 2:28 PM
 * @since 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserFaceDTO implements Serializable {

    private String userId;

    private String loginName;

    private byte[] faceFeature;

    private Integer similarValue;
}
