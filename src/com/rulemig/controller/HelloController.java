package com.rulemig.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulemig.anno.Controller;

@Controller(url = "/hello")
public class HelloController extends ControllerAdapter {
	
	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		forward("hello.jsp", req, resp);
	}
}
