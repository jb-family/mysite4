package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;


@Controller
public class GalleryController {
	
	@Autowired
	GalleryService galleryService;
	
	//사진 리스트
	@RequestMapping(value="/gallery/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GalleryController > addList()");
		
		List<GalleryVo> gList = galleryService.selectList();
		
		model.addAttribute("gList", gList);
		return "/gallery/list";
	}
	
	
	//사진 추가
	@RequestMapping(value="/gallery/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@RequestParam("file") MultipartFile file, HttpSession session ,@ModelAttribute GalleryVo galleryVo, Model model) {
		System.out.println("GalleryController > add()");
		
		String saveName = galleryService.save(file, galleryVo, session);
		model.addAttribute("saveName", saveName);
		return "redirect:/gallery/list";
	}
	
	//사진 주소 가져오기
	@ResponseBody
	@RequestMapping(value="/gallery/show", method = {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo show(@ModelAttribute GalleryVo galleryVo) {
		System.out.println("GalleryController > show()");

		
		GalleryVo fileInfo = galleryService.show(galleryVo);
		System.out.println(fileInfo);
		
		return fileInfo;
	}
	
	//사진 삭제
	@ResponseBody
	@RequestMapping(value="/gallery/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public int delete(@ModelAttribute GalleryVo galleryVo) {
		System.out.println("GalleryController > delete()");
		int count = galleryService.delete(galleryVo);
		System.out.println("삭제된 건 수 :" + count);
		
		return count;
	}
	
	
	
	
	
	
	
	
}
