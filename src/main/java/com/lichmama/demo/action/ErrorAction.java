package com.lichmama.demo.action;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorAction {

	@RequestMapping("/404")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String fileNotFound() {
		return "404";
	}
	
	@RequestMapping("/500")
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String internalServerError() {
		return "500";
	}
}