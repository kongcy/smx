package com.xtxk.system.api.model;

import java.io.Serializable;

public class SysUserFaceInfoWithBLOBs extends SysUserFaceInfo implements Serializable {
    private byte[] faceFeature;

    private String phoneBase64;

    private static final long serialVersionUID = 1L;

    public byte[] getFaceFeature() {
        return faceFeature;
    }

    public void setFaceFeature(byte[] faceFeature) {
        this.faceFeature = faceFeature;
    }

    public String getPhoneBase64() {
        return phoneBase64;
    }

    public void setPhoneBase64(String phoneBase64) {
        this.phoneBase64 = phoneBase64 == null ? null : phoneBase64.trim();
    }
}