package com.rulemig.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rulemig.controller.ControllerAdapter;
import com.rulemig.util.ApplicationContext;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext context = ApplicationContext.getInstance();
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO 로그인 여부 처리...
		String method = req.getMethod();
		logger.info("[" + method + "] " + req.getServletPath());
		ControllerAdapter controller = context.getController(req.getServletPath());
		if (controller == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		logger.info("proceed request");
		
		try {
			if(method.equalsIgnoreCase("GET")) {
				controller.get(req, resp);
			} else if(method.equalsIgnoreCase("POST")) {
				controller.post(req, resp);
			} else if(method.equalsIgnoreCase("PUT")) {
				controller.put(req, resp);
			} else if(method.equalsIgnoreCase("DELETE")) {
				controller.delete(req, resp);
			} else {
				resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} catch (Exception e) {
			logger.error("While processing HTTP request, error occurred", e);
			throw new ServletException(e);
		}
	}
	
}
