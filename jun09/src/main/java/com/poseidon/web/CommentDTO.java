package com.poseidon.web;

import java.util.Date;
import lombok.Data;

@Data
public class CommentDTO {
	private int c_no, b_no, u_no;
	private String c_content, u_id;
	private Date c_date;
}
