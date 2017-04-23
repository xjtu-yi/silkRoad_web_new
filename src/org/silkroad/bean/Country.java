package org.silkroad.bean;

/**
 * @author : wuke
 * @date : 2017年4月21日下午5:15:48 
 * Title : Country 
 * Description : 国情咨文数据库(Nation Conditions)
 */
public class Country extends RecResource {
	private String title;
	private String summary; // "abstract" in MySQL countries_repo
	private String pub_place; // "publish_place" in MySQL countries_repo
	private String pub_date;
	private int year;
	private String palce; // "subjects" in MySQL

	public Country() {

	}

	public Country(String title, String summary, String pub_place, String pub_date, int year, String palce) {
		super();
		this.title = title;
		this.summary = summary;
		this.pub_place = pub_place;
		this.pub_date = pub_date;
		this.year = year;
		this.palce = palce;
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

	public String getPub_place() {
		return pub_place;
	}

	public void setPub_place(String pub_place) {
		this.pub_place = pub_place;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPalce() {
		return palce;
	}

	public void setPalce(String palce) {
		this.palce = palce;
	}

}