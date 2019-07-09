package com.javatpoint.doa;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.javatpoint.beans.AccountantBean;
import com.javatpoint.beans.AdminBean;
import com.javatpoint.hibernate.HibernateSessionFactory;

public class AdminDao {

	public boolean validate(String email,String password){
		Session session = HibernateSessionFactory.currentSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(AdminBean.class);
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
	
	public int save(AdminBean bean) {
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
			System.out.println("successfully inserted into admin_account");
			HibernateSessionFactory.closeSession();

		}
	}
}
