package com.xtxk.core.vo;

import com.xtxk.core.util.A;

import java.util.List;
import java.util.Map;

/**
 * 会议接口请求结果
 * Created by Administrator on 2020/12/15.
 */
public class RTOPTerminalResultVO {

    private Boolean success;

    private Integer errorStatus;

    private String errorMsg;

    private Map<String, List<TerminalInfo>> data;

    public static class TerminalInfo {

        //井筒名
        private String wellboreName;

        //井ID
        private String id;

        //终端会议号
        private String callNumber;

        public String getWellboreName() {
            return wellboreName;
        }

        public void setWellboreName(String wellboreName) {
            this.wellboreName = wellboreName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCallNumber() {
            return callNumber;
        }

        public void setCallNumber(String callNumber) {
            this.callNumber = callNumber;
        }
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(Integer errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Map<String, List<TerminalInfo>> getData() {
        return data;
    }

    public void setData(Map<String, List<TerminalInfo>> data) {
        this.data = data;
    }

    public boolean isOK() {
        return this.success && A.isNotEmpty(this.data.get("ipData"));
    }

    public List<TerminalInfo> ipDataList() {
        return this.data.get("ipData");
    }

    public List<TerminalInfo> wellDataList() {
        return this.data.get("wellData");
    }
}
