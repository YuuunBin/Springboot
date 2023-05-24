package com.cos.Blog1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Blog1Application {
   
	public static void main(String[] args) {
		SpringApplication.run(Blog1Application.class, args);
	}
  
}
