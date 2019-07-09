package com.javatpoint.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javatpoint.beans.StudentBean;
import com.javatpoint.dao.spring.StudentDao;
import com.javatpoint.spring.SpringBeanFactory;
@WebServlet("/EditStudent")
public class EditStudent extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int rollno=Integer.parseInt(request.getParameter("rollno"));
		String name=request.getParameter("name");
	 	String email=request.getParameter("email");
		String sex=request.getParameter("sex");
		String course=request.getParameter("course");
		int fee=Integer.parseInt(request.getParameter("fee"));
		int paid=Integer.parseInt(request.getParameter("paid"));
		int due=Integer.parseInt(request.getParameter("due"));
		String address=request.getParameter("address");
		String contact=request.getParameter("contact");
		
		//StudentBean bean=new StudentBean(rollno,name, email, sex, course, fee, paid, due, address, contact);
		//int status=StudentDao.update(bean);
		StudentBean bean = SpringBeanFactory.getInstance().getStudentBean();
		bean.setRollno(rollno);
		bean.setName(name);
		bean.setEmail(email);
		bean.setSex(sex);
		bean.setCourse(course);
		bean.setFee(fee);
		bean.setPaid(paid);
		bean.setDue(due);
		bean.setAddress(address);
		bean.setContact(contact);
		StudentDao dao =SpringBeanFactory.getInstance().getStudentDao();
		int status=dao.update(bean);
		response.sendRedirect("ViewStudent");
		
		out.close();
	}

}
