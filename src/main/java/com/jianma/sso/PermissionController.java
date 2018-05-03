package com.jianma.sso;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jianma.sso.exception.SSOException;
import com.jianma.sso.model.ListResultModel;
import com.jianma.sso.model.PageModel;
import com.jianma.sso.model.Permission;
import com.jianma.sso.model.ResultModel;
import com.jianma.sso.service.PermissionsService;
import com.jianma.sso.util.ResponseCodeUtil;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController extends SSOController{

	@Autowired
	@Qualifier(value = "permissionServiceImpl")
	private PermissionsService permissionServiceImpl;
	
	@ResponseBody
	@RequestMapping(value = "/createPermission", method = RequestMethod.POST)
	public ResultModel createPermission(HttpServletRequest request, HttpServletResponse response, @RequestBody Permission permission)
			throws SSOException {
		resultModel = new ResultModel();
		permission.setCreatetime(new Date());
		int result = 0;
		try {
			result = permissionServiceImpl.createPermission(permission);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSOException(500, "创建出错");
		}

		if (result == ResponseCodeUtil.PERMISSION_OPERATION_SUCESS)
		{
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			
		}
		else {
			resultModel.setResultCode(400);
			resultModel.setSuccess(false);
			resultModel.setMessage("创建失败!");
		}

		return resultModel;

	}
	
	@ResponseBody
	@RequestMapping(value = "/deletePermission", method = RequestMethod.POST)
	public ResultModel deletePermission(HttpServletRequest request, HttpServletResponse response, @RequestParam int id)
			throws SSOException {
		resultModel = new ResultModel();

		int result = 0;
		try {
			result = permissionServiceImpl.deletePermission((long)id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSOException(500, "创建出错");
		}

		if (result == ResponseCodeUtil.PERMISSION_OPERATION_SUCESS)
		{
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			
		}
		else {
			resultModel.setResultCode(400);
			resultModel.setSuccess(false);
			resultModel.setMessage("创建失败!");
		}

		return resultModel;

	}
	
	@ResponseBody
	@RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
	public ResultModel updatePermission(HttpServletRequest request, HttpServletResponse response, @RequestBody Permission permission)
			throws SSOException {
		resultModel = new ResultModel();

		permission.setCreatetime(new Date());
		int result = 0;
		try {
			result = permissionServiceImpl.updatePermission(permission);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSOException(500, "创建出错");
		}

		if (result == ResponseCodeUtil.PERMISSION_OPERATION_SUCESS)
		{
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			
		}
		else {
			resultModel.setResultCode(400);
			resultModel.setSuccess(false);
			resultModel.setMessage("创建失败!");
		}

		return resultModel;

	}
	@ResponseBody
	@RequestMapping(value = "/getDataByPage", method = RequestMethod.GET)
	public ListResultModel getDataByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit) {
		ListResultModel listResultModel = new ListResultModel();
		try {
			PageModel pageModel = permissionServiceImpl.getDataByPage(limit, offset);
			listResultModel.setTotalRecords(pageModel.getCount());
			listResultModel.setResultData(pageModel.getList());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
