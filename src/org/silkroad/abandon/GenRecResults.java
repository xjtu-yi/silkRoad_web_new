package org.silkroad.abandon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bson.Document;
import org.silkroad.bean.*;
import org.silkroad.utility.MongoConn;
import org.silkroad.utility.MySQLConn;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;

/**
 * @author: wuke 
 * @date  : 20161125 19:15:13
 * Title  : GenerateRecResults
 * Description : according to the recommend resources list, which is an array of RecResource objects,
 *   using the res_type and res_type select the complete information of the resource, 
 *   then store the result into Mongodb's collection recResult
 */
public class GenRecResults {
	/**
	 * test    模拟生成推荐结果
	 * */
	public static void main(String[] args) {
		String user_id = "admin";
		ArrayList<RecResource> recResources = new ArrayList<RecResource>();
		
		RecResource recRes1 = new RecResource("00147584817501367cc3ae6c77a4b49a628571ac0cc6e27000", 
				"news", "http://ikcest.xjtu.edu.cn/info/1002/29560.htm");
		
		//RecResource recRes1 = new RecResource();
		//recRes1.setRes_id("00147584814940988f11ab23bb34b34a01c06ba8c7c42e5000");
		//recRes1.setRes_type("news");
		//recRes1.setUrl("http://www.sciencemag.org/news/2016/10/alien-life-could-feed-cosmic-rays");
		
		RecResource recRes2 = new RecResource("0014639682192079b9c1699be8044b0a30e2c150828f201000", 
				"ebook", "http://ebooks.spiedigitallibrary.org/book.aspx?bookid=58");
		
		//RecResource recRes2 = new RecResource();
		//recRes2.setRes_id("001463968219443d61d4e61429f455e91ce8e5a8fd61c48000");
		//recRes2.setRes_type("ebook");
		//recRes2.setUrl("http://ebooks.spiedigitallibrary.org/book.aspx?bookid=91");
		
		RecResource recRes3 = new RecResource("001476715791812fba189de04204b05a72c495c03d5049e000", 
				"uansr", "https://www.scopus.com/inward/record.uri?eid=2-s2.0-84981516063&partnerID=40&md5=dbda71792f7556068a584da301bf7633");
		
		//RecResource recRes3 = new RecResource();
		//recRes3.setRes_id("0014767157918963c7aa7d743c04fca96d34772730850dc000");
		//recRes3.setRes_type("uansr");
		//recRes3.setUrl("https://www.scopus.com/inward/record.uri?eid=2-s2.0-84964308492&partnerID=40&md5=abe17e1d57cdef1cdf8e7d0eabf0c385");
		
		RecResource recRes4 = new RecResource("00146656739590072a264cd71544f969a73a398087cb085000", 
				"conf", "http://www.zingconferences.com/conferences/neurobiology-memory-c");
		
		//RecResource recRes4 = new RecResource();
		//recRes4.setRes_id("001466567434684b1d10af16b844c30844c2bbf48e2dcf6000");
		//recRes4.setRes_type("conf");
		//recRes4.setUrl("https://www.grc.org/programs.aspx?id=12508");
				
		RecResource recRes5 = new RecResource("0014762417923130e5b90fb66da4421b908a14a30014af4000", 
				"patent", "http://ikcest.xjtu.edu.cn/patent_n_content.jsp?urltype=tree.TreeTempUrl&wbtreeid=1214&patent_number=CN105288577-A");
		
		//RecResource recRes5 = new RecResource();
		//recRes5.setRes_id("001476241792334763e38d3eaa74e2393880686179fd67d000");
		//recRes5.setRes_type("patent");
		//recRes5.setUrl("http://ikcest.xjtu.edu.cn/patent_n_content.jsp?urltype=tree.TreeTempUrl&wbtreeid=1214&patent_number=CN105288654-A");

		recResources.add(recRes1);
		recResources.add(recRes2);
		recResources.add(recRes3);
		recResources.add(recRes4);
		recResources.add(recRes5);
		
		complementResInfoStoreIntoMongo (user_id, recResources);
	}
	
