package com.javatpoint.dao.spring;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.javatpoint.beans.AccountantBean;

public class AccountantDao extends BaseDao {

	@Autowired
    private SessionFactory sessionFactory;
	public int save(AccountantBean bean) {
		Session session = getCurrentSession();
		//Session session = getCurrentSe;
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
			//session.close();
			close(session);

		}

	}

	public  boolean validate(String email, String password) {
		Session session = getCurrentSession();
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
			close(session);;
			

		}
		return false;
	}

	public  int update(AccountantBean bean) {
		Session session = getCurrentSession();;
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
			close(session);;

		}
		
	}

	public  int delete(int id) {
		Session session = getCurrentSession();;
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
			close(session);

		}
		
		
	}

	public  List<AccountantBean> getAllRecords() {
		Session session = getCurrentSession();;

		try {
			List<AccountantBean> allRecords = session.createCriteria(
					AccountantBean.class).list();
			return allRecords;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			close(session);
		}

	}

	public  AccountantBean getRecordById(int id) {
		Session session = getCurrentSession();;

		try {
			AccountantBean record = session.load(AccountantBean.class, id);
			return record;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			close(session);
		}
	}
}
