package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.ArticleForm;
import com.example.repository.ArticlesRepository;

@Controller
@RequestMapping
public class ArticleDeleteController {
	@Autowired
	private ArticlesRepository article;
	
	
	@RequestMapping("")
	public String delete(ArticleForm form) {
		int num = Integer.parseInt(form.getId());
		article.delete(num);
		return "/bbs";
	}
	

}