	/**
	 * Complement RecResource's information and store them into Mongodb's collection recResult
	 * @param user_id
	 * @param recResources
	 */
	static void complementResInfoStoreIntoMongo (String user_id, ArrayList<RecResource> recResources) {
		// get mysql conn
		Connection mysqlConn = MySQLConn.getConn();
		
		// get mongodb collection recResult
		String databaseName = "silkRoad";
		String mongoCollectionName = "recResult";
		MongoCollection<Document> mongoCollection = MongoConn.getMongoCollection(databaseName, mongoCollectionName);
		
		// store the complete infotmation of the resource
		RecResource recResource = null;
		/*News news = new News();
		Ebook ebook = new Ebook();
		Paper paper = new Paper();
		Patent patent = new Patent();
		Conference conf = new Conference();*/
		
		// traversal the ArrayList<RecResource> recResourses
		String res_type = null;
		int i;
		for (i = 0; i < recResources.size(); i++) {
			res_type = recResources.get(i).getRes_type();
			try {
				if (res_type.equals("news")) {
					// select the complete information of the news
					recResource = selectNewsInfo(mysqlConn, recResources.get(i)); // upcasting
					
					//storeRecResourceIntoMongo(news, mongoCollection);
				}
	            else if (res_type.equals("ebook")) {
	            	recResource = selectEbookInfo(mysqlConn, recResources.get(i));
	            	
	            	//storeRecResourceIntoMongo(ebook, mongoCollection);
				}
	            else if (res_type.equals("uansr")) { // 和前端确认对应paper?
	            	recResource = selectPaperInfo(mysqlConn, recResources.get(i));
					
					//storeRecResourceIntoMongo(paper, mongoCollection);
				}
	            else if (res_type.equals("conf")) {
	            	recResource = selectConfInfo(mysqlConn, recResources.get(i));
					
					//storeRecResourceIntoMongo(conf, mongoCollection);
				}
	            else if (res_type.equals("patent")) {
	            	recResource = selectPatentInfo(mysqlConn, recResources.get(i));
					
					//storeRecResourceIntoMongo(patent, mongoCollection);
				}
				
				// store object into Mongodb collection recResult
				storeRecResourceIntoMongo(recResource, user_id, mongoCollection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param mysqlConn
	 * @param recResult
	 * @return
	 * @throws SQLException
	 */
	private static News selectNewsInfo(Connection mysqlConn, RecResource recResult) throws SQLException {
		News news = new News();
		
		String res_id = recResult.getRes_id();
		String res_type = recResult.getRes_type();
		String url = recResult.getUrl();
		
		// get the complete information of the news, and store it into one News object
		news.setRes_id(res_id);
		news.setRes_type(res_type);
		news.setUrl(url); // 必须从日志中获取
		
		String sql = "select title,content,image_url,post_at "
				+ "from crawler_repo where id=\"" + res_id+"\";";
		PreparedStatement pstmt = mysqlConn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			news.setTitle(rs.getString(1));
			news.setContent(rs.getString(2));
			news.setImg_url(rs.getString(3));
			news.setDate(rs.getString(4));
		}
		return news;
	}
	
	/**
	 * 
	 * @param mysqlConn
	 * @param recResult
	 * @return
	 * @throws SQLException
	 */
	private static Ebook selectEbookInfo(Connection mysqlConn, RecResource recResult) throws SQLException {
		Ebook ebook = new Ebook();
		
		String res_id = recResult.getRes_id();
		String res_type = recResult.getRes_type();
		String url = recResult.getUrl();
		
		// get the complete information of the ebook, and store it into one Ebook object
		ebook.setRes_id(res_id);
		ebook.setRes_type(res_type);
		ebook.setUrl(url); // 可以考虑从Mysql中读，防止日志出错
		
		String sql = "select isbn,eisbn,author,title,page_counter,publisher,year,img_url,language "
				+ "from ebook_repo where id=\"" + res_id+"\";";
		PreparedStatement pstmt = mysqlConn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ebook.setIsbn(rs.getString(1));
			ebook.setEisbn(rs.getString(2));
			ebook.setAuthor(rs.getString(3));
			ebook.setTitle(rs.getString(4));
			ebook.setPages(rs.getString(5));
			ebook.setPublisher(rs.getString(6));
			ebook.setYear(rs.getString(7));
			ebook.setImg_url(rs.getString(8));
			ebook.setLanguage(rs.getString(9));
		}
		return ebook;
	}
	
	/**
	 * 
	 * @param mysqlConn
	 * @param recResult
	 * @return
	 * @throws SQLException
	 */
	private static Paper selectPaperInfo(Connection mysqlConn, RecResource recResult) throws SQLException {
		Paper paper = new Paper();
		
		String res_id = recResult.getRes_id();
		String res_type = recResult.getRes_type();
		String url = recResult.getUrl();
		
		// get the complete information of the paper, and store it into one Paper object
		paper.setRes_id(res_id);
		paper.setRes_type(res_type);
		paper.setUrl(url); // 可以考虑从Mysql中读，防止日志出错
		
		String sql = "select title,year,volume,doi,summary "
				+ "from uansr_repo where id=\"" + res_id+"\";";
		PreparedStatement pstmt = mysqlConn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			paper.setTitle(rs.getString(1));
			paper.setYear(rs.getString(2));
			paper.setVolume(rs.getString(3));
			paper.setDoi(rs.getString(4));
			paper.setSummary(rs.getString(5));
		}
		return paper;
	}
	
	/**
	 * 
	 * @param mysqlConn
	 * @param recResult
	 * @return
	 * @throws SQLException
	 */
	private static Conference selectConfInfo(Connection mysqlConn, RecResource recResult) throws SQLException {
		Conference conf = new Conference();
		
		String res_id = recResult.getRes_id();
		String res_type = recResult.getRes_type();
		String url = recResult.getUrl();
		
		// get the complete information of the conf, and store it into one Conference object
		conf.setRes_id(res_id);
		conf.setRes_type(res_type);
		conf.setUrl(url); // 可以考虑从Mysql中读，防止日志出错
		
		String sql = "select conference_name,organizer,start_date,broad_theme "
				+ "from conf_repo where id=\"" + res_id+"\";";
		PreparedStatement pstmt = mysqlConn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			conf.setConference_name(rs.getString(1));
			conf.setOrganizer(rs.getString(2));
			conf.setStart_date(rs.getString(3));
			conf.setBroad_theme(rs.getString(4));
		}
		return conf;
	}
	
