package com.lichmama.demo.core;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.lichmama.demo.common.constant.ActionStatus;

@ControllerAdvice
public class CustomeExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Object exceptionHandler(HttpServletRequest request, Exception e) {
		logger.error("统一异常处理", e);
		if (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").equals("XMLHttpRequest")) {
			return ActionStatus.error();
		}
		return new ModelAndView("redirect:/500");
	}
}
