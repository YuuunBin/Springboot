package com.cos.Blog1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.Blog1.model.RoleType;
import com.cos.Blog1.model.User;
import com.cos.Blog1.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해 bean에 등록을 해줌. IoC 해줌
//서비스 해주는 이유? 
@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	@Transactional(readOnly=true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
		return new User();
		});
		return user;
	}
		
	
	@Transactional
	public void 회원가입(User user) {
	String rawPassword = user.getPassword(); //1234 원문
	String encPassword = encoder.encode(rawPassword); //해쉬
	user.setPassword(encPassword);
	user.setRole(RoleType.USER);
		userRepository.save(user);

	}
	
	@Transactional
	public void 회원수정(User user) {
		//수정시 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정하면 됨. 그래서 select먼저 함. user오브젝트를 db로부터 가져오는 이유는 영속화하려고.
		//영속화된 오브젝트를 변경하면 자동으로 update문을 날려줌.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		
		if(persistance.getOauth()==null || persistance.getOauth().equals("")) {
			String rawPassword=user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}

	
		

		//회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 자동으로 이루어짐
		//영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 변화된 것들을 update문을 날려줌
		
	}
	
	

//	@Transactional(readOnly=true)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
