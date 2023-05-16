package com.cos.Blog1.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//인증 안 된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 /이면 index.jsp 허용
//static 이하에 있는 js/** css/** image/**

@Controller
public class UserController{
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal Principal principal) {
		
		return "user/updateForm";
	}
}