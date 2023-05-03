package com.cos.Blog1.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //user 클래스가 mysql에 테이블이 생성된다 DB에 맵핑 시켜주는 클래스
@Data//게터세터
@NoArgsConstructor //빈 생성자
@AllArgsConstructor//전체생성자
@Builder//빌더 패턴
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	 
	@Column(nullable = false, length=100)
	private String title;
	
	@Lob //대용량 데이터
	private String content;
	
	//조회수
	@ColumnDefault("0")
	private int count;
	
	//	누가 적었는지 알기 위해서 
	// Many=Board, One=user 한 명의 유저는 여러 게시글을 쓸 수 있다는 뜻
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId") 
	private User user; 
	
	//댓글은 한 사람만 쓰는게 아니라서 List를 써야함
	@OneToMany(mappedBy="board", fetch=FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다. FK가 아니니 DB에 컬럼을 만들지 말라는 뜻 board 를 셀렉할 때 join문을 통해 값을 얻기위해 필요하다는 뜻
								//mappedBy="board" 에서의 board는 reply 클래스의 필드 이름
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
