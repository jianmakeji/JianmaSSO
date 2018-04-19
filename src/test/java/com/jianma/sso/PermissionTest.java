package com.jianma.sso;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.sso.model.Permission;
import com.jianma.sso.service.PermissionsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PermissionTest {

	@Autowired
	@Qualifier(value = "permissionServiceImpl")
	private PermissionsService permissionServiceImpl;
	
	//@Test
	public void createPermission(){
		Permission permission = new Permission();
		permission.setPermissionName("xxxx");
		permission.setCreatetime(new Date());
		permissionServiceImpl.createPermission(permission);
		/*
		permission.setPermissionName("人体测量三维图形库");
		permission.setCreatetime(new Date());
		permissionServiceImpl.createPermission(permission);
		*/
	}
	
	@Test
	public void deletePermission(){
		permissionServiceImpl.deletePermission(4L);
	}
	
	//@Test
	public void updatePermission(){
		Permission permission = new Permission();
		permission.setId(1);
		permission.setPermissionName("设计看板系统");
		permission.setCreatetime(new Date());
		permissionServiceImpl.updatePermission(permission);
	}
}
