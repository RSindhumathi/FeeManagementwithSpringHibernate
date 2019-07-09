package com.javatpoint.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javatpoint.beans.AdminBean;
import com.javatpoint.dao.spring.AdminDao;
import com.javatpoint.spring.SpringBeanFactory;

/**
 * Servlet implementation class AddAdminForm
 */
@WebServlet("/AddAdminForm")
public class AddAdminForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	 	String email=request.getParameter("email");
	 	String psw = request.getParameter("password");
	 	
	 	/*
	 	ApplicationContext appCon=new ClassPathXmlApplicationContext("Bean.xml"); 
	 	AdminBean factory=(AdminBean)appCon.getBean("adminBean"); 
	 	AdminDao dao=(AdminDao)appCon.getBean("adminDao"); 
	 	*/
	 	AdminBean factory = SpringBeanFactory.getInstance().getAdminBean();
	 	System.out.println(factory);
	 	factory.setEmail(email);
	 	factory.setPassword(psw);
	 	
	 	AdminDao dao = SpringBeanFactory.getInstance().getAdminDao();
	 	System.out.println(dao);
	 	int status = dao.save(factory);
	 	
	// AdminBean bean = new AdminBean(email,psw);
	// int status = AdminDao.save(bean);
	 out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Add Admin</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container'>");
		if(status > 0){
		out.println("admin is added successfully!");}
		else{
			out.println("Admin is not inserted ");	
		}
		
		request.getRequestDispatcher("index.html").include(request, response);
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
