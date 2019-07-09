package com.javatpoint.dao.spring;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		return session;
	}
	
	public void close(Session session){
		session.close();
	}
}
