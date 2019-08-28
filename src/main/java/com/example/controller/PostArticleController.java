package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticlesRepository;

@Controller
public class PostArticleController {
	
	@Autowired
	private ArticlesRepository articleRepository;
	
	@RequestMapping("/postArticle")
	public String postArticle(@Validated ArticleForm form, 
			BindingResult result,
			Model model) {
		
		if(result.hasErrors()) {
			return "forward:/articleList";
		} 
		
		Article articles = new Article();
		articles.setName(form.getName());
		articles.setContent(form.getContent());
		articleRepository.insert(articles);
		return "redirect:/";
		
	}

}
