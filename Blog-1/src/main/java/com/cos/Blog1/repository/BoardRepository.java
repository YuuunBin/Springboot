package com.cos.Blog1.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.Blog1.model.Board;

//DAO 
//자동으로 bean 등록이 된다. sql 역할을 하는 인터페이스임.
//@Repository//생략 가능함
public interface BoardRepository extends JpaRepository<Board, Integer>{	
}

	
	