	/**
	 * 
	 * @param mysqlConn
	 * @param recResult
	 * @return
	 * @throws SQLException
	 */
	private static Patent selectPatentInfo(Connection mysqlConn, RecResource recResult) throws SQLException {
		Patent patent = new Patent();
		
		String res_id = recResult.getRes_id();
		String res_type = recResult.getRes_type();
		String url = recResult.getUrl();
		
		// get the complete information of the patent, and store it into one Patent object
		patent.setRes_id(res_id);
		patent.setRes_type(res_type);
		patent.setUrl(url); // 可以考虑从Mysql中读，防止日志出错
		
		String sql = "select patent_number,title,inventor,assignee_name_or_code "
				+ "from patent_repo where id=\"" + res_id+"\";";
		PreparedStatement pstmt = mysqlConn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			patent.setPatent_number(rs.getString(1));
			patent.setTitle(rs.getString(2));
			patent.setInventor(rs.getString(3));
			patent.setAssignee_name_or_code(rs.getString(4));
		}
		return patent;
	}
	
	/**
	 * Store object RecResource into Mongodb collection recResult
	 * @param recResource
	 * @param user_id
	 * @param mongoCollection
	 */
	private static void storeRecResourceIntoMongo(RecResource recResource, String user_id, MongoCollection<Document> mongoCollection) {
		// Gson提供了fromJson() 和toJson() 两个直接用于解析和生成的方法，前者实现反序列化，后者实现了序列化
		Gson gson = new Gson();	
		String json = gson.toJson(recResource);
		
		Document document = Document.parse(json);
		document.append("user_id", user_id);
		mongoCollection.insertOne(document);
	}
}