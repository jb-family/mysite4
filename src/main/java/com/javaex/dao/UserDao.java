package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	//회원정보 저장(회원가입)
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao > join()");

		int count = sqlSession.insert("user.insert", userVo);
		return count;
	}
	//회원정보 저장(로그인)
	public UserVo getUser(UserVo userVo) {
		System.out.println("UserDao > login()");

		UserVo authUser = sqlSession.selectOne("user.getUser", userVo);
		return authUser;
	}
	
	//로그인된 회원정보 가져오기
	public UserVo getUserModify(int no) {
		System.out.println("UserDao > getUserModify()");
		
		UserVo userModify = sqlSession.selectOne("user.getUserModify", no);
		return userModify;
	}
	
	//로그인된 회원정보 수정
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao > userUpdate()");
		
		int count = sqlSession.update("user.update", userVo);
		return count;
	}
	
	
	
	
}
