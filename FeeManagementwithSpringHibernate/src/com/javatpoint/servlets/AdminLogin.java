package com.javatpoint.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.javatpoint.doa.AdminDao;
import com.javatpoint.spring.SpringBeanFactory;
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(AdminLogin.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.warn("sadfsdgsdgdfgsd");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Admin Panel</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navadmin.html").include(request, response);
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		//spring bean factory used o create a objects.
		boolean status = SpringBeanFactory.getInstance().getAdminDao().validate(email, password);
		//boolean status=AdminDao.validate(email, password);
		if(status){
			HttpSession session=request.getSession();
			session.setAttribute("admin","true");
			request.getRequestDispatcher("adminhome.html").include(request, response);
		}else{
			out.println("<p>Sorry, username or password error!</p>");
			request.getRequestDispatcher("AdminLoginForm.html").include(request, response);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
 
}
