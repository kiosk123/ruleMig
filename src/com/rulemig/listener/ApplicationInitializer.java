package com.rulemig.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rulemig.util.ApplicationContext;



@WebListener
public class ApplicationInitializer implements ServletContextListener {

	private final static Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("################ application start ########################");
		ApplicationContext context = ApplicationContext.getInstance();
		try {
			context.prepareControllerAnnotationObject("com.rulemig.controller");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("################ application shutdown #####################");
		
	}
}
