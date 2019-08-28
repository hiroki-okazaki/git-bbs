package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * articlesテーブルを操作するリポジトリ.
 * 
 * @author hirokiokazaki
 *
 */
@Repository
public class ArticlesRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLE_ARTICLES = "articles";

	private static final String TABLE_COMMENTS = "comments";

	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		// ここに結果の処理を書く
		Article article = new Article();

		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));

		return article;
	};

	private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = (rs) -> {

		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = null;

		int preId = -1;

		while (rs.next()) {

			int id = rs.getInt("A_id");

			if (id != preId) {
				Article article = new Article();

				article.setId(rs.getInt("A_id"));
				article.setName(rs.getString("A_name"));
				article.setContent(rs.getString("A_content"));

				commentList = new ArrayList<>();
				article.setCommentList(commentList);
				articleList.add(article);
			}
			Integer commentCheckId = rs.getInt("C_id");

			if (commentCheckId != 0) {

				Comment comment = new Comment();
				comment.setId(rs.getInt("C_id"));

				comment.setName("C_name");
				comment.setContent(rs.getString("C_content"));
				comment.setArticleId(rs.getInt("C_article_id"));

				commentList.add(comment);
			}
			preId = id;
		}
		return articleList;
	};

	/**
	 * 全投稿記事を取得.
	 * 
	 * @return 投稿記事情報
	 */
	public List<Article> findAll() {
		String sql = "SELECT A.id A_id,A.name A_name,A.content A_content,C.id C_id,C.name C_name,C.content C_content,C.article_id C_article_id FROM articles A LEFT OUTER JOIN comments C ON A.id = C.article_id";

		List<Article> article = template.query(sql, ARTICLE_RESULT_SET_EXTRACTOR);

		return article;
	}

	/**
	 * 掲示板に記事を投稿する.
	 * 
	 * @param article 投稿記事
	 * @return 投稿記事情報
	 */
	public Article insert(Article article) {
		System.out.println(article);
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);

		String insertSql = "INSERT INTO articles (name, content) VALUES(:name, :content)";

		template.update(insertSql, param);

		return article;
	}

	/**
	 * 掲示板に投稿された記事を削除する.
	 * 
	 * @param id ID
	 */
	public void deleteById(Integer id) {
		String deleteSql = "DELETE FROM articles WHERE id = :id ";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		template.update(deleteSql, param);
	}
	
	public void delete(int articleId) {
		String sql = "WITH deleted AS (DELETE FROM "+ TABLE_ARTICLES +" WHERE id = :articleId RETURNING id) DELETE FROM " + TABLE_COMMENTS + " WHERE article_id IN (SELECT id FROM deleted)";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		
		template.update(sql,param);
	}
}