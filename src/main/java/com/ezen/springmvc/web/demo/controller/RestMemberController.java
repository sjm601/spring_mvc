package com.ezen.springmvc.web.demo.controller;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원 관련 API 서비스 세부 컨트롤러
 * @author 김기정
 *
 */
@Controller
@RequestMapping("/member2")
@Slf4j
public class RestMemberController {
	
	@GetMapping("/{memberId}")
	@ResponseBody
	public String read(@PathVariable String memberId,  @RequestBody(required = false) String text, Model model) {
		
		log.info("수신한 텍스트: {}", text);
		
		String id = "bangry";
		String name = "김기정";
		return "아이디: "+id+", 이름 : " + name;
	}
	
	
	// 회원가입 요청 처리
	@PostMapping("/register")
	@ResponseBody
	public Member register(@RequestBody Member member) {
		log.info(member.toString());
		// MemberService를 이용한 DB 처리 완료 가정
		return member;
	}
	
}










