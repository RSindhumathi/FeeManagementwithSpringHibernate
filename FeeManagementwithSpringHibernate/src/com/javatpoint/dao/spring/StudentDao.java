package com.javatpoint.dao.spring;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.javatpoint.beans.StudentBean;

public class StudentDao extends BaseDao {

	@Autowired
    private SessionFactory sessionFactory;
	public  int save(StudentBean bean) {
		//Session session = sessionFactory.getCurrentSession();
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
			System.out.println("successfully inserted into fee_student");
		//	sessionFactory.close();
			close(session);
		}

	
	}

	public  int update(StudentBean bean) {
		Session session = getCurrentSession();
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
			close(session);

		}
		
	}

	public  int delete(int rollno) {
		Session session =getCurrentSession();
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
			close(session);
			System.out.println("deleted a row in fee_student by rollno");

		}
		
	}

	public  int deleteByName(String name) {

		Session session = getCurrentSession();
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
			close(session);
			System.out.println("deleted a row in fee_student by name");

		}
	}

	public  List<StudentBean> getAllRecords() {

		Session session = getCurrentSession();

		try {
			List<StudentBean> allRecords = session.createCriteria(
					StudentBean.class).list();
			return allRecords;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			close(session);
			System.out.println("retrive all records from fee_student");
		}
	}

	@SuppressWarnings("deprecation")
	public  List<StudentBean> getDues() {
		Session session =getCurrentSession();
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
			close(session);
			

		}
		return null;
	}

	public  StudentBean getRecordByRollno(int rollno) {

		Session session = getCurrentSession();

		try {
			StudentBean record = session.load(StudentBean.class, rollno);
			System.out.println(record);
			return record;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			close(session);
		}
	}
	

}