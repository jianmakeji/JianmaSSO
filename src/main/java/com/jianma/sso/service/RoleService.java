package com.jianma.sso.service;

import com.jianma.sso.model.Role;

public interface RoleService {

	public int createRole(Role role);
    public int deleteRole(Long roleId);

    public int correlationPermissions(Long roleId, Long... permissionIds);
    public int uncorrelationPermissions(Long roleId, Long... permissionIds);
}
