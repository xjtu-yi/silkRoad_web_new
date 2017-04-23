package org.silkroad.bean;

public class Patent extends RecResource {
	private String patent_number;
	private String title;
	private String inventor;
	private String assignee_name_or_code;

	public Patent() {
		
	}

	public Patent(String patent_number, String title, String inventor, String assignee_name_or_code) {
		super();
		this.patent_number = patent_number;
		this.title = title;
		this.inventor = inventor;
		this.assignee_name_or_code = assignee_name_or_code;
	}

	public void setPatent_number(String patent_number) {
		this.patent_number = patent_number;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setInventor(String inventor) {
		this.inventor = inventor;
	}

	public void setAssignee_name_or_code(String assignee_name_or_code) {
		this.assignee_name_or_code = assignee_name_or_code;
	}

	public String getPatent_number() {
		return patent_number;
	}

	public String getTitle() {
		return title;
	}

	public String getInventor() {
		return inventor;
	}

	public String getAssignee_name_or_code() {
		return assignee_name_or_code;
	}
}
