package org.silkroad.abandon;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import org.silkroad.utility.MongoConn;

/**
 * @author: wuke 
 * @date  : 20161118 16:14:40
 * Title  : GenerateClickLogs
 * Description : logs(click_time, user_id, res_id, res_type, url)
 */
public class GenClickLogs {
	public static void main(String[] args) {
		try {
			// 获取集合
			MongoCollection<Document> collectionLogs = MongoConn.getMongoCollection("logs");
			System.out.println("Successfully get collection logs in database logs");
			
			// 插入文档
			for(int i = 0; i < 1000; i++) {
				Document document1 = new Document("click_time", "2016-11-18 13:02:47").
						append("res_id", "001466567429271808dfdc3bd64488981b26cef7e209a14000").
						append("res_type", "conf").
						append("url", "http://ikcest.xjtu.edu.cn/Symposium_con.jsp?urltype=tree.TreeTempUrl&"
								+ "wbtreeid=1301&id=001466567429271808dfdc3bd64488981b26cef7e209a14000");
				Document document3 = new Document("click_time", "2016-11-16 13:02:47").
						append("res_id", "00147584817501367cc3ae6c77a4b49a628571ac0cc6e27000").
						append("res_type", "news").
						append("url", "http://ikcest.xjtu.edu.cn/info/1002/29560.htm");
				Document document5 = new Document("click_time", "2016-11-17 13:02:47").
						append("res_id", "0014639682194240f003daa7af14f9eb9e2bb849d698313000").
						append("res_type", "ebook").
						append("url", "http://ebooks.spiedigitallibrary.org/book.aspx?bookid=100");
				Document document7 = new Document("click_time", "2016-10-01 13:02:47").
						append("res_id", "0014762417923130e5b90fb66da4421b908a14a30014af4000").
						append("res_type", "patent").
						append("url", "http://ikcest.xjtu.edu.cn/patent_n_content.jsp?urltype=tree.TreeTempUrl&"
								+ "wbtreeid=1214&patent_number=CN105288577-A");
				Document document9 = new Document("click_time", "2016-11-11 13:02:47").
						append("res_id", "001476715791812fba189de04204b05a72c495c03d5049e000").
						append("res_type", "paper").
						append("url", "https://www.scopus.com/record/display.uri?eid=2-s2.0-84981516063&"
								+ "origin=inward&txGid=AC683D5889E9A0D124BB9B7D8811C7F0.wsnAw8kcdt7IPYLO0V48gA%3a15");
				collectionLogs.insertOne(document1);
				collectionLogs.insertOne(document3);
				collectionLogs.insertOne(document5);
				collectionLogs.insertOne(document7);
				collectionLogs.insertOne(document9);
			}
			for(int i = 0; i < 800; i++) {
				Document document2 = new Document("click_time", "2016-11-17 13:02:47").
						append("res_id", "001466567415065addba35a806341ea8a3b32b45f9c5dc7000").
						append("res_type", "conf").				
						append("url", "http://ikcest.xjtu.edu.cn/Symposium_con.jsp?urltype=tree.TreeTempUrl&"
								+ "wbtreeid=1301&id=001466567415065addba35a806341ea8a3b32b45f9c5dc7000");
				Document document4 = new Document("click_time", "2016-10-4 13:02:47").
						append("res_id", "00147584816433907fc8f043bde4aa5ba46866a1c6f502d000").
						append("res_type", "news").
						append("url", "http://ikcest.xjtu.edu.cn/info/1002/29557.htm");
				Document document6 = new Document("click_time", "2016-11-09 13:02:47").
						append("res_id", "0014639682192935dbd2bfa076c4b218cc3a32838613821000").
						append("res_type", "ebook").
						append("url", "http://ebooks.spiedigitallibrary.org/book.aspx?bookid=207");	
				Document document8 = new Document("click_time", "2016-11-06 13:02:47").
						append("res_id", "001476241792334763e38d3eaa74e2393880686179fd67d000").
						append("res_type", "patent").
						append("url", "http://ikcest.xjtu.edu.cn/patent_n_content.jsp?urltype=tree.TreeTempUrl&"
								+ "wbtreeid=1214&patent_number=CN105288654-A");						
				Document document10 = new Document("click_time", "2016-11-16 13:02:47").
						append("res_id", "001476715791856844a6d0d69434742b317784edbc23577000").
						append("res_type", "paper").
						append("url", "https://www.scopus.com/record/display.uri?eid=2-s2.0-84981297906&"
								+ "origin=inward&txGid=AC683D5889E9A0D124BB9B7D8811C7F0.wsnAw8kcdt7IPYLO0V48gA%3a30");
				collectionLogs.insertOne(document2);
				collectionLogs.insertOne(document4);
				collectionLogs.insertOne(document6);
				collectionLogs.insertOne(document8);
				collectionLogs.insertOne(document10);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
