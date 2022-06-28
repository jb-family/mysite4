package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestService guestService;
	
	//방명록 첫페이지(등록폼+리스트)
	@RequestMapping(value ="/api/guestbook/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList() {
		System.out.println("ApiGuestbookController > addList()");
		return "apiGuestbook/addList";
	}
	
	
	//방명록 리스트 데이터만 보내주는 요청
	@ResponseBody
	@RequestMapping(value="/api/guestbook/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestVo> list() {
		System.out.println("ApiGuestbookController > list()");
		
		List<GuestVo> gList = guestService.selectList();
		System.out.println(gList);
		
		return gList;
	}
	
	//방명록 저장
	@ResponseBody
	@RequestMapping(value="/api/guestbook/add")
	public GuestVo add(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestService > add()");
		GuestVo gVo = guestService.addGuest(guestVo);
		System.out.println(gVo);
		return gVo;
	}
	
}
