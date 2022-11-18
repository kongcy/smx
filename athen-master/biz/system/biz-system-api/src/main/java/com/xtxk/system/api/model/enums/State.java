package com.xtxk.system.api.model.enums;

/**
 * 通用状态
 ***/
public enum State {
	Disable(0, "锁定"), Enable(1, "开启"), NoStart(-1, "未开始");

	int code;
	String value;

	State(int code, String value) {
		this.code = code;
		this.value = value;
	}

	/** 显示用 */
	public String getValue() {
		return value;
	}

	/** 数据关联用 */
	public int getCode() {
		return code;
	}
}
