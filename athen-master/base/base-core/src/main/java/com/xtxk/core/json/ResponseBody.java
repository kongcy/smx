package com.xtxk.core.json;

import com.xtxk.core.util.U;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description JsonResult<T> 简易版
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/9
 */
public class ResponseBody<T> {
    /**
     * 返回码
     */
    public static enum Code {
        /**
         * 失败
         */
        FAIL(0, "失败"),
        /**
         * 成功
         */
        SUCCESS(1, "调用成功"),
        /**
         * 未认证
         **/
        NO_AUTH(100, "未认证"),

        TOKEN_EXPIRE(-2, "TOKEN已过期"),
        /**
         * 抢占登录
         */
        NO_LOGIN(-1, "抢占登录");


        int flag;
        String des;

        Code(int flag, String des) {
            this.flag = flag;
            this.des = des;
        }

        public int getFlag() {
            return flag;
        }

        public static int getFlag(boolean success) {
            return success ? SUCCESS.flag : FAIL.flag;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public static boolean contains(int code) {
            for (ResponseBody.Code ce : values()) {
                if (ce.flag == code) return true;
            }
            return false;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }

    /**
     * 返回编码. 是否成功、是否登录等. 必须有值
     */
    @ApiModelProperty(value = "返回编码. 是否成功(0失败，1成功) 必须有值")
    private int code = JsonResult.Code.FAIL.flag;

    /**
     * 返回说明. 必须有值, 然而阻止不了传递 null 或者空字符
     */
    @ApiModelProperty(value = "返回说明. 必须有值")
    private String msg = U.EMPTY;

    /**
     * 返回数据. 可以是对象(User, 等同于 Map), 可以是数组(List&lt;User&gt;)
     */
    @ApiModelProperty(value = "返回数据. 可以是对象(User, 等同于 Map)")
    private T data;


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    /**
     * 成功与否及信息
     */
    private ResponseBody(boolean success, String msg) {
        this(success, msg, null);
    }

    /**
     * 成功与否, 信息 及 返回的数据
     */
    private ResponseBody(boolean success, String msg, T data) {
        this(ResponseBody.Code.getFlag(success), msg, data);
    }

    private ResponseBody(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回码及信息
     */
    private ResponseBody(ResponseBody.Code code, String msg) {
        this(code, msg, null);
    }

    /**
     * 返回码, 信息 及 返回的数据
     */
    private ResponseBody(ResponseBody.Code code, String msg, T data) {
        this(code.getFlag(), msg, data);
    }

    public ResponseBody setCode(ResponseBody.Code code) {
        this.code = code.getFlag();
        return this;
    }

    public ResponseBody setCode(int code) {
        // 只在里面的才能被赋值
        if (ResponseBody.Code.contains(code)) this.code = code;
        return this;
    }


    // ---------- 下面的静态方法, 在 controller 中调用 ----------

    /**
     * 成功时要有 msg 说明
     */
    public static ResponseBody success(String msg) {
        return new ResponseBody(true, msg);
    }

    /**
     * 返回的数据不需要过滤属性时
     */
    public static <T> ResponseBody success(String msg, T data) {
        return new ResponseBody(true, msg, data);
    }


    /**
     * 返回的数据需要过滤属性时
     */
    public static <T> ResponseBody success(String msg, T data, String... params) {
        return new ResponseBody(true, msg, JsonUtil.toObjectWithField(data, params));
    }


    /**
     * 失败时要有 msg 说明
     */
    public static ResponseBody fail(String msg) {
        return new ResponseBody(false, msg);
    }

    /**
     * 失败时要有 msg 说明
     */
    public static ResponseBody fail(String msg, Object obj) {
        return new ResponseBody(false, msg, obj);
    }

    /**
     * 未登录时
     */
    public static ResponseBody notLogin() {
        return new ResponseBody(ResponseBody.Code.NO_LOGIN, "您还未登录, 请先登录");
    }

    /**
     * 未认证
     */
    public static ResponseBody notAuth(String msg) {
        return new ResponseBody(ResponseBody.Code.NO_AUTH, msg);
    }
    /**
     * 其他地方登录
     */
    public static ResponseBody grapLogin(String msg) {
        return new ResponseBody(ResponseBody.Code.NO_LOGIN, msg);
    }
    /**
     * 已过期
     */
    public static ResponseBody tokenExpire(String msg) {
        return new ResponseBody(Code.TOKEN_EXPIRE, msg);
    }
}
