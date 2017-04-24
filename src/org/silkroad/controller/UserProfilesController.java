package org.silkroad.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.silkroad.utility.MongoConn;
import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.MongoCollection;

/**
* @author : wuke
* @date   : 2017年4月23日下午3:25:06
* Title   : UserProfiles
* Description : 
*/
@WebServlet("/UserProfiles")
public class UserProfilesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get userId
		String user_id = request.getParameter("user_id");
		
		// Query from MongoDB
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", "user_viewed_times");
		
		Document doc = collection.find(eq("user_id", user_id)).first();
		
		String json = doc.toJson();
		
		// Respond to the request
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().write(json);
	}
}
