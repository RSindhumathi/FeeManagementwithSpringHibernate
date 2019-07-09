package com.javatpoint.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javatpoint.beans.AccountantBean;
import com.javatpoint.dao.spring.AccountantDao;
import com.javatpoint.spring.SpringBeanFactory;
@WebServlet("/EditAccountant")
public class EditAccountant extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
	 	String email=request.getParameter("email");
		String password=request.getParameter("password");
		String address=request.getParameter("address");
		String contact=request.getParameter("contact");
		
	//	AccountantBean bean=new AccountantBean(id,name, email, password, address, contact);
	//	AccountantDao.update(bean);
		
		AccountantBean bean=SpringBeanFactory.getInstance().getAccountantBean();
		bean.setId(id);
		bean.setName(name);
		bean.setEmail(email);
		bean.setPassword(password);
		bean.setAddress(address);
		bean.setContact(contact);
		AccountantDao dao = SpringBeanFactory.getInstance().getAccountantDao();
		dao.update(bean);
		
	
		response.sendRedirect("ViewAccountant");
		
		out.close();
	}

}
