package com.javatpoint.dao.spring;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.javatpoint.beans.AccountantBean;
import com.javatpoint.beans.AdminBean;

public class AdminDao extends BaseDao{

	
	public boolean validate(String email,String password){
		Session session = getCurrentSession();
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
			close(session);
			

		}
		return false;
		
	}
	
	public int save(AdminBean bean) {
		Session session = getCurrentSession();
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
			close(session);

		}
	}
}
