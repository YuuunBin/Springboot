package com.cos.Blog1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.Blog1.model.Board;
import com.cos.Blog1.model.Reply;
import com.cos.Blog1.model.User;
import com.cos.Blog1.repository.BoardRepository;
import com.cos.Blog1.repository.ReplyRepository;

// 스프링이 컴포넌트 스캔을 통해 bean에 등록을 해줌. IoC 해줌
//서비스 해주는 이유? 
@Service
public class BoardService {

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);

	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글찾기 실패");
		}); // 영속화작업
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(서비스가 종료될 때) 트랜잭션이 종료 됨. 이 때 더티체킹 - 자동업데이트가 됨. db flush
	}

	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply) {

		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("글찾기 실패: 아이디 찾을 수 없음");
		}); // 영속화 완료

		requestReply.setUser(user);
		requestReply.setBoard(board);

		replyRepository.save(requestReply);
	}
}
