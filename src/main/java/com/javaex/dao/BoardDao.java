package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	//리스트(일반)
	public List<BoardVo> selectList4(int startRnum, int endRnum) {
		System.out.println("BoardDao > selectList4()");
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		System.out.println(map);
		List<BoardVo> boardList = sqlSession.selectList("board.selectList4", map);
		System.out.println(boardList);
		return boardList;
		
		
		
	}
	
	//전체글 개수
	public int selectTotalCnt() {
		System.out.println("BoardDao > selectTotalCnt()");
		
		int totalCnt = sqlSession.selectOne("board.selectTotalCnt");
		
		return totalCnt;
	}
	

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
		return count;
	}
	
	
}
