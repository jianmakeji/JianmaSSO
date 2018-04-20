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
import com.jianma.sso.model.Permission;
import com.jianma.sso.model.PermissionRole;
import com.jianma.sso.model.Role;
import com.jianma.sso.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RoleTest {

	@Autowired
	@Qualifier(value = "roleServiceImpl")
	private RoleService roleServiceImpl;
	
	//@Test
	public void createRole(){
		Role role = new Role();
		role.setRolename("管理员");
		role.setCreatetime(new Date());
		
		Set<PermissionRole> permissionRoles = new HashSet<PermissionRole>(5);
		
		PermissionRole pRole = new PermissionRole();
		pRole.setRole(role);
		Permission permission = new Permission();
		permission.setId(1);
		pRole.setPermission(permission);
		permissionRoles.add(pRole);
		
		pRole = new PermissionRole();
		pRole.setRole(role);
		permission = new Permission();
		permission.setId(2);
		pRole.setPermission(permission);
		permissionRoles.add(pRole);
		
		pRole = new PermissionRole();
		pRole.setRole(role);
		permission = new Permission();
		permission.setId(3);
		pRole.setPermission(permission);
		permissionRoles.add(pRole);
		
		role.setPermissionRoles(permissionRoles);
		
		roleServiceImpl.createRole(role);
	}
	
	@Test
	public void updateRole(){
		
		//roleServiceImpl.correlationPermissions(1L, 4L);
		
	}
	
	@Test
	public void deleteRole(){
		
		//roleServiceImpl.deleteRole(3L);
		
	}
	
	@Test
	public void uncorelationPermission(){
		//roleServiceImpl.uncorrelationPermissions(1L, 4L);
	}
	
	//@Test 
	public void updateRoleName(){
		Role role = new Role();
		role.setId(1);
		role.setRolename("系统管理员");
		role.setCreatetime(new Date());
		roleServiceImpl.updateRole(role);
	}
	
	@Test
	public void getDataPage(){
		PageModel pageModel = roleServiceImpl.getDataByPage(10, 0);
		System.out.println(pageModel.getCount());
		
		List<?> list = pageModel.getList();
		
		for(Object obj : list){
			if (obj instanceof Role){
				System.out.println(((Role)obj).getRolename());
			}
		}
	}
}
