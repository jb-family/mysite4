package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Service
public class GalleryService {
	
	@Autowired
	GalleryDao galleryDao;
	
	//Vo생성
	GalleryVo galleryVo = new GalleryVo();
	
	
	//리스트 불러오기
	public List<GalleryVo> selectList() {
		System.out.println("GalleryService > selectList()");
		
		List<GalleryVo> gList = galleryDao.selectList();
		return gList;
	}
	
	//저장
	public String save(MultipartFile file, GalleryVo galleryVo ,HttpSession session) {
		System.out.println("GalleryService > save()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		int userNo = authUser.getNo();
		galleryVo.setUserNo(userNo);
		
		
		//저장할 경로 (디렉토리)
		String saveDir = "C:\\javaStudy\\upload";
		
		//오리지널 파일명
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		//저장 파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName; 
		
		//파일경로(디렉토리 + 저장파일명)
		String filePath = saveDir + "\\" + saveName;
		
		//파일크기
		Long fileSize = file.getSize();
		
		
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		
		
		galleryDao.insert(galleryVo);
		
		
		

		try {
			//파일 byte배열 만들어 저장하기
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
	
	//사진 주소 가져오기
	public GalleryVo show(GalleryVo galleryVo) {
		System.out.println("GalleryService > show()");
		
		GalleryVo fileInfo = galleryDao.show(galleryVo);
		return fileInfo;
	}
	
	
	//사진 삭제
	public int delete(GalleryVo galleryVo) {
		System.out.println("GalleryService > delete()");
		
		//유저정보 가져오기
		GalleryVo user = galleryDao.getUser(galleryVo);
		System.out.println(user);
		int userNo = user.getNo();
		
		//유저삭제
		int count = galleryDao.delete(userNo);
		return count;
	}
	
	
}
