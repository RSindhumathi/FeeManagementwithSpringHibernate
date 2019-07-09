package com.javatpoint.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory {
	public static final SessionFactory sessionFactory;

	static {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		sessionFactory = meta.getSessionFactoryBuilder().build();
	}

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		// Open a new Session, if this thread has none yet
		if (s == null) {
			s = sessionFactory.openSession();
			// Store it in the ThreadLocal variable
			session.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		if (s != null)
			s.close();
		session.set(null);
	}
}
