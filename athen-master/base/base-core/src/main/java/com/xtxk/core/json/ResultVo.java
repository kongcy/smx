package com.xtxk.core.json;

import lombok.Data;

import java.io.Serializable;

/**
 * 工具柜返回body
 *
 * @author chenying
 * @date 2022/11/3 5:07 PM
 * @time 5:07 PM
 * @since 1.0.0
 **/
@Data
public class ResultVo implements Serializable {
    private int code;
    private String result;
    private String msg;

    private ResultVo(int code, String result) {
        this.code = code;
        this.result = result;
    }

    private ResultVo(int code, String result, String msg) {
        this(code, result);
        this.msg = msg;
    }
    /**成功*/
    public static ResultVo success() {
        return new ResultVo(State.SUCCESS.code, State.SUCCESS.value);
    }
    /**成功*/
    public static ResultVo success(String msg){
        return new ResultVo(State.SUCCESS.code,State.SUCCESS.value,msg);
    }
    /**失败*/
    public static ResultVo fail(){
        return new ResultVo(State.FAIL.code, State.FAIL.value);
    }
    /**失败*/
    public static ResultVo fail(String msg){
        return new ResultVo(State.FAIL.code, State.FAIL.value,msg);
    }
     public enum State {
        SUCCESS(0, "success"),
        FAIL(1, "failure");
        private int code;
        private String value;

        State(int code, String value) {
            this.code = code;
            this.value = value;
        }

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
}
