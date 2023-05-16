package com.cos.Blog1.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
//ORM -> 자바 오브젝트 -> 테이블로 맵핑해주는 기술
//user클래스가 mysql에 테이블 생성됨
@Entity
@Data//게터세터
@NoArgsConstructor //빈 생성자
@AllArgsConstructor//전체생성자
@Builder//빌더 패턴
//@DynamicInsert  insert시 null인 필드를 제외시켜준다.
public class User {

	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db의 넘버링 전략을 따라감.
	private int id; //시퀸스, auto_increment
	
	@Column(nullable =false, length=30)
	private String username;
	
	// 비번 해쉬로 비밀번호 암호화 할 거라서 넉넉하게 숫자 정해둠
	@Column(nullable =false, length=100) 
	private String password;
	
	@Column(nullable =false, length=50)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private RoleType role; 

	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;  
}
