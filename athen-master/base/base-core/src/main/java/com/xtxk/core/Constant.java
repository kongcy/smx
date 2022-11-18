package com.xtxk.core;

import com.xtxk.core.util.U;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by foresee on 2019-01-27.
 * 常量类
 */
public abstract class  Constant {
    public static final ThreadLocal<Object> PAGE_LOCAL = new ThreadLocal();
    public static final ThreadLocal<String> TOKEN_LOCAL = new ThreadLocal();
    /**
     * 数据源dataSourceKey
     */
    public static final String DATA_SOURCE_KEY = "dataSourceKey";
    //token
    public static final String AUTHOR_TOKEN = "AuthorToken";
    //redis缓存
    public static final String CACHE_AUTHOR_TOKEN = "AUTHOR_TOKEN";

    /**
     * 默认数据源
     */
    public static final String DEFAULT_DB = "master";
    public final static String DUBBO_GROUP = Dubbo.V1.getGroup();

    public final static String DEFAULT_PASSWORD = "MTIzNDU2";
    public final static String DUBBO_VERSION = "1.0.0";
    public static final String SUPPORT_METHODS = "GET,POST,DELETE,PUT";
    //插件相关常量
    public static final String PLUGIN_DECODE_RESOLUTION = "480P";
    public static final String PLUGIN_DECODE_TYPE_0 = "0";
    public static final String PLUGIN_DECODE_TYPE_1 = "1";
    public static final String PLUGIN = "1";
    public static final String NO_PLUGIN = "2";
    public static final String PLUGIN_LOCAL_IP = "127.0.0.1";
    public static final boolean STATUS = false;
    //用户在线
    public static final String PLUGIN_USER_STATE = "online";
    //用户离线
    public static final String PLUGIN_USER_OFFLINE_STATE = "offLine";
    /**
     * 前台传递过来的分页参数名
     */
    public static final String GLOBAL_PAGE = "currentPage";
    /**
     * 前台传递过来的每页条数名
     */
    public static final String GLOBAL_LIMIT = "limit";
    /**
     * 当前默认页数
     */
    public static final int DEFAULT_PAGE_NO = 1;
    /***
     * 每页默认总条数
     */
    public static final int DEFAULT_LIMIT = 10;
    /**
     * 在线
     */
    public final static String ONLINE = "1";
    /**
     * 离线
     */
    public final static String OFFLINE = "0";
    /**
     * 设备在线
     */
    public static final String DEVICE_ONLINE = "DeviceOnline";
    /**
     * 通道在线
     */
    public static final String DEVICE_CHANNEL_ONLINE = "DeviceChannelOnline";
    public static final String CONFERENCE_DETAIL_URL = "/conference/getSingleHistoryConferenceDetail";
    /**
     * 错误码
     */
    public static final String ERROR_CODE_401="401";

    public static final String ERROR_CODE_402="402";

    public static final String ERROR_CODE_403="403";
    /**
     * 登录状态
     */
    public static enum LoginSate {
        SUCCESS(1, "登录成功"),
        FAIL(-1, "登录失败"),
        LOCK(0, "账号锁定");
        private int code;
        private String des;

        LoginSate(int code, String des) {
            this.code = code;
            this.des = des;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }

    /**
     * dubbo版本控制，以后升级接口直接修改dubbo服务端，版本号。
     */
    public static enum Dubbo {
        V1("athenSystem", "1.0.0"),
        V2("athenSystem", "2.0.0");
        private String group;  //dubbo云上电厅的服务组
        private String version; //服务版本号，该版本有客户端传入，dubbo服务升级，通过客户端版本号可以控制调用不同的版本服务，解决版本兼容问题

        Dubbo(String group, String version) {
            this.group = group;
            this.version = version;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

    }

    /**
     * 统一认证登录返回code和对应msg
     */
    public enum UserLoginErrorCodeType {
        IP_NOT_PERMIT_IN_LIST("error1", "检查IP地址是否在允许列表之内"),
        NO_REGISTER("error2", "检查系统是否已经注册"),
        INVALID_USER_NAME("error3", "检查用户名是否正确"),
        INVALID_SECURITY_PASSWORD("error4", "检查密码是否安全规则"),
        USER_NO_PERMISSION("error5", "检查用户是否有使用权限"),
        WRONG_PASSWORD("error6", "检查密码是否正确"),
        DB_EXCEPTION("error7", "数据库操作异常");
        String returnCode;
        String msg;

        public String getReturnCode() {
            return returnCode;
        }

        public String getMsg() {
            return msg;
        }

        UserLoginErrorCodeType(String returnCode, String msg) {
            this.returnCode = returnCode;
            this.msg = msg;
        }

        public static UserLoginErrorCodeType getByCode(String returnCode) {
            if (U.isNotBlank(returnCode)) {
                for (UserLoginErrorCodeType type : values()) {
                    if (type.returnCode.equals(returnCode)) {
                        return type;
                    }
                }
            }
            return DB_EXCEPTION;
        }
    }

    /**
     * 录像类型
     */
    public enum RecordType {
        SERVICE_RECORD(1, "服务器录像"),
        AUTO_RECORD(2, "手动录像"),
        BJ_RECORD(3, "报警录像"),
        ZD_RECORD(4, "重大资源录像");
        private int code;
        private String name;

        RecordType(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 音视讯信息管理- 设备操作
     */
    public enum OperationTypeEnum {
        REGISTER_DEVICE(1, "注册设备"),
        UPDATE(2, "编辑"),
        DELETE(3, "删除"),
        IN_LAKE(4, "入湖");
        private int value;
        private String name;

        OperationTypeEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
