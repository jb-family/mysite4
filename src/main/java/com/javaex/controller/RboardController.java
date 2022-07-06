package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Controller
public class RboardController {
	@Autowired
	RboardService rBoardService;
	
	//리스트 가져오기
	@RequestMapping(value="/rboard/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
		System.out.println("RboardController > list()");
		List<RboardVo> rList = rBoardService.list(keyword);
		
		model.addAttribute("rList", rList);
		return "/rboard/list";
	}
	
	//댓글작성폼
	@RequestMapping(value="/rboard/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("RboardController > writeForm()");
		
		return "/rboard/writeForm";
	}
	
	//글 추가
	@RequestMapping(value="/rboard/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute RboardVo rBoardVo, HttpSession session) {
		System.out.println("RboardController > write()");
		
		rBoardService.write(rBoardVo, session);
		return "redirect:/rboard/list";
	}
	
	//글 보기
	@RequestMapping(value="/rboard/read/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @PathVariable("no") int no) {
		System.out.println("RboardController > read()");
		
		RboardVo user = rBoardService.read(no);
		model.addAttribute("user", user);
		
		return "/rboard/read";
	}
	
	//글 삭제
	@RequestMapping(value="/rboard/delete/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("no") int no) {
		System.out.println("RboardController > delete()");
		
		int count = rBoardService.delete(no);
		System.out.println(count);
		return "redirect:/rboard/list";
	}
	
	//글 수정폼
	@RequestMapping(value="/rboard/modifyForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@PathVariable("no") int no, Model model) {
		System.out.println("RboardController > modifyForm()");
		
		RboardVo user = rBoardService.modifyUser(no);
		model.addAttribute("user", user);
		return "/rboard/modifyForm";
	}
	
	//글 수정
	@RequestMapping(value="/rboard/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute RboardVo rBoardVo) {
		System.out.println("RboardController > modify()");
		rBoardService.modifyUser(rBoardVo);
		return "redirect:/rboard/list";
	}
	
	
	//댓글 작성폼
	@RequestMapping(value="/rboard/commentForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String commentForm(@PathVariable("no") int no, Model model) {
		System.out.println("RboardController > commentForm()");
		
		RboardVo user = rBoardService.commentUser(no);
		model.addAttribute("user", user);
		
		return "/rboard/commentForm";
	}
	
	//댓글 작성
	@RequestMapping(value="/rboard/comment", method = {RequestMethod.GET, RequestMethod.POST})
	public String commentInsert(@ModelAttribute RboardVo rBoardVo) {
		System.out.println("RboardController > comment()");
		rBoardService.commentInsert(rBoardVo);
		
		return "redirect:/rboard/list";
	}
	
}
