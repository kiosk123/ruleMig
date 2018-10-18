package com.rulemig.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rulemig.util.ApplicationContext;
import com.rulemig.util.MessageUtil;



@WebListener
public class ApplicationInitializer implements ServletContextListener {

	private final static Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("################ application start ########################");
		logger.info("################ initialize MessageUtil");
		
		String rootPath = sce.getServletContext().getRealPath("/WEB-INF");
		try {
			MessageUtil.init(rootPath + "/message.properties");
			logger.info("################ MessageUtil init success");
		} catch (Exception e) {
			logger.error("############### MessageUtil init failed", e);
			logger.error("application shutdown");
			System.exit(1);
		}
		
		logger.info("################ initialize ApplicationContext");
		ApplicationContext context = ApplicationContext.getInstance();
		try {
			context.prepareControllerAnnotationObject("com.rulemig.controller");
		} catch (Exception e) {
			logger.error("################ ApplicationContext init failed", e);
			logger.error("application shutdown");
			System.exit(1);
		}
		logger.info("################ application init finished");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("################ application shutdown #####################");
		
	}
}
