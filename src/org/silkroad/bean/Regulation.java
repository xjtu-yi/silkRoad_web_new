package org.silkroad.bean;

/**
 * @author : wuke
 * @date : 2017年4月21日下午9:16:28 
 * Title : Regulation 
 * Description : 政策法规数据库(IEEE)
 */
public class Regulation extends RecResource {
	private String doi;
	private String title;
	private String summary; // "abstract" in MySQL
	private String scope;
	private String author_keywords;
	private String date_publication;
	private String online_sibn;
	private String full_text_url; // "full_text" in MySQL

	public Regulation() {
	}

	public Regulation(String doi, String title, String summary, String scope, String author_keywords,
			String date_publication, String online_sibn, String full_text_url) {
		super();
		this.doi = doi;
		this.title = title;
		this.summary = summary;
		this.scope = scope;
		this.author_keywords = author_keywords;
		this.date_publication = date_publication;
		this.online_sibn = online_sibn;
		this.full_text_url = full_text_url;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthor_keywords() {
		return author_keywords;
	}

	public void setAuthor_keywords(String author_keywords) {
		this.author_keywords = author_keywords;
	}

	public String getDate_publication() {
		return date_publication;
	}

	public void setDate_publication(String date_publication) {
		this.date_publication = date_publication;
	}

	public String getOnline_sibn() {
		return online_sibn;
	}

	public void setOnline_sibn(String online_sibn) {
		this.online_sibn = online_sibn;
	}

	public String getFull_text_url() {
		return full_text_url;
	}

	public void setFull_text_url(String full_text_url) {
		this.full_text_url = full_text_url;
	}
}
