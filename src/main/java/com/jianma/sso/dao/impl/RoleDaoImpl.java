package com.jianma.sso.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.sso.dao.RoleDao;
import com.jianma.sso.model.Permission;
import com.jianma.sso.model.PermissionRole;
import com.jianma.sso.model.Role;
import com.jianma.sso.model.User;
import com.jianma.sso.model.UserRole;

@Repository
@Component
@Qualifier(value = "roleDaoImpl")
public class RoleDaoImpl implements RoleDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Role r where r.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, roleId.intValue());
		query.executeUpdate();
	}

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {

		Session session = sessionFactory.getCurrentSession();
		Role role = new Role();
		role.setId(roleId.intValue());

		for (Long permissionId : permissionIds) {
			PermissionRole permissionRole = new PermissionRole();
			permissionRole.setRole(role);
			Permission permission = new Permission();
			permission.setId(permissionId.intValue());
			permissionRole.setPermission(permission);
			session.save(permissionRole);
		}
	}

	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		Session session = sessionFactory.getCurrentSession();
		Role role = new Role();
		role.setId(roleId.intValue());

		for (Long permissionId : permissionIds) {
			UserRole userRole = new UserRole();
			Role role = new Role();
			role.setId(roleId.intValue());
			userRole.setUser(user);
			userRole.setRole(role);
			session.delete(userRole);
		}

	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Role r where r.rolename = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, name);
		List<Role> list = query.list();
		if (list.size() > 0) {
			Optional<Role> role = Optional.ofNullable(list.get(0));
			return role;
		} else {
			return Optional.empty();
		}
	}

}
