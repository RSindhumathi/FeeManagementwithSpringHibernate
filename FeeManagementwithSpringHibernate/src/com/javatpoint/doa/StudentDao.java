package com.javatpoint.doa;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.javatpoint.beans.AccountantBean;
import com.javatpoint.beans.AdminBean;
import com.javatpoint.beans.StudentBean;
import com.javatpoint.hibernate.HibernateSessionFactory;

public class StudentDao {

	public static int save(StudentBean bean) {
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
			System.out.println("successfully inserted into fee_student");
			HibernateSessionFactory.closeSession();

		}

	
	}

	public static int update(StudentBean bean) {
		Session session = HibernateSessionFactory.currentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			StudentBean record = session.load(StudentBean.class, bean.getRollno());
			record.setAddress(bean.getAddress());
			record.setContact(bean.getContact());
			record.setEmail(bean.getEmail());
			record.setName(bean.getName());
			record.setCourse(bean.getCourse());
			record.setDue(bean.getDue());
			record.setFee(bean.getFee());
			record.setPaid(bean.getPaid());
			record.setSex(bean.getSex());
			session.update(record);
			tr.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in update student");
			throw e;
		} finally {
			System.out.println("successfully updated into fee_student");
			HibernateSessionFactory.closeSession();

		}
		
	}

	public static int delete(int rollno) {
		Session session = HibernateSessionFactory.currentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			StudentBean record = session.load(StudentBean.class, rollno);
			
			session.delete(record);
			tr.commit();
			return 1;	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
			System.out.println("deleted a row in fee_student by rollno");

		}
		
	}

	public static int deleteByName(String name) {

		Session session = HibernateSessionFactory.currentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			StudentBean record = session.load(StudentBean.class, name);
			
			session.delete(record);
			tr.commit();
			return 1;	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
			System.out.println("deleted a row in fee_student by name");

		}
	}

	public static List<StudentBean> getAllRecords() {

		Session session = HibernateSessionFactory.currentSession();

		try {
			List<StudentBean> allRecords = session.createCriteria(
					StudentBean.class).list();
			return allRecords;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
			System.out.println("retrive all records from fee_student");
		}
	}

	@SuppressWarnings("deprecation")
	public static List<StudentBean> getDues() {
		Session session = HibernateSessionFactory.currentSession();
		try {
	
			Criteria crit=session.createCriteria(StudentBean.class);
			
			
			crit.add(Restrictions.gt("due", 1));
			
			List<StudentBean> allRecords = crit.list();
	
			if( allRecords!=null && allRecords.size() > 0 ){
				return allRecords;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			HibernateSessionFactory.closeSession();
			

		}
		return null;
	}

	public static StudentBean getRecordByRollno(int rollno) {

		Session session = HibernateSessionFactory.currentSession();

		try {
			StudentBean record = session.load(StudentBean.class, rollno);
			System.out.println(record);
			return record;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	

}