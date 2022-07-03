package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	SqlSession sqlSession;
	
	//리스트 불러오기
	public List<GalleryVo> selectList() {
		System.out.println("GalleryDao > selectList()");
		
		List<GalleryVo> gList = sqlSession.selectList("gallery.selectList");
		
		return gList;
	}
	
	
	//저장
	public int insert(GalleryVo galleryVo) {
		System.out.println("GalleryDao > insert()");
		System.out.println("total"+ galleryVo);
		int count = sqlSession.insert("gallery.insert",galleryVo);
		return count;
	}
	
	//갤러리 사진 주소 가져오기
	public GalleryVo show(GalleryVo galleryVo) {
		System.out.println("GalleryDao > show()");
		
		GalleryVo fileInfo = sqlSession.selectOne("gallery.show",galleryVo);
		return fileInfo;
		
	}
	
	//삭제할 유저정보 가져오기
	public GalleryVo getUser(GalleryVo galleryVo) {
		System.out.println("GalleryDao > getUser()");
		
		GalleryVo user = sqlSession.selectOne("gallery.getUser", galleryVo);
		System.out.println(user);
		
		return user;
	}
	
	//삭제
	public int delete(int no) {
		System.out.println("GalleryDao > delete()");
		
		int count = sqlSession.delete("gallery.delete", no);
		return count;
	}
	
	
	
}
