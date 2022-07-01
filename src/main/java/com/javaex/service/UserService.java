package com.javaex.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//회원정보 저장(회원가입)
	public int join(UserVo userVo) {
		System.out.println("UserService > join()");

		int count = userDao.userInsert(userVo);
		return count;
	}
	
	//회원정보 저장(로그인)
	public UserVo login(UserVo userVo) {
		System.out.println("UserService > login()");
		System.out.println(userVo);
		UserVo authUser = userDao.getUser(userVo);
		
		return authUser;
	}
	
	//로그인된 회원정보 가져오기
	public UserVo getUserModify(int no) {
		System.out.println("UserService > getUserModify()");
		UserVo userModify = userDao.getUserModify(no);
		return userModify;
	}
	
	//로그인된 회원정보 수정
	public int modify(UserVo userVo) {
		System.out.println("UserService > modify()");
		
		int count = userDao.userUpdate(userVo);
		return count;
	}
	
	
	//유저아이디 체크
	public String check(UserVo userVo) {
		System.out.println("UserService > check()");
		
		String idCheck = "";
		
		UserVo getIdUser = userDao.getIdUser(userVo);
		
		if(getIdUser == null) {
			idCheck = "success";
			
		}else if(getIdUser != null) {
			idCheck = "fail";
		}
		
		
		return idCheck;
	}
	
	
	
}
