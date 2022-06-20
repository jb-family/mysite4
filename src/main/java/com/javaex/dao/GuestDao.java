package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	
	@Autowired
	SqlSession sqlSession;
	
	//리스트
	public List<GuestVo> selectList() {
		System.out.println("GuestDao > selectList");
		
		List<GuestVo> gList = sqlSession.selectList("guest.selectList", sqlSession);
		return gList;
	}
	
	//추가
	public int insert(GuestVo guestVo) {
		System.out.println("GuestDao > insert");
		
		int count = sqlSession.insert("guest.insert", guestVo);
		return count;
	}
	
	//유저정보 가져오기
	public GuestVo getUser(int no) {
		System.out.println("GuestDao > guestVo");
		
		GuestVo user = sqlSession.selectOne("guest.getUser", no);
		return user;
	}
	
	//삭제
	public int delete(GuestVo guestVo) {
		System.out.println("GuestDao > delete");
		
		int count = sqlSession.delete("guest.delete", guestVo);
		return count;
	}
	
	
	
	
	

}
