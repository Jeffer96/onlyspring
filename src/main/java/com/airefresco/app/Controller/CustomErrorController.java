package com.airefresco.app.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String indexHandler() {
		return "/unauthorized";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
