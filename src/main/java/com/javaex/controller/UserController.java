package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//회원정보 저장(회원가입폼)
	@RequestMapping(value="/user/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController > joinForm()");
		return "user/joinForm";
	}
	
	//회원정보 저장(회원가입)
	@RequestMapping(value="/user/joinOk", method = {RequestMethod.GET, RequestMethod.POST}) 
	public String joinOk(@ModelAttribute UserVo userVo) {
		System.out.println("UserController > joinOk()");
		
		userService.join(userVo);
		
		return "user/joinOk";
	}
	
	//회원정보 저장(로그인폼)
	@RequestMapping(value="/user/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController > loginForm()");
		
		return "user/loginForm";
	}
	
	//회원정보 저장(로그인)
	@RequestMapping(value="/user/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController > login()");
		
		UserVo authUser = userService.login(userVo);
		
		//세션에 저장
		if (authUser != null) {
			System.out.println("로그인성공");
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		} else {
			System.out.println("로그인실패");
			return "redirect:/user/loginForm?result=fail";
		}
		
	}
	
	//회원정보 삭제(로그아웃)
	@RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	
	//회원정보 수정(수정폼)
	@RequestMapping(value="/user/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session) {
		System.out.println("UserController > modifyForm()");
		
		//세션가져오기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//no값으로 세션 값 불러오기
		int no = authUser.getNo();
		UserVo userModify = userService.getUserModify(no); 
		
		//가져온정보 다른 세션에 저장
		session.setAttribute("user", userModify);
		
		return "user/modifyForm";
	}
	
	//회원정보 수정(수정)
	@RequestMapping(value="/user/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController > modify()");
		
		//세션불러오기
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		//userVo에 세션에 있는 no값 넣기
		userVo.setNo(authUser.getNo());
		
		//userVo 업데이트 후 authUser에 다시 세션값 저장
		userService.modify(userVo);
		session.setAttribute("authUser", userVo);
		
		System.out.println("업데이트된정보"+userVo);
		
		return "redirect:/main";
	}
	
	
	
	
	
}
