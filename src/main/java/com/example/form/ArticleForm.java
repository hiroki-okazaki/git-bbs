package com.example.form;


/**
 * 投稿記事情報を表すフォームクラス
 * @author hirokiokazaki
 *
 */
public class ArticleForm {

	
	/**
	 * ID
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
	
	

	@Override
	public String toString() {
		return "articleForm [ID=" + id + ", name=" + name + ", content=" + content + "]";
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
	
	
	
	
}
