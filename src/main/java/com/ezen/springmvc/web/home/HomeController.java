package com.ezen.springmvc.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		return "demo/index";
	}
	
	@GetMapping("/board")
	public String boards(Model model) {
		return "demo/board";
	}
	
}
