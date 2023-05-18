package com.cos.Blog1.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.Blog1.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	//Illegal Exception만 들어옴. value에 Exception만 있으면 모든 예외 처리를 여기서 함.
	@ExceptionHandler(value=IllegalArgumentException.class)
	public ResponseDto<String> handleArgumentExcepton(IllegalArgumentException e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()); //500 에러
	}
}
