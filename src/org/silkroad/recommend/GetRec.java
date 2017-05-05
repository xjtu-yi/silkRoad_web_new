package org.silkroad.recommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.silkroad.bean.Company;
import org.silkroad.bean.Country;
import org.silkroad.bean.PeBook;
import org.silkroad.bean.Regulation;
import org.silkroad.bean.Uansr;
import org.silkroad.bean.UeBook;
import org.silkroad.utility.MySQLConn;

import com.google.gson.Gson;

public class GetRec {


	public static String getRec(Integer res_type_id, String user_id) throws Exception {
		Connection MYSQLCONN = MySQLConn.getConn();
		String tableName = "rec";

		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String res_id = null;
		Object times = null;
		String res_ids = null;
		sql = "select recommend_ids from " + tableName + " where user_id=\"" + user_id + "\" and res_type_id ="
				+ res_type_id;
		statement = MYSQLCONN.prepareStatement(sql);
		rs = statement.executeQuery(sql);
		
		while (rs.next()) {
			res_ids = rs.getString(1);
			// System.out.println(res_ids);
		}
		Gson gson = new Gson();
		List<String> idlist = new ArrayList<String>();

		idlist = gson.fromJson(res_ids, idlist.getClass());
		rs.close();
		statement.close();
		MYSQLCONN.close();
		
		switch (res_type_id) {
		case 1:
			List<Company> companies = new ArrayList<>();
			for (String a : idlist) {
				companies.add(getCompanies(a));
			}
			return gson.toJson(companies);
		case 2:
			List<Country> countries = new ArrayList<>();
			for (String a : idlist) {
				countries.add(getCountries(a));
			}
			return gson.toJson(countries);
		case 3:
			List<PeBook> peBooks = new ArrayList<>();
			for (String a : idlist) {
				peBooks.add(getPebook(a));
			}
			return gson.toJson(peBooks);
		case 4:
			List<Regulation> regulations = new ArrayList<>();
			for (String a : idlist) {
				regulations.add(getRegulation(a));
			}
			return gson.toJson(regulations);
		case 5:
			List<Uansr> uansrs = new ArrayList<>();
			for (String a : idlist) {
				uansrs.add(getUansr(a));
			}
			return gson.toJson(uansrs);
		case 6:
			List<UeBook> ueBooks = new ArrayList<>();
			for (String a : idlist) {
				ueBooks.add(getUebook(a));
			}
			return gson.toJson(ueBooks);
		default:
			break;
		}
		
		
		return null;
	}

