package com.rulemig.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerFacade {
	public void get(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public void post(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public void put(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
