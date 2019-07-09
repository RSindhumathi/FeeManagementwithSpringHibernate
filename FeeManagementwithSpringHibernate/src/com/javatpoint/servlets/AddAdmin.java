package com.javatpoint.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddAdmin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext appCon=new ClassPathXmlApplicationContext("Bean.xml"); 
	}

}
