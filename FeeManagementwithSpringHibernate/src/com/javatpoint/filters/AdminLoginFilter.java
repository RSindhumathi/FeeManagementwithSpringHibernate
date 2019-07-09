package com.javatpoint.filters;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.javatpoint.servlets.AdminLogin;
@WebFilter(urlPatterns= {"/*"})
public class AdminLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	static Logger log = Logger.getLogger(AdminLogin.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		log.info("TimeStamp : "+timestamp + "-IP Address "+req.getRemoteAddr());
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
