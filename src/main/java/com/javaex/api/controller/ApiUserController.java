package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.FileService;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class ApiUserController {
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value="api/user/userCheck", method= {RequestMethod.GET, RequestMethod.POST})
	public String check(@ModelAttribute UserVo userVo) {
		System.out.println("ApiUserController > check()");
		
		String idCheck = userService.check(userVo);
		
		return idCheck;
	}
	

	
	
	
	
	
	
	
	
	
	
}
