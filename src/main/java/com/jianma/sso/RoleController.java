package com.jianma.sso;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.jianma.sso.model.Permission;
import com.jianma.sso.model.PermissionRole;
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
	public ResultModel createRole(HttpServletRequest request, HttpServletResponse response, @RequestBody Role role,
			@RequestParam String permissions)
			throws SSOException {
		resultModel = new ResultModel();

		role.setCreatetime(new Date());
		
		Set<PermissionRole> permissionRoles = new HashSet<PermissionRole>(5);
		
		Arrays.asList(permissions.split(",")).stream().forEach((s)->{
			PermissionRole pRole = new PermissionRole();
			pRole.setRole(role);
			Permission permission = new Permission();
			permission.setId(Integer.parseInt(s));
			pRole.setPermission(permission);
			permissionRoles.add(pRole);
		});
		
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
	@RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
	public ResultModel deleteRole(HttpServletRequest request, HttpServletResponse response,  @RequestParam int id)
			throws SSOException {
		resultModel = new ResultModel();

		int result = 0;
		try {
			result = roleServiceImpl.deleteRole((long)id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSOException(500, "创建出错");
		}

		if (result == ResponseCodeUtil.ROLE_OPERATION_FAILURE)
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
	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	public ResultModel updateRole(HttpServletRequest request, HttpServletResponse response, @RequestBody Role role)
			throws SSOException {
		resultModel = new ResultModel();

		role.setCreatetime(new Date());
		int result = 0;
		try {
			result = roleServiceImpl.updateRole(role);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSOException(500, "创建出错");
		}

		if (result == ResponseCodeUtil.ROLE_OPERATION_FAILURE)
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
	@RequestMapping(value = "/correlationPermissions", method = RequestMethod.POST)
	public ResultModel correlationPermissions(HttpServletRequest request, HttpServletResponse response, @RequestParam int roleId, @RequestParam String permissionIds)
			throws SSOException {
		resultModel = new ResultModel();
		
		String[] arrayPermissionIds = permissionIds.split(",");
		Long[] longPermissionIds = new Long[arrayPermissionIds.length];
		
		List<Long> longList = new ArrayList<>();
		for (String s : arrayPermissionIds){
			longList.add(Long.parseLong(s));
		}
		longList.toArray(longPermissionIds);
		
		int result = 0;
		try {
			result = roleServiceImpl.correlationPermissions((long)roleId, longPermissionIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSOException(500, "创建出错");
		}

		if (result == ResponseCodeUtil.ROLE_OPERATION_FAILURE)
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
	@RequestMapping(value = "/uncorrelationPermissions", method = RequestMethod.POST)
	public ResultModel uncorrelationPermissions(HttpServletRequest request, HttpServletResponse response, @RequestParam int roleId, @RequestParam String permissionIds)
			throws SSOException {
		resultModel = new ResultModel();
		
		String[] arrayPermissionIds = permissionIds.split(",");
		Long[] longPermissionIds = new Long[arrayPermissionIds.length];
		
		List<Long> longList = new ArrayList<>();
		for (String s : arrayPermissionIds){
			longList.add(Long.parseLong(s));
		}
		longList.toArray(longPermissionIds);
		
		int result = 0;
		try {
			result = roleServiceImpl.uncorrelationPermissions((long)roleId, longPermissionIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSOException(500, "创建出错");
		}

		if (result == ResponseCodeUtil.ROLE_OPERATION_FAILURE)
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
	@RequestMapping(value = "/getDataByPage", method = RequestMethod.GET)
	public ListResultModel getDataByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit) {
		ListResultModel listResultModel = new ListResultModel();
		try {
			PageModel pageModel = roleServiceImpl.getDataByPage(limit,offset);
			listResultModel.setTotalRecords(pageModel.getCount());
			listResultModel.setResultData(pageModel.getList());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
} 
