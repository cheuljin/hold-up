package com.poseidon.DTO;

import lombok.Data;

@Data
public class LoginDTO {
	private int u_no, u_grade, u_resign;
	private String u_id, u_name, u_pw, u_date;
	private String email;//새로 추가
}
