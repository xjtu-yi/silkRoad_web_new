package org.silkroad.controller;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.silkroad.utility.MongoConn;
import org.silkroad.utility.MySQLConn;

import com.mongodb.client.MongoCollection;

/**
 * @author : wuke
 * @date : 2017年4月23日下午3:30:06 Title : UserViewedHistory Description :
 */
@WebServlet("/UserViewedHistory")
public class UserViewedHistoryController extends HttpServlet {
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
		// String user_id = request.getParameter("user_id");
		String user_id = request.getSession().getAttribute("user_id").toString(); // 
		String res_type = request.getParameter("res_type");
		
		user_id = "888";

		// Query from MongoDB
		String collectionName = "user_viewed_" + res_type;
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);

		Document doc = collection.find(eq("user_id", user_id)).first();

		String json = doc.toJson();

		// Respond to the request
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*"); // 控制访问
		response.getWriter().write(json);
	}
}
