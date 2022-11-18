package com.xtxk.core.json;

import com.github.pagehelper.Page;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.core.vo.PageList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@ApiModel(value = "接口返回封装模型")
@Data
public class JsonResult<T> {

    /**
     * 返回码
     */
    public static enum Code {
        /**
         * 失败
         */
        FAIL(0,"失败"),
        /**
         * 成功
         */
        SUCCESS(1,"调用成功"),
        /**
         * 未认证
         */
        NO_AUTH(100,"未认证"),
        /**
         * 抢占登录
         */
        NO_LOGIN(-1,"抢占登录"),

        NO_PERMISSION(-2,"没有接口访问权限！");

        int flag;
        String des;

        Code(int flag,String des) {
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
            for (Code ce : values()) {
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
    private int code = Code.FAIL.flag;

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

    /**
     * 返回查询总数
     */
    @ApiModelProperty(value = "返回查询总数")
    private Integer total;

    /**
     * 返回当前页
     */
    @ApiModelProperty(value = "返回当前页")
    private Integer pageIndex;

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


//    public Integer getTotal() {
//        return total;
//    }
//
//    public void setTotal(Integer total) {
//        this.total = total;
//    }
//
//    public Integer getPageIndex() {
//        return pageIndex;
//    }
//
//    public void setPageIndex(Integer pageIndex) {
//        this.pageIndex = pageIndex;
//    }

    /**
     * 成功与否及信息
     */
    private JsonResult(boolean success, String msg) {
        this(success, msg, null);
    }

    /**
     * 成功与否, 信息 及 返回的数据
     */
    private JsonResult(boolean success, String msg, T data) {
        this(Code.getFlag(success), msg, data);
    }

    private JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private JsonResult(int code, String msg, T data, Integer total, Integer pageIndex) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.total = total;
        this.pageIndex = pageIndex;
    }
    /**
     * 返回码及信息
     */
    private JsonResult(Code code, String msg) {
        this(code, msg, null);
    }

    /**
     * 返回码, 信息 及 返回的数据
     */
    private JsonResult(Code code, String msg, T data) {
        this(code.getFlag(), msg, data);
    }

    public JsonResult setCode(Code code) {
        this.code = code.getFlag();
        return this;
    }

    public JsonResult setCode(int code) {
        // 只在里面的才能被赋值
        if (Code.contains(code)) this.code = code;
        return this;
    }


    /**
     * 当前对象渲染成一个 json 字符串
     */
    public String render() {
        String result = JsonUtil.toRender(this);
        if (LogUtil.ROOT_LOG.isInfoEnabled())
            LogUtil.ROOT_LOG.info("return json: " + result);
        return result;
    }

    public void toJson(HttpServletResponse response) throws IOException {
        render("application/json", response);
    }

    private void render(String type, HttpServletResponse response) throws IOException {
        response.setContentType(type + ";charset=UTF-8");
        response.getWriter().write(render());
    }

    public void toHtml(HttpServletResponse response) throws IOException {
        render("text/html", response);
    }


    // ---------- 下面的静态方法, 在 controller 中调用 ----------

    /**
     * 成功时要有 msg 说明
     */
    public static JsonResult success(String msg) {
        return new JsonResult(true, msg);
    }

    /**
     * 返回的数据不需要过滤属性时
     */
    public static <T> JsonResult success(String msg, T data) {
        return new JsonResult(true, msg, data);
    }

    public static <T> JsonResult success(String msg, T data, Integer total, Integer pageIndex) {
        return new JsonResult(1, msg, data, total, pageIndex);
    }

    /**
     * 是否为分页
     */
    public static <T> JsonResult success(String msg, T data, boolean isPage) {
        if (isPage) {
            return pageList(msg,data);
        }
        return success(msg,new ArrayList<>());
    }

    public static <T> JsonResult pageList(String msg, T data) {
        if (data != null) {
            Page<T> pageInfo = null;
            if(data instanceof Page){
                pageInfo = (Page<T>) data;
            }else if(data instanceof PageList) {
                PageList<T> pageList = (PageList<T>) data;
                return success(msg,pageList.getData(),(int)pageList.getTotal(),pageList.getPageNum());
            }else if(data instanceof List){
            }
            return success(msg, pageInfo.getResult(), (int) pageInfo.getTotal(), pageInfo.getPageNum());
        }
        return success(msg,new ArrayList<>());
    }

    /**
     * 返回的数据需要过滤属性时
     */
    public static <T> JsonResult success(String msg, T data, String... params) {
        return new JsonResult(true, msg, JsonUtil.toObjectWithField(data, params));
    }


    /**
     * 失败时要有 msg 说明
     */
    public static JsonResult fail(String msg) {
        return new JsonResult(false, msg);
    }

    /**
     * 失败时要有 msg 说明
     */
    public static JsonResult fail(String msg, Object obj) {
        return new JsonResult(false, msg, obj);
    }

    /**
     * 未登录时
     */
    public static JsonResult notLogin() {
        return new JsonResult(Code.NO_LOGIN, "您还未登录, 请先登录");
    }

    public static JsonResult notPermission(String msg){
        return new JsonResult(Code.NO_PERMISSION,msg);
    }

    public static JsonResult notAuth(String msg) {
        return new JsonResult(Code.NO_AUTH, msg);
    }

    public static JsonResult  grapLogin(String msg){
        return new JsonResult(Code.NO_LOGIN,msg);
    }

}
