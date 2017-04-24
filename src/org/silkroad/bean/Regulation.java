package org.silkroad.bean;

/**
 * @author : wuke
 * @date : 2017年4月21日下午9:16:28 
 * Title : Regulation 
 * Description : 政策法规数据库(IEEE)
 */
public class Regulation extends Resource {
	private String doi;
	private String title;
	private String summary; // "abstract" in MySQL
	private String scope;
	private String purpose;
	private String date_publication;
	private String full_text_url; // "full_text" in MySQL

	public Regulation() {
	}

	public Regulation(String doi, String title, String summary, String scope, String purpose,
			String date_publication, String full_text_url) {
		super();
		this.doi = doi;
		this.title = title;
		this.summary = summary;
		this.scope = scope;
		this.purpose = purpose;
		this.date_publication = date_publication;
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getDate_publication() {
		return date_publication;
	}

	public void setDate_publication(String date_publication) {
		this.date_publication = date_publication;
	}

	public String getFull_text_url() {
		return full_text_url;
	}

	public void setFull_text_url(String full_text_url) {
		this.full_text_url = full_text_url;
	}
}
