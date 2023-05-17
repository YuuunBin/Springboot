package com.cos.Blog1.controller.api;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.Blog1.config.auth.PrincipalDetailService;
import com.cos.Blog1.dto.ResponseDto;
import com.cos.Blog1.model.User;
import com.cos.Blog1.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private PrincipalDetailService principalDetailService;


	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 인서트를 하고 아래에서 리턴이 되면 만사형통
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user, HttpSession session) {
		userService.회원수정(user);
		// 트랜잭션으로 db값은 저장됐지만 세션값은 변경되지 않은 상태. 세션값을 직접 변경해줄 것임.
		// authentication 객체 생성 및 세션등록
		UserDetails userDetail = principalDetailService.loadUserByUsername(user.getUsername());
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}