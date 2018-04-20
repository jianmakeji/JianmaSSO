package com.jianma.sso.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.sso.dao.PermissionDao;
import com.jianma.sso.model.Permission;

@Repository
@Component
@Qualifier(value = "permissionDaoImpl")
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createPermission(Permission permission) {
		sessionFactory.getCurrentSession().save(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Permission p where p.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, permissionId.intValue());
		query.executeUpdate();
				
	}

	@Override
	public void updatePermission(Permission permission) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update Permission p set p.permissionName = ?, p.createtime = ?  where p.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, permission.getPermissionName());
		query.setParameter(1, new Date());
		query.setParameter(2, permission.getId());
		query.executeUpdate();
	}

	@Override
	public List<Permission> getDataByPage(int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Permission p ";
		Query query = session.createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(offset);
		return query.list();
	}

	@Override
	public int countPermissions() {
		Session session = sessionFactory.getCurrentSession();
		final String hql = " select count(p) from Permission p"; 
        final Query query = session.createQuery(hql); 
        long count = (Long)query.uniqueResult();
        return (int)count;
	}

}
