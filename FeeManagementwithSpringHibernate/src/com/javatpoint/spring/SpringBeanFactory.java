package com.javatpoint.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javatpoint.beans.AccountantBean;
import com.javatpoint.beans.AdminBean;
import com.javatpoint.beans.StudentBean;
import com.javatpoint.dao.spring.AccountantDao;
import com.javatpoint.dao.spring.AdminDao;
import com.javatpoint.dao.spring.StudentDao;


public class SpringBeanFactory {

	static SpringBeanFactory instance = new SpringBeanFactory();
	private ApplicationContext appContext = null;
	
	public static SpringBeanFactory getInstance(){
		return instance;
	}
	public SpringBeanFactory(){
		appContext=new ClassPathXmlApplicationContext("Spring.xml"); 
	}
	
	public AdminBean getAdminBean(){
		return (AdminBean)appContext.getBean("adminBean");
	}

	public AdminDao getAdminDao(){
		return (AdminDao)appContext.getBean("adminDao"); 
	}
	
	public AccountantBean getAccountantBean(){
		return (AccountantBean)appContext.getBean("accountantBean");
	}
	
	public StudentBean getStudentBean(){
		return (StudentBean)appContext.getBean("studentBean");
	}
	
	public AccountantDao getAccountantDao(){
		return (AccountantDao)appContext.getBean("accountantDao"); 
	}
	
	public StudentDao getStudentDao(){
		return (StudentDao)appContext.getBean("studentDao"); 
	}
}
