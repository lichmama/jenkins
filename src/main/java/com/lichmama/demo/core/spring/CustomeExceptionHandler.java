package com.lichmama.demo.core.spring;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.lichmama.demo.common.util.StringUtil;

@ControllerAdvice
public class CustomeExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler
	public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		logger.error("统一异常处理：", ex);
		String requestType = request.getHeader("X-Requested-With");
		if (StringUtil.isEqual("XMLHttpRequest", requestType)) {
			response.setStatus(500);
			response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().write("{\"status\":{\"code\":500,\"text\":\"internal server error\"}}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return new ModelAndView("500");
	}
}
