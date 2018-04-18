package com.jianma.sso.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.sso.dao.UserDao;
import com.jianma.sso.model.PageModel;
import com.jianma.sso.model.Role;
import com.jianma.sso.model.User;
import com.jianma.sso.model.UserRole;
import com.jianma.sso.service.UserService;
import com.jianma.sso.util.Md5SaltTool;
import com.jianma.sso.util.ResponseCodeUtil;


@Service
@Component
@Qualifier(value = "userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier(value = "userDaoImpl")
	private UserDao userDaoImpl;
	
	@Override
	public int createUser(User user) {
		try {
			Optional<User> optUser = userDaoImpl.findByEmail(user.getEmail());

			if (optUser.isPresent()) {
				return ResponseCodeUtil.UESR_CREATE_EXIST;
			} else {
				
				user.setPassword(Md5SaltTool.getEncryptedPwd(user.getPassword()));
				Set<UserRole> userRoles = new HashSet<>();
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				Role role = new Role();
				role.setId(3);
				userRole.setRole(role);
				userRoles.add(userRole);
				user.setUserRoles(userRoles);
				userDaoImpl.createUser(user);

				
				return ResponseCodeUtil.UESR_OPERATION_SUCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public int deleteUser(Long userId) {
		try {
			userDaoImpl.deleteUser(userId);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int correlationRoles(Long userId, Long... roleIds) {
		try {
			userDaoImpl.correlationRoles(userId, roleIds);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public int uncorrelationRoles(Long userId, Long... roleIds) {
		try {
			userDaoImpl.uncorrelationRoles(userId, roleIds);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public Optional<User> findOne(Long userId) {
		return userDaoImpl.findOne(userId);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userDaoImpl.findByEmail(email);
	}

	@Override
	public Set<String> findRoles(String username) {

		return userDaoImpl.findRoles(username);
	}

	@Override
	public Set<String> findPermissions(String username) {

		return userDaoImpl.findPermissions(username);
	}


	@Override
	public int updatePwd(String email, String password,String oldSlot) {
		try {

			User user = new User();
			user.setPassword(Md5SaltTool.getEncryptedPwd(user.getPassword()));
			user.setEmail(email);
			
			userDaoImpl.updatePwd(email, user.getPassword(), oldSlot, user.getSlot());
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}
	
	
	@Override
	public Optional<User> checkAuthc(String email) {
		// TODO Auto-generated method stub
		return userDaoImpl.checkAuthc(email);
	}

	@Override
	public PageModel getUserByPage(int offset, int limit) {
		List<User> list = userDaoImpl.findUserListByPage(offset, limit);
		int count = userDaoImpl.getCountUser();
		PageModel userPageModel = new PageModel();
		userPageModel.setCount(count);
		userPageModel.setList(list);
		return userPageModel;
	}

	@Override
	public int updateValidSign(String email, int validValue) {
		try {
			userDaoImpl.updateValidSign(email, validValue);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

}
