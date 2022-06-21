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

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;


@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//리스트 불러오기 (검색기능 +)
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
		System.out.println("BoardController > list");
		
		List<BoardVo> bList = boardService.selectList(keyword);
		System.out.println(bList);
		model.addAttribute("bList",bList);
		
		return "board/list";
	}
	
	//글 작성폼
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardController > writeForm");
		
		
		return "board/writeForm";
	}
	
	//작성 글 추가
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController > write");
		
		boardService.insert(boardVo);
		return "redirect:/board/list";
	}

	//유저가 작성한 글 보기
	@RequestMapping(value="/read/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @PathVariable("no") int no) {
		System.out.println("BoardController > read");
		
		//조회수 증가(조회수 업데이트)
		boardService.hitUp(no);
		
		//작성한 글 보기
		BoardVo info = boardService.getUser(no);
		model.addAttribute("info", info);
		
		return "board/read";
	}
	
	//작성한 글 삭제
	@RequestMapping(value="/delete/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("no") int no, HttpSession session) {
		System.out.println("BoardController > delete");
		
		//세션 가져오기 (userNo)
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		
		//no를 넘겨서 유저 정보 가져오기
		BoardVo getUser = boardService.getUser(no);
			
		if(getUser.getUserNo() != userNo) {
			System.out.println("삭제할 수 없습니다.");
			return "redirect:/board/list";
		}else {
			boardService.delete(no);
			System.out.println("삭제되었습니다.");
			return "redirect:/board/list";
		}
		
	}
	
	//글 수정폼
	@RequestMapping(value="/modifyForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	
	public String modifyForm(@PathVariable("no") int no, Model model) {
		System.out.println("BoardController > modifyForm");
		
		BoardVo userModify = boardService.getUser(no);
		model.addAttribute("userModify", userModify);
		System.out.println("modmodmodmo"+userModify);
		return "board/modifyForm";
	}
	
	//작성한 글 수정
	@RequestMapping(value="/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController > modify");
		
		boardService.update(boardVo);
		
		return "redirect:/board/list";
	}
	
	
	
	
}