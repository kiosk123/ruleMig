package com.rulemig.util;

import java.util.Hashtable;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rulemig.anno.Controller;
import com.rulemig.controller.ControllerAdapter;

public class ApplicationContext {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	private Hashtable<String, ControllerAdapter> controllerTable = new Hashtable<>();
	
	private ApplicationContext() {}
	
	public static ApplicationContext getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private static class LazyHolder {
		 private static final ApplicationContext INSTANCE = new ApplicationContext();  
	}
	
	public void prepareControllerAnnotationObject(String basePackage) throws Exception {
		String errorlog = "";
		Reflections reflector = new Reflections(basePackage);
		Set<Class<?>> set = reflector.getTypesAnnotatedWith(Controller.class);
		for (Class<?> clazz : set) {
			String key = clazz.getAnnotation(Controller.class).url();
			if (ApplicationConstants.EMPTY_URL.equals(key)) {
				errorlog = "class not set url";
				logger.error(errorlog);
				throw new Exception(errorlog); 
			}
			
			if (!key.endsWith(".do")) {
				key += ".do";
			}
			
			if (!ControllerAdapter.class.equals(clazz.getSuperclass())) {
				errorlog = "super class must be ControllerAdapter";
				logger.error(errorlog);
				throw new Exception(errorlog);
			}
			
			try {
				ControllerAdapter newInstance = (ControllerAdapter)clazz.getDeclaredConstructor().newInstance();
				controllerTable.put(key, newInstance);
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error(e.getMessage(), e);
				throw e;
			}	
		}
	}
	
	public ControllerAdapter getController(String url) {
		return controllerTable.get(url);
	}
}
