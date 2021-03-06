package com.jianma.sso.dao;

import java.util.List;
import java.util.Optional;

import com.jianma.sso.model.Role;

public interface RoleDao {
	
	public void createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
    
    public Optional<Role> getRoleByName(String name);
    
    public void updateRole(Role role);
    
    public List<Role> getDataByPage(int limit, int offset);
    
    public int countRole();
}
