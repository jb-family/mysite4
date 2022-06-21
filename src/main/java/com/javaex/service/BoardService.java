package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//리스트 불러오기
	public List<BoardVo> selectList() {
		System.out.println("BoardService > list");
		
		List<BoardVo> bList = boardDao.selectList();
		return bList;
	}
	
	//작성한 글 추가
	public int insert(BoardVo boardVo) {
		System.out.println("BoardService > insert");
		
		int count = boardDao.insert(boardVo);
		return count;
	}
	
	//유저가 작성한 글 보기
	public BoardVo boardInfo(int no) {
		System.out.println("BoardService > boardInfo");
		
		BoardVo info = boardDao.boardInfo(no);
		return info;
	}
	
	//조회수 증가(조회수 업데이트)
	public int hitUp(int no) {
		System.out.println("BoardService > hitUp");
		
		int count = boardDao.hitUp(no);
		System.out.println("service "+count);
		return count;
	}
	
	//no를 넘겨서 유저 정보 가져오기
	public BoardVo getUser(int no) {
		System.out.println("BoardService > getUser");
		
		BoardVo getUser = boardDao.getUser(no);
		return getUser;
	}
	
	//작성한 글 삭제
	public int delete(int no) {
		System.out.println("BoardService > delete");
		
		int count = boardDao.delete(no);
		return count;
	}
	
	//작성한 글 수정
	public int update(BoardVo boardVo) {
		System.out.println("BoardService > update");
		
		int count = boardDao.update(boardVo);
		return count;
	}
	
	
	
}
