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
	
}
