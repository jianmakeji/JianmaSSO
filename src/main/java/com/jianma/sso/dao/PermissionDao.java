package com.jianma.sso.dao;

import com.jianma.sso.model.Permission;

public interface PermissionDao {

	 public void createPermission(Permission permission);

	 public void deletePermission(Long permissionId);
	 
	 public void updatePermission(Permission permission);
	    
}
