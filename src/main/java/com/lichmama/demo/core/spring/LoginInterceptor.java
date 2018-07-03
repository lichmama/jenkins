package com.lichmama.demo.core.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lichmama.demo.common.util.StringUtil;
import com.lichmama.demo.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	private String SESSION_KEY = "loginUser";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("handler: " + handler);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SESSION_KEY);
		if (user == null) {
			logger.info("访问拒绝：用户未登录！");
			if (isAjaxRequest(request)) {
				response.getWriter().write("{\"status\": {\"code\": \"401\", \"text\": \"access rejected, user not logged in\"}}");
			} else {
				response.sendRedirect("/index");
			}
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if (StringUtil.isNotEmpty(requestType)) {
			if ("XMLHttpRequest".equals(requestType))
				return true;
		}
		return false;
	}
}
