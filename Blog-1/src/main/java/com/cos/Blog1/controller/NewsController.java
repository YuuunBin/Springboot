package com.cos.Blog1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.Blog1.model.NewsArticle;
import com.cos.Blog1.repository.NewsRepository;


@Controller
public class NewsController{
	@Autowired
    private NewsRepository newsRepository;


	  @GetMapping("/news")
	    public String showNews(Model model) {
	        List<NewsArticle> newsList = newsRepository.findAll();
	        model.addAttribute("newsList", newsList);
	        return "news";
	    }
	}
