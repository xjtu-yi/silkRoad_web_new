package org.silkroad.bean;

public class Ebook extends RecResource {
	private String isbn;
	private String eisbn;
	private String author;
	private String title;
	private String pages;
	private String publisher;
	private String year;
	private String img_url;
	private String language;

	public Ebook() {

	}

	public Ebook(String isbn, String eisbn, String author, String title, String pages, String publisher, String year,
			String img_url, String language) {
		super();
		this.isbn = isbn;
		this.eisbn = eisbn;
		this.author = author;
		this.title = title;
		this.pages = pages;
		this.publisher = publisher;
		this.year = year;
		this.img_url = img_url;
		this.language = language;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setEisbn(String eisbn) {
		this.eisbn = eisbn;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getEisbn() {
		return eisbn;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getPages() {
		return pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getYear() {
		return year;
	}

	public String getImg_url() {
		return img_url;
	}

	public String getLanguage() {
		return language;
	}

}
