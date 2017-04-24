package org.silkroad.bean;

/**
 * @author : wuke
 * @date : 2017年4月21日下午9:11:58 
 * Title : PeBook 
 * Description : 人口数据库(pebook)
 */
public class PeBook extends Resource{
	private String title;
	private String summary;
	private String book_title;

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
