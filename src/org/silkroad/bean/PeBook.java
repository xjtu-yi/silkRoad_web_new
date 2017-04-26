package org.silkroad.bean;

/**
 * @author : wuke
 * @date : 2017��4��21������9:11:58 
 * Title : PeBook 
 * Description : �˿����ݿ�(pebook)
 */
public class PeBook extends Resource{
	private String title;
	private String summary;
	private String book_title;
	private String author;
	private String doi;
	private String year;

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public PeBook() {

	}

	public PeBook(String title, String summary, String book_title) {
		super();
		this.title = title;
		this.summary = summary;
		this.book_title = book_title;
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

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

}
