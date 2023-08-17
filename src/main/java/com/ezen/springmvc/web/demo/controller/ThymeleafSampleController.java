package com.ezen.springmvc.web.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/thymeleaf")
@Slf4j
public class ThymeleafSampleController {
	
	@GetMapping("/exam1")
	public String exam1(Model model) {
		
		Member member = Member.builder()
					.id("bangry")
					.passwd("1111")
					.age(30)
					.build();
		
		List<Member> list = new ArrayList<>();
		list.add(new Member("hong1", "1111", 10));
		list.add(new Member("hong2", "1111", 10));
		list.add(new Member("hong3", "1111", 10));
		
		model.addAttribute("member", member);		
		model.addAttribute("list", list);		
		return "demo/exam1";
	}
	
	@GetMapping("/exam2")
	public String exam2(Model model) {
		model.addAttribute("score", 88);	
		return "demo/exam2";
	}
	
	@GetMapping("/exam3")
	public String exam3(Model model) {
		model.addAttribute("id", "sjm601");	
		model.addAttribute("name", "박상훈");	
		return "demo/exam3";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute Member member,Model model,RedirectAttributes redirectAttributes) {
		log.info("수신한 회원 정보 {}", member);
		//회원 가입 완료 후 결과 페이지로 재요청
		redirectAttributes.addFlashAttribute("result","success");
		redirectAttributes.addFlashAttribute("member", member);
		return "redirect:/thymeleaf/member-result";
	}
	
	@GetMapping("/member-result")
	public String resitResult(Model model) {
		return "demo/member-result";
	}
	
	@GetMapping("/regist")
	public String form(Model model) {
		return "demo/regist-form";
	}
	
}
