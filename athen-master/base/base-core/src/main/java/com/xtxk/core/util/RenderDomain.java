package com.xtxk.core.util;

public class RenderDomain {

    /** pc 端 */
    private String index;

    /** 接口调用的域名, 走 https */
    private String api;

    /** html 5 域名, 主要基于 wechat */
    //private String mobile;

    /** 资源文件域名, css png js 等 */
    private String still;

    /** 用户上传的图片、文件(按目录区分)域名 */
    private String upload;

    /** 后台系统的域名 */
    private String manager;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getStill() {
        return still;
    }

    public void setStill(String still) {
        this.still = still;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
