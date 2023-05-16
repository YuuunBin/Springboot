package com.cos.Blog1.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.Blog1.model.RoleType;
import com.cos.Blog1.model.User;
import com.cos.Blog1.repository.UserRepository;

//html
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입
	private UserRepository userRepository;

	// 정보 전체 select
	// http://localhost:8005/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	// 한 페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public Page<User> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> user = pagingUser.getContent();
		return pagingUser;
	}

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "해당 id는 db에 없습니다.";
		}
		return "삭제되었습니다. : id:" + id;
	}

	// save함수는 id를 전달하지 않으면 insert해주고
	// save함수는 id를 전달, 해당 id에 대한 데이터가 있으면 update
	// save함수는 id를 전달, 해당 id에 대한 데이터가 없으면 insert
	// 수정은 pw,email만
	// 영속화 작업
	@Transactional // 함수 종료시 자동 commit 됨
	@PutMapping("dummy/user/{id}")
	public User updateUseUser(@PathVariable int id, @RequestBody User requestUser) {// json데이터를 요청->java object
		System.out.println("id: " + id);
		System.out.println("password:" + requestUser.getPassword());
		System.err.println("email:" + requestUser.getEmail());
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정 실패");

		});

		// 값 변경감지 -> db에 수정 날려줌. 이걸 더티체킹이라고 함.
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// 더티체킹
		return user;
	}

	// id 번호로 정보 불러오기. 없는 번호일시 에러메시지+ 페이지 뜸.
	// http://localhost:8005/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);

			}
		});
		// 요청:웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환(웹브라우저가 이해할 수 있는 데이터로)->json
		// 스브링부트는 MessageConvert가 JAckson라이브러리를 호출해서 user 오브젝트를 json으로 변환해 브라우저로 전송
		return user;
	}

//	public String join(String username, String password, String email) { (key=value 약속된 규칙)
//	System.out.println("username:" + username);
//	System.out.println("password:" + password);
//	System.out.println("email:" + email);
//	return "회원가입 완료";
	// http://localhost:8005/blog/dummy/join (요청)
	// http 바디에 username, password, email데이터 가지고 요청.
	// 오브젝트로 받아 처리할 수도 있음
	@PostMapping("dummy/join")
	public String join(User user) { // key = value (약속된 규칙)
		System.out.println("id: : " + user.getId());
		System.out.println("username: : " + user.getUsername());
		System.out.println("password: : " + user.getPassword());
		System.out.println("email: : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate: " + user.getCreateDate());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
}
