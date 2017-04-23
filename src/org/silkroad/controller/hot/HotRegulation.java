package org.silkroad.controller.hot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.silkroad.utility.MongoConn;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

/**
* @author : wuke
* @date   : 2017年4月22日下午6:22:45
* Title   : HotRegulation
* Description : 
*/
@WebServlet("/HotRegulation")
public class HotRegulation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get userId and user_type
		String user_id = request.getParameter("user_id");
		String user_type = request.getParameter("user_type");
		
		// Query TOP 5 Documents from MongoDB
		String collectionName = user_type + "_regulation_times"; 
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		BasicDBObject sort = new BasicDBObject();
		sort.put("times", -1); // -1, descending
		
		List<Document> docList = null;
		docList = collection.find().sort(sort).limit(5).into(new ArrayList<Document>());
		
		// transform List<Document> into String
		String json = new Gson().toJson(docList);
		
		// Respond to the request
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().write(json); 
	}

}
