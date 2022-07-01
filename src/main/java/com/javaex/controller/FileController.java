package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
public class FileController {
   @Autowired
   private FileService fileService;
   
   @RequestMapping(value="/fileupload/form", method= {RequestMethod.GET,RequestMethod.POST})
   public String form() {
      System.out.println("FileController>form");
      return "fileupload/form";
   }
   
   @RequestMapping(value="/fileupload/upload", method= {RequestMethod.GET,RequestMethod.POST})
   public String upload(@RequestParam("file") MultipartFile file, Model model) { //모든 파일을 다 담을수잇는 (pom세팅)
      System.out.println("FileController>upload"); //MultipartFile 은 파일첨부를 해도 안해도 있는척함.
      System.out.println(file.getOriginalFilename());//그래서 오리지날파일네임을 사용해서 원래 파일이름 
      
      String saveName = fileService.save(file);
      System.out.println("saaaaaaaaaaaav"+saveName);
      model.addAttribute("saveName", saveName);
      
      return "fileupload/result";
   }
   
   
   
   
}