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
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;


@Controller
public class GalleryController {
	
	@Autowired
	GalleryService galleryService;
	
	//갤러리 리스트
	@RequestMapping(value="gallery/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String gallery(Model model) {
		System.out.println("ApiUserController > gallery()");
		
		List<GalleryVo> gList = galleryService.selectList();
		
		model.addAttribute("gList", gList);
		return "gallery/list";
	}
	
	//갤러리 추가
	@RequestMapping(value="gallery/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@RequestParam("file") MultipartFile file, HttpSession session ,@ModelAttribute GalleryVo galleryVo, Model model) {
		System.out.println("ApiUserController > add()");
		
		String saveName = galleryService.save(file, galleryVo, session);
		model.addAttribute("saveName", saveName);
		return "redirect:/gallery/list";
	}
}
