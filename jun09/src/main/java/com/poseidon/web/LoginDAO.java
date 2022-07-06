package com.poseidon.web;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//로그인 처리하는 메소드
	public LoginDTO login(LoginDTO dto) {
		return sqlSession.selectOne("login.login", dto);
	}

	public int join(LoginDTO dto) {
		return sqlSession.insert("login.join", dto);
	}

	public int checkID(String parameter) {
		return sqlSession.selectOne("login.checkID", parameter);
	}
	
}
