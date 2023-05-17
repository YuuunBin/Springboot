package com.cos.Blog1.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data//게터세터
@NoArgsConstructor //빈 생성자
@AllArgsConstructor//전체생성자
@Builder//빌더 패턴
public class Reply {
 
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db의 넘버링 전략을 따라감.
	private int id; //시퀸스, auto_increment
	
	@Column(nullable=false,length=200)
	private String content;
	
	@ManyToOne 
	//보드 테이블과 연관관계. 
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne
	//유저테이블과 자동으로 연결됨
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;  
}
