package com.jianma.sso;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
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
import com.jianma.sso.model.ResultModel;
import com.jianma.sso.model.Role;
import com.jianma.sso.service.RoleService;
import com.jianma.sso.util.ResponseCodeUtil;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends SSOController{

	@Autowired
	@Qualifier(value = "roleServiceImpl")
	private RoleService roleServiceImpl;
	
	
	@ResponseBody
	@RequestMapping(value = "/createRole", method = RequestMethod.POST)
	public ResultModel createRole(HttpServletRequest request, HttpServletResponse response, @RequestBody Role role)
			throws SSOException {
		resultModel = new ResultModel();

		role.setCreatetime(new Date());
		int result = 0;
		try {
			result = roleServiceImpl.createRole(role);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSOException(500, "创建出错");
		}

		if (result == ResponseCodeUtil.ROLE_IS_EXISTS) {
			resultModel.setResultCode(300);
			resultModel.setSuccess(false);
			resultModel.setMessage("角色已经存在!");
		} 
		else if (result == ResponseCodeUtil.ROLE_OPERATION_FAILURE)
		{
			resultModel.setResultCode(400);
			resultModel.setSuccess(false);
			resultModel.setMessage("创建失败!");
		}
		else {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
		}

		return resultModel;

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getDataByPage", method = RequestMethod.POST)
	public ListResultModel getDataByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit) {
		ListResultModel listResultModel = new ListResultModel();
		try {
			PageModel pageModel = roleServiceImpl.getDataByPage(offset, limit);
			listResultModel.setTotalRecords(pageModel.getCount());
			listResultModel.setResultData(pageModel.getList());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
