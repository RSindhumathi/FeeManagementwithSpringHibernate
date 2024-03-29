package com.javatpoint.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javatpoint.beans.AccountantBean;
import com.javatpoint.beans.AdminBean;
import com.javatpoint.dao.spring.AccountantDao;
import com.javatpoint.spring.SpringBeanFactory;


@WebServlet("/AddAccountant")
public class AddAccountant extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Accountant Added</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		String name=request.getParameter("name");
	 	String email=request.getParameter("email");
		String password=request.getParameter("password");
		String address=request.getParameter("address");
		String contact=request.getParameter("contact");
		//AccountantBean bean=new AccountantBean(name, email, password, address, contact);
		//int status=AccountantDao.save(bean);
		
		
		AccountantBean bean=SpringBeanFactory.getInstance().getAccountantBean();
		bean.setName(name);
		bean.setEmail(email);
		bean.setPassword(password);
		bean.setAddress(address);
		bean.setContact(contact);
		AccountantDao dao = SpringBeanFactory.getInstance().getAccountantDao();
		int status=dao.save(bean);
		
		if(status > 0 ) {
		out.print("<h1>Add Accountant Form</h1>");
		out.println("<p>Accountant is added successfully!</p>");
		request.getRequestDispatcher("AddAccountantForm.html").include(request, response);}
		else{
			out.print("<h1>Add Accountant Form</h1>");
			out.println("<p>Accountant is  not added successfully!</p>");
			request.getRequestDispatcher("AddAccountantForm.html").include(request, response);
		}
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
