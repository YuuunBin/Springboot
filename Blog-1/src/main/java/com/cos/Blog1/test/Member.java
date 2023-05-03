package com.cos.Blog1.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
//@Getter
//@Setter
//게터세터 다 만들어주는 어노테이션 Data
@Data
//생성자 만들어줌
//@AllArgsConstructor
//빈 생성자
@NoArgsConstructor
public class Member {
	private int id;
	// 데이터 변경하지 않게끔 final 지정(만약 변경할 일 있는 애들은 final 걸면 안 됨)
	private String username;
	private String password;
	private String email;

	// 직접 AllArgsConstructor 만드는 방법
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
