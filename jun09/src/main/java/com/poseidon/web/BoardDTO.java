package com.poseidon.web;

import java.util.Date;

import lombok.Data;

//jun_boardview에 맞춰서 수정해주세요.

@Data
public class BoardDTO {
	private int b_no, b_count, commentCount;
	private String b_title, b_content, u_id;
	private Date b_date;
}
