package com.jianma.sso.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jianma.sso.model.User;

public interface UserDao {

	public void createUser(User user);
    public void deleteUser(Long userId);

    public void deleteUserByEmail(String email);
    
    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);

    public Optional<User> findOne(Long userId);

    public Optional<User> findByEmail(String email);

    public Optional<User> checkAuthc(String email);
    
    public Set<String> findRoles(String username);

    public Set<String> findPermissions(String username);
    
    public void updateValidSign(String email, int validValue);
        
    public void updatePwd(String email, String password);
        
    public List<User> findUserListByPage(int offset, int limit);
    
    public int getCountUser();
}
