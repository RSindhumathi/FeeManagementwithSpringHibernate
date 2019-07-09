package com.javatpoint.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.javatpoint.dao.spring.StudentDao;
import com.javatpoint.spring.SpringBeanFactory;
@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String srollno=request.getParameter("rollno");
		int rollno=Integer.parseInt(srollno);
		StudentDao dao =SpringBeanFactory.getInstance().getStudentDao();
		dao.delete(rollno);
		//StudentDao.delete(rollno);
		response.sendRedirect("ViewStudent");
	}
}
