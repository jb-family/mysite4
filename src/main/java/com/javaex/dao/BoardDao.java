package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	

	//리스트 불러오기 (검색기능 +)
	public List<BoardVo> selectList(String keyword) {
		System.out.println("BoardDao > list");
		List<BoardVo> bList = sqlSession.selectList("board.selectList", keyword);
		return bList;
	}
	
	//작성한 글 추가
	public int insert(BoardVo boardVo) {
		System.out.println("BoardDao > insert");
		
		int count = sqlSession.insert("board.insert", boardVo);
		return count;
	}
	
	
	//조회수 증가(조회수 업데이트)
	public int hitUp(int no) {
		System.out.println("BoardDao > hitUp");
		
		int count = sqlSession.update("board.hitUp", no);
		System.out.println("dao "+count);
		return count;
	}
	
	//no를 넘겨서 유저 정보 가져오기
	public BoardVo getUser(int no) {
		System.out.println("BoardDao > getUser");
		
		BoardVo getUser = sqlSession.selectOne("board.getUser", no);
		return getUser;
	}
	
	//작성한 글 삭제
	public int delete(int no) {
		System.out.println("BoardDao > delete");
		
		int count = sqlSession.delete("board.delete", no);
		return count;
	}
	
	//작성한 글 수정
	public int update(BoardVo boardVo) {
		System.out.println("BoardService > update");
		
		int count = sqlSession.update("board.update", boardVo);
		System.out.println("boardVooooo22222"+boardVo);
		return count;
	}
	
	
}
