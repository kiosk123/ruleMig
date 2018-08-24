package com.rulemig.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rulemig.anno.Controller;
import com.rulemig.util.MessageUtil;

@Controller(url = "/hello")
public class HelloController extends ControllerAdapter {
	
	private final static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		logger.info(MessageUtil.getMessage("hello.message", "Hi", "World"));
		forward("hello.jsp", req, resp);
	}
}
