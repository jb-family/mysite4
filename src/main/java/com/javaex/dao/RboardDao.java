package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	//리스트 가져오기
	public List<RboardVo> list(String keyword) {
		System.out.println("RboardDao > list()");
		List<RboardVo> rList = sqlSession.selectList("rboard.list", keyword);
		
		return rList;
	}
	
	//글 추가
	public int write(RboardVo rBoardVo) {
		System.out.println("RboardDao > write()");
		
		System.out.println("title : "+rBoardVo);
		int count = sqlSession.insert("rboard.insert", rBoardVo);
		return count;
	}
	
	//글 보기
	public RboardVo getUser(int no) {
		System.out.println("RboardDao > getUser()");
		
		RboardVo user = sqlSession.selectOne("rboard.getUser", no);
		return user;
	}
	
	//조회수 증가
	public int hitUp(int no) {
		System.out.println("RboardDao > hitUp()");
		
		int count = sqlSession.update("rboard.hitUp", no);
		return count;
	}
	
	
	//글 삭제
	public int delete(int no) {
		System.out.println("RboardDao > delete()");
		
		int count = sqlSession.delete("rboard.delete", no);
		System.out.println("no값"+no);
		return count;
	}
	
	//글 수정
	public int modifyUser(RboardVo rBoardVo) {
		System.out.println("RboardDao > update()");
		
		int count = sqlSession.update("rboard.update", rBoardVo);
		return count;
	}
	
	
	//글 보기
	public RboardVo getUserInfo(int no) {
		System.out.println("RboardDao > getUserInfo()??????");
		
		RboardVo user = sqlSession.selectOne("rboard.getUserInfo", no);
		return user;
	}
	
	
	
	
	//댓글 추가
	public int commentInsert(RboardVo rBoardVo) {
		System.out.println("RboardDao > commentInsert()");
		
		int count = sqlSession.insert("rboard.commentInsert", rBoardVo);
		return count;
	}
	
	
	//order_no 증가
	public int orderUp(RboardVo rBoardVo) {
		System.out.println("RboardDao > orderUp()");
		
		int count = sqlSession.update("rboard.orderUp", rBoardVo);
		return count;
	}
	
	//댓글 작성자 (groupNo) 정보 가져오기
	public RboardVo groupInfo(int no) {
		System.out.println("RboardDao > getUserInfo()");
		
		RboardVo user = sqlSession.selectOne("rboard.groupInfo", no);
		return user;
	}
	
	
	//자식정보 가져오기 
	public RboardVo userInfo(RboardVo rBoardVo) {
		System.out.println("RboardDao > userInfo()");
		System.out.println("왜안나와 시발아"+rBoardVo);
		RboardVo rVo = sqlSession.selectOne("rboard.userInfo", rBoardVo);
		return rVo;
	}
	
	
	
}
