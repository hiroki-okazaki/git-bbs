package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/insertComment")
public class InsertCommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public CommentForm setUpForm() {
		return new CommentForm();
	}
	
	/**
	 * コメント追加.
	 * @param form リクエストパラメーターが入ったフォーム
	 * @return 初期画面
	 */
	@RequestMapping("insert_comment")
	public String insertComment(CommentForm form) {
		System.out.println(form);
		Comment comment = new Comment();
		comment.setArticleId(form.getIntArticleId());
		BeanUtils.copyProperties(form, comment);
		commentRepository.insert(comment);
		return "redirect:/articleList";
	}

}
