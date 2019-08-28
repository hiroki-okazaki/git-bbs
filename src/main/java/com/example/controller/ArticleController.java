package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.CommentForm;
import com.example.repository.ArticlesRepository;
import com.example.repository.CommentRepository;

@Controller
@Transactional
@RequestMapping("")
public class ArticleController {
	
	@Autowired
	private ArticlesRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public CommentForm setUpForm() {
		return new CommentForm();
	}

}
