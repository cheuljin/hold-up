package com.poseidon.Util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSave {
	public String save(String realPath, MultipartFile file) throws IllegalStateException, IOException {
		File saveFile = new File(realPath);
		if(!saveFile.exists()) {
			saveFile.mkdirs();
		}
		
		String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
		
		saveFile = new File(saveFile, fileName);
		//file.transferTo(saveFile);//자바io에 있어요
		FileCopyUtils.copy(file.getBytes(), saveFile);//스프링이 제공
		
		return fileName;
	}
}
