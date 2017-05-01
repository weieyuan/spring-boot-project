package com.wei.vo;

import lombok.Getter;
import lombok.Setter;

public class CommonRes {

	@Getter
	@Setter
	private boolean ok;

	@Getter
	@Setter
	private Object res;

	public CommonRes() {

	}

	public CommonRes(boolean ok, Object res) {
		this.ok = ok;
		this.res = res;
	}

}
