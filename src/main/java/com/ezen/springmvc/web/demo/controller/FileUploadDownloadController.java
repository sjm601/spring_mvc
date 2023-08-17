package com.ezen.springmvc.web.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class FileUploadDownloadController {
	
	@Value("${file.dir}")
	private String location;
	
	private final FileStore fileStore;
	
//	public FileUploadDownloadController(FileStore fileStore) {
//		this.fileStore = fileStore;
//	}

	@GetMapping("/upload2")
	public String uploadForm() {
		return "form2";
	}

	@PostMapping("/upload2")
	public String uploadFiles(@ModelAttribute MemberForm memberForm, RedirectAttributes redirectAttributes)
			throws IOException {
		log.info("업로드 파일: {}", memberForm.getUploadfiles());

		List<UploadFile> uploadFiles = fileStore.storeFiles(memberForm.getUploadfiles());
		
		log.info("저장된파일명 : {}", uploadFiles);

		// 데이터베이스에 오리지날파일명과 저장파일명 저장 후(테이블의 컬럼이 2개 필요)
		redirectAttributes.addFlashAttribute("uploader", memberForm.getUploader());
		redirectAttributes.addFlashAttribute("uploadfiles", uploadFiles);
		return "redirect:upload-result2";
	}

	@GetMapping("/upload-result2")
	public String uploadResult() {
		return "upload-result2";
	}
	
	@GetMapping("/download")
	public String download(Model model) {
		File directory = new File(location);
		File[] files = directory.listFiles();
		model.addAttribute("list", files);
		return "download";
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> download(@PathVariable String fileName) throws IOException {
		Path path = Paths.get(location + "/" + fileName);
		String contentType = Files.probeContentType(path);
		HttpHeaders headers = new HttpHeaders();
		// 응답 헤더에 파일정보 설정
		headers.setContentDisposition(
				ContentDisposition
					.builder("attachment")
					.filename(fileName, StandardCharsets.UTF_8).build());
		
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	//동적 이미지의 경우 출력
	@GetMapping("/images/{name}")
	@ResponseBody
	public ResponseEntity<Resource> showImage(@PathVariable String name) throws IOException {
		Path path = Paths.get(location + "/" + name);
		String contentType = Files.probeContentType(path);
		Resource resource = new FileSystemResource(path);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
}
