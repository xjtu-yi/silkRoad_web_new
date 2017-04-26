package org.silkroad.bean;

/**
 * @author : wuke
 * @date : 2017��4��21������9:24:25 
 * Title : Uansr 
 * Description : �����Ƽ����ݿ�(Education of Science)
 */
public class Uansr extends Resource {
	private String author;
	private String title;
	private String year;
	private String summary;
	private String doi;
	

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

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
