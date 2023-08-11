package com.ezen.springmvc.web.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원 관련 요청에 대한 세부 컨트롤러(핸들러)
 * @author 박상훈
 *
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/regist")
	public String form(Model model){
		return "regist";
	}
	
	//@RequestMapping(value="/regist", method = RequestMethod.POST)
	@PostMapping("/regist")
	public String regist(@ModelAttribute Member member, Model model){
		log.info("수신한 사용자 정보 : {}", member);
		//service를 이용한 DB 등록 처리
		return "redirect:regist-result";
	}
	
	
	@GetMapping("/regist-result")
	public String registResult(Model model){
		return "regist-result";
	}
	
	@GetMapping(value="/find" , params = "admin=bangry")
	public String find(Model model){
		return "hello";
	}
	
	//member/회원아이디(bangry)
	@GetMapping("/{memberId}")
	public String read(@PathVariable String memberId, @CookieValue(value = "loginId", required = false, defaultValue = "guest") String loginId,  Model model){
		log.info("수신한 회원아이디 {}",memberId);
		log.info("로그인 아이디 {}",loginId);
		return "hello";
	}

	//member/회원아이디(bangry)
		@PostMapping("/login")
		public String login(@RequestParam String id,@RequestParam String passwd, HttpSession session ,Model model){
			session.setAttribute("loginId", id);
			log.info("세션에 저장된 아이디 : {}",session.getAttribute("loginId"));
			return "hello";
		}
}
