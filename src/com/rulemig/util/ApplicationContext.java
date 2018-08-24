package com.rulemig.util;

import java.util.Hashtable;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rulemig.anno.Controller;
import com.rulemig.controller.ControllerAdapter;
import com.rulemig.error.ErrorCodes;
import com.rulemig.error.RuleMigException;

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
	
	public void prepareControllerAnnotationObject(String basePackage) throws RuleMigException {
		Reflections reflector = new Reflections(basePackage);
		Set<Class<?>> set = reflector.getTypesAnnotatedWith(Controller.class);
		for (Class<?> clazz : set) {
			String key = clazz.getAnnotation(Controller.class).url();
			if (ApplicationConstants.EMPTY_URL.equals(key)) {
				throw new RuleMigException(ErrorCodes.FAIL_APP_START, "class not set url"); 
			}
			
			if (!key.endsWith(".do")) {
				key += ".do";
			}
			
			if (!ControllerAdapter.class.equals(clazz.getSuperclass())) {
				throw new RuleMigException(ErrorCodes.FAIL_APP_START, "super class must Adapter");
			}
			
			try {
				ControllerAdapter newInstance = (ControllerAdapter)clazz.newInstance();
				controllerTable.put(key, newInstance);
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuleMigException(ErrorCodes.FAIL_APP_START, e.getMessage(), e);
			}	
		}
	}
	
	public ControllerAdapter getController(String url) {
		return controllerTable.get(url);
	}
}
