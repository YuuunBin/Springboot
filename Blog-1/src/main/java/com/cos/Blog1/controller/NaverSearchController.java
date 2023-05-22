package com.cos.Blog1.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.Blog1.controller.api.NaverSearchApi;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open")
public class NaverSearchController {

	@Autowired
	NaverSearchApi naver = new NaverSearchApi();

	@ResponseBody
	@GetMapping("/auth/search")
	public ResponseEntity<JSONObject> getPlace(@RequestParam("query") String query) throws Exception {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(naver.search(query));
		JSONObject jsonObj = (JSONObject) obj;
		return ResponseEntity.ok(jsonObj);
	}
}