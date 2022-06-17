package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	
	@RequestMapping(value="/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController > joinForm()");
		return "user/joinForm";
	}
	
	@RequestMapping(value="/joinOk", method = {RequestMethod.GET, RequestMethod.POST}) 
	public String joinOk() {
		System.out.println("UserController > joinOk()");
		
		return "user/joinOk";
	}
	
	@RequestMapping(value="/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController > loginForm()");
		
		return "user/loginForm";
	}
	
	@RequestMapping(value="/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm() {
		System.out.println("UserController > modifyForm()");
		
		return "user/modifyForm";
	}
}
