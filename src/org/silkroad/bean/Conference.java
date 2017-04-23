package org.silkroad.bean;

public class Conference extends RecResource {
	private String conference_name;
	private String organizer;
	private String start_date;
	private String broad_theme;

	public Conference() {

	}

	public Conference(String conference_name, String organizer, String start_date, String broad_theme) {
		super();
		this.conference_name = conference_name;
		this.organizer = organizer;
		this.start_date = start_date;
		this.broad_theme = broad_theme;
	}

	public void setConference_name(String conference_name) {
		this.conference_name = conference_name;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public void setBroad_theme(String broad_theme) {
		this.broad_theme = broad_theme;
	}

	public String getConference_name() {
		return conference_name;
	}

	public String getOrganizer() {
		return organizer;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getBroad_theme() {
		return broad_theme;
	}
}
