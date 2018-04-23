package com.jianma.sso.service;

import com.jianma.sso.model.PageModel;
import com.jianma.sso.model.Role;

public interface RoleService {

	public int createRole(Role role);
    
	public int deleteRole(Long roleId);
    
	public int updateRole(Role role);
	
    public int correlationPermissions(Long roleId, Long... permissionIds);
    
    public int uncorrelationPermissions(Long roleId, Long... permissionIds);
    
    public PageModel getDataByPage(int limit, int offset);

}