	public static Company getCompanies(String res_id) throws Exception {
		Connection MYSQLCONN = MySQLConn.getConn();
		String sql = null;
		String tableName = "companies_repo";
		PreparedStatement statement = null;
		ResultSet rs = null;

		sql = "select name, summary, location, country, doc_url, rankings from " + tableName + " where id=\"" + res_id
				+ "\"";
		statement = MYSQLCONN.prepareStatement(sql);
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			Company company = new Company();
			company.setRes_id(res_id);
			company.setRes_type("company");
			company.setName(rs.getString(1));
			company.setSummary(rs.getString(2));
			company.setLocation(rs.getString(3));
			company.setCountry(rs.getString(4));
			company.setUrl(rs.getString(5));
			company.setRankings(rs.getString(6));
			return company;
		}
		rs.close();
		statement.close();
		MYSQLCONN.close();
		return null;

	}

	public static Country getCountries(String res_id) throws Exception {
		Connection MYSQLCONN = MySQLConn.getConn();

		String sql = null;
		String tableName = "countries_repo";
		PreparedStatement statement = null;
		ResultSet rs = null;

		sql = "select title, abstract, publish_place, pub_date, doc_url, subjects from " + tableName + " where id=\""
				+ res_id + "\"";
		statement = MYSQLCONN.prepareStatement(sql);
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			Country country = new Country();
			country.setRes_id(res_id);
			country.setRes_type("country");
			country.setTitle(rs.getString(1));
			country.setSummary(rs.getString(2));
			country.setPub_place(rs.getString(3));
			country.setPub_date(rs.getString(4));
			country.setUrl(rs.getString(5));
			country.setPalce(rs.getString(6));
			return country;
		}
		rs.close();
		statement.close();
		MYSQLCONN.close();
		return null;
	}

	public static PeBook getPebook(String res_id) throws Exception {
		Connection MYSQLCONN = MySQLConn.getConn();

		String sql = null;
		String tableName = "pebook_repo";
		PreparedStatement statement = null;
		ResultSet rs = null;

		sql = "select  title, doi, summary,text_url from " + tableName + " where id=\"" + res_id + "\"";
		statement = MYSQLCONN.prepareStatement(sql);
		rs = statement.executeQuery(sql);

		// complete one Document
		while (rs.next()) {
			PeBook peBook = new PeBook();
			peBook.setRes_id(res_id);
			peBook.setRes_type("pebook");
			peBook.setTitle(rs.getString(1));
			peBook.setDoi(rs.getString(2));
			peBook.setSummary(rs.getString(3));
			peBook.setUrl(rs.getString(4));
			return peBook;
		}
		rs.close();
		statement.close();
		MYSQLCONN.close();
		return null;
	}

	public static Regulation getRegulation(String res_id) throws Exception {
		Connection MYSQLCONN = MySQLConn.getConn();

		String sql = null;
		String tableName = "regulation_repo";
		PreparedStatement statement = null;
		ResultSet rs = null;

		sql = "select doi, url, title, abstract, scope, purpose, date_publication, full_text from " + tableName
				+ " where id=\"" + res_id + "\"";
		statement = MYSQLCONN.prepareStatement(sql);
		rs = statement.executeQuery(sql);

		// complete one Document

		while (rs.next()) {
			Regulation regulation = new Regulation();
			regulation.setRes_id(res_id);
			regulation.setRes_type("regulation");
			regulation.setDoi(rs.getString(1));
			regulation.setUrl(rs.getString(2));
			regulation.setTitle(rs.getString(3));
			regulation.setSummary(rs.getString(4));
			regulation.setScope(rs.getString(5));
			regulation.setPurpose(rs.getString(6));
			regulation.setDate_publication(rs.getString(7));
			regulation.setFull_text_url(rs.getString(8));
			return regulation;
		}
		rs.close();
		statement.close();
		MYSQLCONN.close();
		return null;
	}

	public static UeBook getUebook(String res_id) throws Exception {
		Connection MYSQLCONN = MySQLConn.getConn();

		String sql = null;
		String tableName = "uebook_repo";
		PreparedStatement statement = null;
		ResultSet rs = null;

		sql = "select author, title, year, url, img_url, summary, nation from " + tableName + " where id=\"" + res_id + "\"";
		statement = MYSQLCONN.prepareStatement(sql);
		rs = statement.executeQuery(sql);
		
		while (rs.next()) {
			UeBook ueBook = new UeBook();
			ueBook.setRes_id(res_id);
			ueBook.setRes_type("uebook");
			ueBook.setAuthor(rs.getString(1));
			ueBook.setTitle(rs.getString(2));
			ueBook.setYear(rs.getString(3));
			ueBook.setUrl(rs.getString(4));
			ueBook.setImg_url(rs.getString(5));
			ueBook.setSummary(rs.getString(6));
			ueBook.setNation(rs.getString(7));
			return ueBook;
		}
		rs.close();
		statement.close();
		MYSQLCONN.close();
		return null;
	}

	public static Uansr getUansr(String res_id) throws Exception {
		Connection MYSQLCONN = MySQLConn.getConn();

		String sql = null;
		String tableName = "uansr_repo";
		PreparedStatement statement = null;
		ResultSet rs = null;

		sql = "select author, title, year, doi, url, summary from " + tableName + " where id=\"" + res_id + "\"";
		statement = MYSQLCONN.prepareStatement(sql);
		rs = statement.executeQuery(sql);

		while (rs.next()) {
			Uansr uansr = new Uansr();
			uansr.setAuthor(rs.getString(1));
			uansr.setTitle(rs.getString(2));
			uansr.setYear(rs.getString(3));
			uansr.setDoi(rs.getString(4));
			uansr.setUrl(rs.getString(5));
			uansr.setSummary(rs.getString(6));
			return uansr;
		}
		rs.close();
		statement.close();
		MYSQLCONN.close();
		return null;
	}
}
