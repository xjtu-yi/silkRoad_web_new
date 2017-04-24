package org.silkroad.bean;

/**
 * @author : wuke
 * @date : 2017年4月21日下午9:31:00 
 * Title : UeBook 
 * Description : 历史文化数据库(History and Culture)
 */
public class UeBook extends Resource {
	private String author;
	private String title;
	private String year;
	private String img_url;
	private String summary;
	private String nation;

	public UeBook() {
	}

	public UeBook(String author, String title, String year, String img_url, String summary, String nation) {
		super();
		this.author = author;
		this.title = title;
		this.year = year;
		this.img_url = img_url;
		this.summary = summary;
		this.nation = nation;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
}
