package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Service
public class RboardService {
	
	@Autowired
	RboardDao rBoardDao;
	
	//리스트 가져오기
	public List<RboardVo> list(String keyword) {
		System.out.println("RboardService > list()");
		List<RboardVo> rList = rBoardDao.list(keyword);

		//공백
		for(int i = 0; i < rList.size(); i++) {
			for(int j = 0; j < rList.get(i).getDepth(); j++) {
				rList.get(i).setTitle("&ensp;" + rList.get(i).getTitle());
			}
		}
		
		return rList;
	}
	
	//글 추가
	public int write(RboardVo rBoardVo, HttpSession session) {
		System.out.println("RboardService > write()");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		int no = authUser.getNo();
		rBoardVo.setUserNo(no);
		
		int count = rBoardDao.write(rBoardVo);
		return count;
	}
	
	//글 보기
	public RboardVo read(int no) {
		System.out.println("RboardService > read()");
		
		//조회수 증가
		rBoardDao.hitUp(no);
		
		RboardVo user = rBoardDao.getUser(no);
		return user;
	}
	
	//글 삭제
	public int delete(int no) {
		System.out.println("RboardService > delete()");
		
		int count = rBoardDao.delete(no);
		return count;
	}
	//글 수정자 정보 가져오기
		public RboardVo modifyUser(int no) {
			System.out.println("RboardService > update()");

			RboardVo user = rBoardDao.getUser(no);
			return user;
		}
		
	//글 수정
	public int modifyUser(RboardVo rBoardVo) {
		System.out.println("RboardService > update()");
		
		int update = rBoardDao.modifyUser(rBoardVo);
		return update;
	}
	
	//정보 가져오기
	public RboardVo commentUser(int no) {
		System.out.println("RboardService > commentUser()");
		
		//유저 정보 가져오기
		RboardVo user = rBoardDao.getUserInfo(no);
		return user;
	}
	
	
	//댓글 추가
	public int commentInsert(RboardVo rBoardVo) {
		System.out.println("RboardService > commentInsert()");
		
		//자식정보 추가
		rBoardDao.commentInsert(rBoardVo);
		System.out.println("자식값;;;;;;;;"+rBoardVo);
		
		//order_no 증가
		int count = rBoardDao.orderUp(rBoardVo);
		
		return count;
		
	}
	
	
	
	
	
}
