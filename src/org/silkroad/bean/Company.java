package org.silkroad.bean;

/**
 * @author : wuke
 * @date : 2017年4月21日下午9:01:25 
 * Title : Company 
 * Description : 工业经济数据库(Fortune Global 500)
 */
public class Company extends Resource{
	private String name;
	private String summary;
	private String location;
	private String country;
	private String rankings;

	public Company() {
	}

	public Company(String name, String summary, String location, String country, String rankings) {
		super();
		this.name = name;
		this.summary = summary;
		this.location = location;
		this.country = country;
		this.rankings = rankings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRankings() {
		return rankings;
	}

	public void setRankings(String rankings) {
		this.rankings = rankings;
	}

}
