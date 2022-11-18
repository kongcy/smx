package com.xtxk.core.vo;

/**
 * @Description 权限code
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/28
 */
public enum Permission {
    READ(400, "只读"),
    OPERATION(200, "可操作"),
    NO_PERMISSION(503, "无操作权限");

    Permission(int code, String value) {
        this.code = code;
        this.value = value;
    }

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
