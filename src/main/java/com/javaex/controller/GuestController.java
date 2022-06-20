package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
public class GuestController {
	
	@Autowired
	GuestService guestService;
	
	//리스트
	@RequestMapping(value="/guestbook/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestController > addList");
		
		//리스트 가져오기
		List<GuestVo> gList = guestService.selectList();
		System.out.println(gList);
		
		//model에 저장하기
		model.addAttribute("gList", gList);
		
		return "guestbook/addList";
	}
	
	//추가
	@RequestMapping(value="/guestbook/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestController > add");
		
		guestService.insert(guestVo);
		return "redirect:/guestbook/addList";
	}
	
	//삭제폼
	@RequestMapping(value="/guestbook/deleteForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(Model model, @PathVariable ("no") int no) {
		System.out.println("GuestController > deleteForm");
		
		//모델에 no값 저장하기.
		model.addAttribute("no",no);
		return "guestbook/deleteForm";
	}
	
	@RequestMapping(value="/guestbook/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam ("no") int no, @RequestParam ("password") String password) {
		System.out.println("GuestController > delete");
		//no값 입력해서 유저 정보 가져오기 
		GuestVo user = guestService.getUser(no);
		
		System.out.println("user정보"+user);
		System.out.println("입력한비밀번호"+password);
		if(user.getPassword().equals(password)) {
			guestService.delete(user);
			System.out.println("삭제되었습니다.");
			return "redirect:/guestbook/addList";
		}else {
			System.out.println("패스워드가 틀렸습니다.");
			return "redirect:/guestbook/addList";
		}
		
		
		
	}
	
	
}
