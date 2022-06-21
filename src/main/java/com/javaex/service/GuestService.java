package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {
	
	@Autowired
	private GuestDao guestDao;
	
	//리스트
	public List<GuestVo> selectList () {
		System.out.println("GuestService > selectList");
		
		List<GuestVo> gList = guestDao.selectList();
		return gList;
	}

	
	//추가
	public int insert(GuestVo guestVo) {
		System.out.println("GuestService > insert");
		
		int count = guestDao.insert(guestVo);
		return count;
	}
	
	//유저정보 가져오기
	public GuestVo getUser(int no) {
		System.out.println("GuestService > getUser");
		
		GuestVo user = guestDao.getUser(no);
		return user;
	}
	
	//삭제
	public int delete(GuestVo guestVo) {
		System.out.println("GuestService > delete");
		
		int count = guestDao.delete(guestVo);
		return count;
	}
	
	
	
	
	
	
	
}
