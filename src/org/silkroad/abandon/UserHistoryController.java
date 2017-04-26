package org.silkroad.abandon;

import static com.mongodb.client.model.Filters.eq;

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
 * @date : 2017年4月23日下午3:30:06 
 * Title : UserViewedHistory 
 * Description : // package org.silkroad.controller;
 */
@WebServlet("/UserViewedHistory")
public class UserHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get userId and res_type
		String res_type = request.getParameter("res_type");
		String user_id = request.getParameter("user_id");
		//String user_id = request.getSession().getAttribute("user_id").toString(); 		

		// Query from MongoDB
		String collectionName = "user_viewed_" + res_type + "_complete";
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		BasicDBObject sort = new BasicDBObject();
		sort.put("times", -1); // descending by "times"

		List<Document> docsArr = collection.find(eq("user_id", user_id)).sort(sort).limit(10).into(new ArrayList<Document>());

		String json = new Gson().toJson(docsArr);

		// Respond to the request
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*"); // 访问控制
		response.getWriter().write(json);
	}
}