package com.cos.Blog1.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
//텍스트 출력
@RestController
public class BlogControllerTest {

	@GetMapping("/test/hello")
	public String hello() {
		return " <h1>hello</h1>";
	}
}