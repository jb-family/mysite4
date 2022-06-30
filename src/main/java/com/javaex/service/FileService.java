package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileDao;
import com.javaex.vo.FileVo;

@Service
public class FileService { 
	
	@Autowired
	FileDao fileDao;
   
   //파일 하드디스크 저장, 파일 정보(DB저장용) 추출
   public String save(MultipartFile file) {
      System.out.println("FileService > save()");
      
      //(1)파일 정보 (DB저장) 추출 저장
      
      //디렉토리 (저장할 경로)
      String saveDir = "C:\\javaStudy\\upload";
      
      
      //오리자날파일명
      String orgName = file.getOriginalFilename();
      
      //확장자
      String exName = orgName.substring(orgName.lastIndexOf("."));

      //랜덤아이디 생성방법	UUID.randomUUID().toString();
      //시간 생성방법	System.currentTimeMillis();
      
      //저장 파일명
      String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName; 

      //파일경로(디렉토리+저장파일명)
      String filePath = saveDir + "\\" + saveName;
      
      //파일사이즈
      Long fileSize = file.getSize();
      
      FileVo fileVo = new FileVo(orgName, saveName, filePath, fileSize);
      System.out.println(fileVo);
      
      
      // dao생성 db생성 후 테이블에 저장하기 (과제)
      int count = fileDao.save(fileVo);
      
      
      //(2)파일저장
      try {
		byte[] fileData = file.getBytes();
		
		OutputStream os = new FileOutputStream(filePath);
		BufferedOutputStream bos = new BufferedOutputStream(os);
		
		bos.write(fileData);
		bos.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
      
      
      return saveName;
      
   }
   
}