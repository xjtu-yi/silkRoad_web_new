package org.silkroad.abandon;

import org.silkroad.bean.Resource;

public class Paper extends Resource {
	private String title;
	private String year;
	private String volume;
	private String doi;
	private String summary;

	public Paper() {

	}

	public Paper(String title, String year, String volume, String doi, String summary) {
		super();
		this.title = title;
		this.year = year;
		this.volume = volume;
		this.doi = doi;
		this.summary = summary;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	public String getVolume() {
		return volume;
	}

	public String getDoi() {
		return doi;
	}

	public String getSummary() {
		return summary;
	}

}
