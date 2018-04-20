package com.jianma.sso.exception;

public class SSOException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private int errCode;
	private String errMsg;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public SSOException(int errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	
}
