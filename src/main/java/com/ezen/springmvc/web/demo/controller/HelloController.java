package com.ezen.springmvc.web.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * /hello  요청에 대한 세부 컨트롤러(핸들러)
 *  @author 김기정
 *
 */
@Controller
public class HelloController {
	
	@GetMapping({"/hello", "/hi"})
	public String hello(Model model) {
		
		String message = "Spring <strong>MVC</strong> 실습페이지입니다..";
		model.addAttribute("message", message);
		return "hello";
	}
}





