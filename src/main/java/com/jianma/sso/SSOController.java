package com.jianma.sso;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jianma.sso.exception.SSOException;
import com.jianma.sso.model.ResultModel;


public abstract class SSOController {

	protected ResultModel resultModel = null;

	@ExceptionHandler(SSOException.class)
	public @ResponseBody ResultModel handleDCException(SSOException ex) {
		resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	
}
