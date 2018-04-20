package com.jianma.sso.service;

import java.util.Optional;
import java.util.Set;

import com.jianma.sso.model.PageModel;
import com.jianma.sso.model.User;


public interface UserService {
	
	public int createUser(User user);

    public int deleteUser(Long userId);

    public int correlationRoles(Long userId, Long... roleIds);
    public int uncorrelationRoles(Long userId, Long... roleIds);

    public Optional<User> findOne(Long userId);

    public Optional<User> findByEmail(String username);

    public Set<String> findRoles(String username);

    public Set<String> findPermissions(String username);
        
    public int updatePwd(String email, String password);
        
    public int checkAuthc(String email, String password);
    
    public PageModel getUserByPage(int offset, int limit);
    
    public int updateValidSign(String email, int validValue);
}
