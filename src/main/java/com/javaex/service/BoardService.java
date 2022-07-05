package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//리스트(일반)
	public Map<String, Object> getBoardList4(int crtPage) {
		System.out.println("BoardService > getBoardList4()");
		
		////////////////////////////////////////////////
		//					리스트 가져오기				  //
		////////////////////////////////////////////////
		
		
		//현재페이지
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
		//페이지 글개수
		int listCnt = 10;
		//시작 글번호
		int startRnum = (crtPage - 1) * listCnt + 1;
		//마지막 글번호
		int endRnum = (startRnum + listCnt) -1;
		
		List<BoardVo> boardList = boardDao.selectList4(startRnum, endRnum);
		
		
		
		////////////////////////////////////////////
		//					페이징계산				  //
		////////////////////////////////////////////
		
		//전체글갯수
		int totalCnt = boardDao.selectTotalCnt();	
		System.out.println("totalCnt :"+totalCnt);
		
		//페이지 당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage / (double)pageBtnCount) * pageBtnCount;
		
		//첫번째 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtnCount) + 1;
		
		
		//다음 화살표 유무
		boolean next = false;
		//이전 화살표 유무
		boolean prev = false;
		
		if( (listCnt * endPageBtnNo) < totalCnt ) {// ex) 50 < 127 true
			next = true;
		}else {
			endPageBtnNo = (int)Math.ceil(totalCnt / (double)listCnt);
		}
		
		if( (startPageBtnNo != 1) ) {	// 
			prev = true;
		}
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		
		System.out.println(pMap);
		
		
		
		
		
		
		
		
		
		
		
		return pMap;
	}
	
	
	
	
	
	//리스트 불러오기 (검색기능 +)
	public List<BoardVo> selectList(String keyword) {
		System.out.println("BoardService > list4");
		
		List<BoardVo> bList = boardDao.selectList(keyword);
		return bList;
	}
	
	//작성한 글 추가
	public int insert(BoardVo boardVo) {
		System.out.println("BoardService > insert");
		
			//임시
			for(int i = 1; i <= 127; i++) {
				boardVo.setTitle(i + "번째 게시글(제목)입니다.");
				boardVo.setContent(i + "번째 게시글(내용)입니다.");
				boardDao.insert(boardVo);
			}
		
		
		
		
		
		//int count = boardDao.insert(boardVo);
		return 1;
	}
	
	
	//조회수 증가(조회수 업데이트)
	public int hitUp(int no) {
		System.out.println("BoardService > hitUp");
		
		int count = boardDao.hitUp(no);
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
		System.out.println("boardVooooo111111"+boardVo);
		return count;
	}
	
	
	public BoardVo read(int no) {
		//조회수 증가(조회수 업데이트)
		boardDao.hitUp(no);
		
		//작성한 글 보기
		BoardVo count = boardDao.getUser(no);
		
		return count;
	}
	

	
}
