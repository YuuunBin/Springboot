package com.cos.Blog1.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.Blog1.model.Board;
import com.cos.Blog1.model.Reply;
import com.cos.Blog1.repository.BoardRepository;
import com.cos.Blog1.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get(); //jackson라이브러리 (오브젝트를 json으로 리턴) 모델의 getter 호출
	}
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll(); //jackson라이브러리 (오브젝트를 json으로 리턴) 모델의 getter 호출
	}
	
}
