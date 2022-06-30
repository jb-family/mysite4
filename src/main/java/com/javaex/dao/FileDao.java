package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileDao {
	
	@Autowired
	SqlSession sqlSession;

	public int save(FileVo fileVo) {
		System.out.println("FileDao > save()");
		int count = sqlSession.insert("file.save", fileVo);
		return count;
	}
	
	
}
