package com.example.form;


/**
 * コメント情報を表すドメインクラス
 * @author hirokiokazaki
 *
 */
public class CommentForm {

	
	/**
	 * ID(主キー)
	 */
	private String id;
	
	/**
	 * 名前
	 */
	private String name;
	
	/**
	 * コメント
	 */
	private String content;
	
	/**
	 * 投稿記事ID
	 */
	private String articleId;
	
	
	public Integer getIntArticleId() {
		return Integer.parseInt(articleId);
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "commentForm [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}
	
	
	
}