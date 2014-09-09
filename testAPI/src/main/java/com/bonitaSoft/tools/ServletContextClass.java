package com.bonitaSoft.tools;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.bonitaSoft.tools.LocalStorage;

public class ServletContextClass implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//add to ServletContext
		LocalStorage localStorage = new LocalStorage();
		
		localStorage.setStorage("hello", "Hello World");
		
		sce.getServletContext().setAttribute("localStorage", localStorage);
	}


	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
}
