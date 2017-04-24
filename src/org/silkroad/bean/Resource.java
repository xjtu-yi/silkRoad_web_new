package org.silkroad.bean;

public class Resource {
	private String res_id;
	private String res_type;
	private String url;

	public Resource() {

	}

	public Resource(String res_id, String res_type, String url) {
		super();
		this.res_id = res_id;
		this.res_type = res_type;
		this.url = url;
	}

	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}

	public void setRes_type(String res_type) {
		this.res_type = res_type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRes_id() {
		return res_id;
	}

	public String getRes_type() {
		return res_type;
	}

	public String getUrl() {
		return url;
	}
}
