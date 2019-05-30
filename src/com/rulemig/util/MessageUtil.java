package com.rulemig.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageUtil {
	private final static Logger logger = LoggerFactory.getLogger(MessageUtil.class);
	private final static Properties messageProps = new Properties();
	
	public static void init(String propsPath) throws Exception {
		Path path = Paths.get(propsPath);
		File file = path.toFile();
		if (!file.isFile() || !file.exists()) {
			String errorlog = "message.properties file is not found";
			logger.error(errorlog);
			throw new IOException(errorlog);
		}
		
		Properties props = new Properties();
		try(FileInputStream in = new FileInputStream(propsPath)) {
			props.load(in);
		} catch (IOException e) {
			logger.error("While reading message.properties file, error occured", e);
			throw e;
		}
		
		messageProps.putAll(props);
	}
	
	public static String getMessage(String key) {
		return messageProps.getProperty(key);
	}
	
	public static String getMessage(String key, String ... param) {
		String message = messageProps.getProperty(key);
		String ret = "";
		if (message != null) {
			MessageFormat mf = new MessageFormat(message);
			try {
				ret = mf.format(param);
			} catch(IllegalArgumentException e) {
				ret = message;
			}
		}
		return ret;
	}
}
