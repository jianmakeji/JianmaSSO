package com.jianma.sso.dao;

import java.util.List;

import com.jianma.sso.model.Permission;

public interface PermissionDao {

	 public void createPermission(Permission permission);

	 public void deletePermission(Long permissionId);
	 
	 public void updatePermission(Permission permission);
	    
	 public List<Permission> getDataByPage(int limit, int offset);
	    
	 public int countPermissions();
}
