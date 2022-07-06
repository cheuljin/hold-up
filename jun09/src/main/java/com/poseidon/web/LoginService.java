package com.poseidon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private LoginDAO loginDAO;
	
	public LoginDTO login(LoginDTO dto) {
		return loginDAO.login(dto);
	}

	public int join(LoginDTO dto) {
		return loginDAO.join(dto);
		//방금한 동적form을 댓글쓰기에 적용하고 join은 기본 form입력방식으로 변경하기
	}

	public int checkID(String parameter) {
		return loginDAO.checkID(parameter);
	}
	
}
