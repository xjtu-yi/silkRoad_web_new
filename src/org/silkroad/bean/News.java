package org.silkroad.bean;

public class News extends RecResource {
	private String title;
	private String content;
	private String img_url;
	private String date;

	public News() {

	}

	public News(String title, String content, String img_url, String date) {
		super();
		this.title = title;
		this.content = content;
		this.img_url = img_url;
		this.date = date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getImg_url() {
		return img_url;
	}

	public String getDate() {
		return date;
	}

}
