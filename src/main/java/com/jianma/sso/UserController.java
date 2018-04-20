package com.jianma.sso;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.servlet.ModelAndView;

import com.jianma.sso.exception.SSOException;
import com.jianma.sso.model.ListResultModel;
import com.jianma.sso.model.PageModel;
import com.jianma.sso.model.ResultModel;
import com.jianma.sso.model.User;
import com.jianma.sso.service.UserService;
import com.jianma.sso.util.GraphicsUtil;
import com.jianma.sso.util.ResponseCodeUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController extends SSOController {

	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResultModel registerUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user)
			throws SSOException {
		resultModel = new ResultModel();

		user.setCreatetime(new Date());
		int result = 0;
		try {
			result = userServiceImpl.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SSOException(500, "创建出错");
		}

		if (result == ResponseCodeUtil.UESR_CREATE_EXIST) {
			resultModel.setResultCode(300);
			resultModel.setSuccess(false);
			resultModel.setMessage("用户已经存在!");
		} else {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
		}

		return resultModel;

	}

	
	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @param newPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetYourPwd", method = RequestMethod.POST)
	public ResultModel resetYourPwd(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String email, @RequestParam String newPwd) {
		resultModel = new ResultModel();
		try {
			userServiceImpl.updatePwd(email, newPwd);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new SSOException(500, "修改出错");
		}
	}

	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		try {

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			// 表明生成的响应是图片
			response.setContentType("image/jpeg");

			Map<String, Object> map = new GraphicsUtil().getGraphics();
			request.getSession().setAttribute("rand", map.get("rand"));
			ImageIO.write((RenderedImage) map.get("image"), "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@ResponseBody
	@RequestMapping(value = "/getDataByPage", method = RequestMethod.POST)
	public ListResultModel getDataByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit) {
		ListResultModel listResultModel = new ListResultModel();
		try {
			PageModel pageModel = userServiceImpl.getUserByPage(offset, limit);
			listResultModel.setTotalRecords(pageModel.getCount());
			listResultModel.setResultData(pageModel.getList());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}

	@ResponseBody
	@RequestMapping(value = "/resetUserValid", method = RequestMethod.POST)
	public ResultModel resetUserValid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String email, @RequestParam int valid) {
		resultModel = new ResultModel();
		try {
			userServiceImpl.updateValidSign(email, valid);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new SSOException(500, "更新用户状态出错");
		}
	}
}
