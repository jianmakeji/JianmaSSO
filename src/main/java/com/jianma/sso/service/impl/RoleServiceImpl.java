package com.jianma.sso.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.sso.dao.RoleDao;
import com.jianma.sso.model.PageModel;
import com.jianma.sso.model.Role;
import com.jianma.sso.service.RoleService;
import com.jianma.sso.util.ResponseCodeUtil;


@Service
@Component
@Qualifier(value = "roleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	@Qualifier(value = "roleDaoImpl")
	private RoleDao roleDaoImpl;
	
	@Override
	public int createRole(Role role) {
		try{
			Optional<Role> opRole = roleDaoImpl.getRoleByName(role.getRolename());
			if (opRole.isPresent()){
				return ResponseCodeUtil.ROLE_IS_EXISTS;
			}
			else{
				roleDaoImpl.createRole(role);
				return ResponseCodeUtil.ROLE_OPERATION_SUCESS;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.ROLE_OPERATION_FAILURE;
		}
		
	}

	@Override
	public int deleteRole(Long roleId) {
		try{
			roleDaoImpl.deleteRole(roleId);
			return ResponseCodeUtil.ROLE_OPERATION_SUCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.ROLE_OPERATION_FAILURE;
		}
	}

	@Override
	public int correlationPermissions(Long roleId, Long... permissionIds) {
		try{
			roleDaoImpl.correlationPermissions(roleId, permissionIds);
			return ResponseCodeUtil.ROLE_OPERATION_SUCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.ROLE_OPERATION_FAILURE;
		}
	}

	@Override
	public int uncorrelationPermissions(Long roleId, Long... permissionIds) {
		
		try{
			roleDaoImpl.uncorrelationPermissions(roleId, permissionIds);
			return ResponseCodeUtil.ROLE_OPERATION_SUCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.ROLE_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateRole(Role role) {
		try{
			roleDaoImpl.updateRole(role);
			return ResponseCodeUtil.ROLE_OPERATION_SUCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.ROLE_OPERATION_FAILURE;
		}
		
	}

	@Override
	public PageModel getDataByPage(int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setList(roleDaoImpl.getDataByPage(limit, offset));
		pageModel.setCount(roleDaoImpl.countRole());
		return pageModel;
	}

	

}
