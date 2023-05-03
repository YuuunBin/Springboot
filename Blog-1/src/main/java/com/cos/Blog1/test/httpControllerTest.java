package com.cos.Blog1.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

// 사용자가 요청->응답(Data)
@RestController
public class httpControllerTest {
	
	private static final String TAG = "HttpControllerTest: ";
	
	//yml에 path 설정 후 주소 : localhost3306:8005/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//builder 어노테이션 활용. 좋은 점: 입력 순서 안 지켜도 됨! 
		Member m = Member.builder().username("ssar").password("1234").email("11@naver.com").build();
		System.out.println(TAG+"getter:" +m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter:" +m.getUsername());
		return "lombok test clear";
	} 
 
	// 	builder 사용하지 않은 lomboktest	
	//	@GetMapping("/http/lombok")
	//	public String lombokTest() {
	//		Member m = new Member(1,"ssar","!234","email");
	//		System.out.println(TAG+"getter :"+m.getId());
	//		m.setId(5000);
	//		System.out.println(TAG+"setter :"+m.getId());
	//		return "lombok test clear";
	//	}
	
	
	 
	//인터넷 브라우저 요청은 무조건 get 요청만 할 수 있다.
	//http://localhost:8005/http/post (select)
	//Request Param으로 정보 출력하는 방법
	// @GetMapping("/http/get")
	//public String getTest(@RequestParam int id, @RequestParam String username) {
	//	return "get 요청:"+id+","+username;
	//	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//	x-www-form-urlencoded 방법. get을 제외한 나머지는 body에 넣어줘야 한다
	//	http://localhost:8005/http/post (insert)
	//	@PostMapping("/http/post") //text/plain, application/json
	//	public String postTest(@RequestBody String text) { //MesageConvert(스프링부트)
	//		return "Post요청: " +text;
	//	}

	// 텍스트 자체를 읽어낼 수 없기 때문에 객체에 정보를 담아서 db로 보냄
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "Post 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//http://localhost:8005/http/post (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//http://localhost:8005/http/post (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete";
	}
}
