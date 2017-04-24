package org.silkroad.bean;

/**
 * @author : wuke
 * @date : 2017年4月21日下午9:24:25 
 * Title : Uansr 
 * Description : 教育科技数据库(Education of Science)
 */
public class Uansr extends Resource {
	private String author;
	private String title;
	private String year;
	private String summary;

	public Uansr() {
	}

	public Uansr(String author, String title, String year, String summary) {
		super();
		this.author = author;
		this.title = title;
		this.year = year;
		this.summary = summary;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
