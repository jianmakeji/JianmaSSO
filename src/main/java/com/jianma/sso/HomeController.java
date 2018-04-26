package com.jianma.sso;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jianma.sso.dao.UserDao;
import com.jianma.sso.model.Permission;
import com.jianma.sso.model.PermissionRole;
import com.jianma.sso.model.ResultModel;
import com.jianma.sso.model.Role;
import com.jianma.sso.model.User;
import com.jianma.sso.model.UserRole;
import com.jianma.sso.service.UserService;
import com.jianma.sso.util.JwtUtil;
import com.jianma.sso.util.Md5SaltTool;
import com.jianma.sso.util.ResponseCodeUtil;
import com.jianma.sso.util.WebRequestUtil;

import io.jsonwebtoken.Claims;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;
	
	@RequestMapping(value = "/")
	public String home(HttpServletRequest request, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/authorityCheck", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel authorityCheck(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String username, @RequestParam String password) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();

		Optional<User> user = userServiceImpl.findByEmail(username);
		if (user.isPresent()){
			try {
				if(Md5SaltTool.validPassword(password, user.get().getPassword())){
					
					String subject = JwtUtil.generalSubject(user.get().getId(),user.get().getUserRoles());
					String token = JwtUtil.createJWT(ResponseCodeUtil.JWT_ID, subject, ResponseCodeUtil.JWT_TTL);
					String refreshToken = JwtUtil.createJWT(ResponseCodeUtil.JWT_ID, subject, ResponseCodeUtil.JWT_REFRESH_TTL);
					JSONObject jo = new JSONObject();
					User userObj = user.get();
					
					Set<String> permissionsSet = userServiceImpl.findPermissions(username);
					
					jo.put("token", token);
					jo.put("refreshToken", refreshToken);
					jo.put("userId", userObj.getId());
					jo.put("roles", userObj.getUserRoles());
					jo.put("permissions", permissionsSet);
					resultModel.setObject(jo);
					resultModel.setMessage("验证成功!");
					resultModel.setResultCode(200);
					
					Set<UserRole> userRoleSet = userObj.getUserRoles();
					StringBuilder roleBuilder = new StringBuilder();
					userRoleSet.stream().forEach((userRole)->{
						roleBuilder.append(userRole.getRole().getRolename() + ",");
					});
					
					StringBuilder permissionBuilder = new StringBuilder();
					permissionsSet.stream().forEach((s)->{
						permissionBuilder.append(s + ",");
					});
					
					StringBuilder cookieBuilder = new StringBuilder();
					cookieBuilder.append("token="+token+"#");
					cookieBuilder.append("refreshToken="+refreshToken+"#");
					cookieBuilder.append("userId="+userObj.getId()+"#");
					cookieBuilder.append("roles="+roleBuilder.toString()+"#");
					cookieBuilder.append("permissions="+permissionBuilder.toString()+"#");
					
					WebRequestUtil.setResponseCookie(response, cookieBuilder.toString());
				}else{
					resultModel.setMessage("密码不正确!");
					resultModel.setResultCode(110);
				}
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				resultModel.setMessage("验证失败!");
				resultModel.setResultCode(110);
				e.printStackTrace();
			}
			
		}else{
			resultModel.setResultCode(110);
			resultModel.setMessage("用户不存在！");
		}
		
		return resultModel;
	}
	
	@RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel refreshToken(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String token) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ResultModel resultModel = new ResultModel();

    	Claims claims = JwtUtil.parseJWT(token);
 		String json = claims.getSubject();
 		JSONObject jObject = JSONObject.parseObject(json);
 		User user = new User();
 		user.setId(jObject.getIntValue("userId"));
 		
 		Set<UserRole> userRoles = new HashSet<>();
 		
 		String[] roles = jObject.getString("roles").split(",");
 		UserRole uRole = null;
 		for (String roleName : roles){
 			uRole = new UserRole();
 			uRole.setUser(user);
 			Role role = new Role();
 			role.setRolename(roleName);
 			uRole.setRole(role);
 		}
 		String subject = JwtUtil.generalSubject(user.getId(),userRoles);
 		String refreshToken = JwtUtil.createJWT(ResponseCodeUtil.JWT_ID, subject, ResponseCodeUtil.JWT_TTL);
 		
		resultModel.setResultCode(200);
		resultModel.setMessage("更新token成功！");
		resultModel.setObject(refreshToken);
		
		return resultModel;
	}
	
}
