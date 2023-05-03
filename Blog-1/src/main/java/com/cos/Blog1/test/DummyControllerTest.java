package com.cos.Blog1.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class DummyControllerTest {

	//http://localhost:8005/blog/dummy/join (요청)
	//http 바디에 username, password, email데이터 가지고 요청. 
	@PostMapping("/dummy/join")
	public String join(String username, String password, String email) {
		System.out.println("username:" + username);
		System.out.println("password:" + password);
		System.out.println("email:" + email);
		return "회원가입 완료";
		
	}
}
