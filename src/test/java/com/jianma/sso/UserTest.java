package com.jianma.sso;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.sso.model.PageModel;
import com.jianma.sso.model.Role;
import com.jianma.sso.model.User;
import com.jianma.sso.model.UserRole;
import com.jianma.sso.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserTest {

	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;
	
	@Test
	public void createUser(){
		User user = new User();
		user.setEmail("cidic@cidic.cn");
		user.setPassword("111111");
		user.setValid((byte)1);
		user.setCreatetime(new Date());
		
		
		Set<UserRole> userRoles = new HashSet<UserRole>(5);
		
		UserRole userRole = new UserRole();
		
		Role role = new Role();
		role.setId(1);
		
		userRole.setUser(user);
		userRole.setRole(role);
		userRoles.add(userRole);
		
		user.setUserRoles(userRoles);
		
		
		userServiceImpl.createUser(user);
	}
	
	
	public void checkUser(){
		int result1 = userServiceImpl.checkAuthc("cidic@cidic.cn","111111");
		System.out.println("result1:" + result1);
		
		int result2 = userServiceImpl.checkAuthc("cidic@cidic.cn","1111xx11");
		System.out.println("result2:" + result2);
		
		int result3 = userServiceImpl.checkAuthc("cidic@cidic.com","111111");
		System.out.println("result3:" + result3);
	}
	
	@Test
	public void updateUser(){
		userServiceImpl.updatePwd("cidic@cidic.cn", "222222");
	}
	
	public void deleteUser(){
		
	}
	
	//@Test
	public void getUserByPage(){
		PageModel pageModel = userServiceImpl.getUserByPage(0, 10);
		
		System.out.println(pageModel.getCount());
		
		List<?> list = pageModel.getList();
		
		for(Object obj : list){
			if (obj instanceof User){
				System.out.println(((User)obj).getEmail());
				((User)obj).getUserRoles().stream().forEach((userRole)->{
					System.out.println(userRole.getRole().getRolename());
				});;
			}
		}
	}
	
	//@Test
	public void getRoleByUsername(){
		Set<String> set = userServiceImpl.findRoles("cidic@cidic.cn");
		
		set.stream().forEach(System.out::println);
	}
	
	//@Test
	public void getPermissionByUsername(){
		Set<String> set = userServiceImpl.findPermissions("cidic@cidic.cn");
		
		set.stream().forEach(System.out::println);
	}
}
