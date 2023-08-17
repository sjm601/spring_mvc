package com.ezen.springmvc.web.demo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileUploadController {

	// application.properties에 설정된 파일 경로
	// @Value("C:/ezen-fullstack/workspace/spring_mvc/upload")
	
	@Value("${file.dir}")
	private String location;
	
	// 파일 업로드 화면 요청
	@GetMapping("/upload")
	public String uploadForm() {
		return "form";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("uploader") String uploader,
			                 @RequestParam("uploadfile") MultipartFile uploadfile,
			                 Model model) throws IOException {
		log.info("업로더 = {}", uploader);
		log.info("multipartFile = {}", uploadfile);
		
		
		if (!uploadfile.isEmpty()) {
			String fullPath = location + uploadfile.getOriginalFilename();
			log.info("파일 저장 fullPath = {}", fullPath);
			File uploadDir = new File(fullPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			// 업로드 파일 저장
			uploadfile.transferTo(uploadDir);
			
			model.addAttribute("uploader", uploader);
			model.addAttribute("uploadfile", uploadfile.getOriginalFilename());
		}
		return "upload-result";
	}

}
