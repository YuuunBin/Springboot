package com.cos.Blog1.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 
//해당 경로에 있는 파일을 읽어줌
@Controller
public class TempControllerTest {

	//http://localhost:8005/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		//파일 리턴 기본 경로:/src/main/resource/static 
		//리턴명:/home.html
		//풀경로: src/main/resource/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temt/jsp")
	public String tempJsp() {
		//prefix : /WEB-INF/views/
		//suffix : jsp
		//풀네임: /WEB-INF/views/test.jsp
		return"test";
	}
}
