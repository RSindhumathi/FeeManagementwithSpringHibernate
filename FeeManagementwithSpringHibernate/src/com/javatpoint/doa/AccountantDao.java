package com.javatpoint.doa;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.javatpoint.beans.AccountantBean;
import com.javatpoint.hibernate.HibernateSessionFactory;

public class AccountantDao {

	public static int save(AccountantBean bean) {

		Session session = HibernateSessionFactory.currentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			session.save(bean);
			tr.commit();
			return 1;
		} catch (Exception e) {
			throw e;
		} finally {
			System.out.println("successfully inserted into fee_accountant");
			HibernateSessionFactory.closeSession();

		}

	}

	public static boolean validate(String email, String password) {
		Session session = HibernateSessionFactory.currentSession();
		try {
			Criteria crit = session.createCriteria(
					AccountantBean.class);
			crit.add(Restrictions.eq("email", email));
			crit.add(Restrictions.eq("password", password));
			
			List<AccountantBean> allRecords = crit.list();
	
			if( allRecords!=null && allRecords.size() > 0 ){
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			HibernateSessionFactory.closeSession();
			

		}
		return false;
	}

	public static int update(AccountantBean bean) {
		Session session = HibernateSessionFactory.currentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			AccountantBean record = session.load(AccountantBean.class, bean.getId());
			record.setAddress(bean.getAddress());
			record.setContact(bean.getContact());
			record.setEmail(bean.getEmail());
			record.setName(bean.getName());
			record.setPassword(bean.getPassword());
			session.update(record);
			tr.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in update account");
			throw e;
		} finally {
			System.out.println("successfully updated into fee_accountant");
			HibernateSessionFactory.closeSession();

		}
		
	}

	public static int delete(int id) {
		Session session = HibernateSessionFactory.currentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			AccountantBean record = session.load(AccountantBean.class, id);
			
			session.delete(record);
			tr.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();

		}
		
		
	}

	public static List<AccountantBean> getAllRecords() {
		Session session = HibernateSessionFactory.currentSession();

		try {
			List<AccountantBean> allRecords = session.createCriteria(
					AccountantBean.class).list();
			return allRecords;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}

	}

	public static AccountantBean getRecordById(int id) {
		Session session = HibernateSessionFactory.currentSession();

		try {
			AccountantBean record = session.load(AccountantBean.class, id);
			return record;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
