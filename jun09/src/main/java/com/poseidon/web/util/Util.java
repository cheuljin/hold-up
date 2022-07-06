package com.poseidon.web.util;

import org.springframework.stereotype.Component;

@Component
public class Util {
	//1. str2int     123  -> 123
	public int str2Int(String str) {
		return Integer.parseInt(str);
	}
	
	//2. html tag
	public String html(String str) {
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\n", "<br>");
		return str;
	}
	
	//3. ip얻어오기
}
