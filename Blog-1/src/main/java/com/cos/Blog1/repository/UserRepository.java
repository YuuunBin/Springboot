package com.cos.Blog1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.Blog1.model.User;

//DAO 
//자동으로 bean 등록이 된다
//@Repository//생략 가능함
public interface UserRepository extends JpaRepository<User, Integer>{
	// SELECT 8 FROM user WHERE username=1?;
	Optional<User> findByUsername(String username);
	
}

	
	
	
	//로그인을 위한 함수 만들기 JPA Naming 쿼리 전략
	//SELECT * FROM user Where username =?1 AND password =?2; 동작함
	//전통적인 로그인방식
	//User findByUsernameAndPassword(String username, String password);

	