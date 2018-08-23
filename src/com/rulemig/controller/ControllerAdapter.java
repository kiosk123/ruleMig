package com.rulemig.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ControllerAdapter implements ControllerFacade {
	
	protected final static String URL_PREFIX = "/WEB-INF/view/";
	
	@Override
	public void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	@Override
	public void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}
	@Override
	public void put(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}
	@Override
	public void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}
	
	public void forward(String url, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher(URL_PREFIX + url).forward(req, resp);
	}
	
	public void redirect(String url, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.sendRedirect(req.getContextPath() + "/" + url);
	}
}
