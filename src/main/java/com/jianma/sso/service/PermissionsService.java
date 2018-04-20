package com.jianma.sso.service;

import com.jianma.sso.model.PageModel;
import com.jianma.sso.model.Permission;

public interface PermissionsService {

	public int createPermission(Permission permission);

	public int deletePermission(Long permissionId);
	
	public int updatePermission(Permission permission);
	
	public PageModel getDataByPage(int limit, int offset);
}
