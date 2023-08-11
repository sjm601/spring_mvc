package com.ezen.springmvc.web.demo.controller;

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
 * @author 박상훈
 *
 */
@Slf4j
@Controller
@RequestMapping("/member2")
public class RestMemberController {
		
	
	@GetMapping("/{memberId}")
	@ResponseBody
	public String read(@PathVariable String memberId,@RequestBody (required = false)String text,Model model){
		String id = "sjm601";
		String name = "미켈 아르테타";
		log.info("수신한 텍스트 :{}",text);
		return "아이디:" +id +", 이름 :" + name;
	
	}

}
