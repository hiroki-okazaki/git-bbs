package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticlesRepository;
import com.example.repository.CommentRepository;

@Controller

@Transactional


@RequestMapping("/")

public class ArticleController {
	

	@Autowired
	private ArticlesRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@RequestMapping("/articleList")
	public String articleList(Model model) {
		
		List<Article> articleList = articleRepository.findAll();
		
		
		for(Article article :  articleList) {
			List<Comment> comment = commentRepository.findByArticleId(article.getId());
			article.setCommentList(comment);
		}
		model.addAttribute("articleList",articleList);
		return "/bbs";
	}
	
	
}
