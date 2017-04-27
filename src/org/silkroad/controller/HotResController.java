package org.silkroad.controller;

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
import com.mongodb.client.MongoCollection;

/**
 * Servlet implementation class StudentHotController
 */
@WebServlet("/HotResController")
public class HotResController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotResController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get userId and user_type
		String user_type = request.getParameter("user_type");
				
		// Query from MongoDB
		String collectionName = user_type + "_hot_complete"; 
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		List<Document> docList = null;
		docList = collection.find().into(new ArrayList<Document>());
		
		// transform List<Document> into String
		String json = new Gson().toJson(docList);
		
		// Respond to the request
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().write(json); 
	}

}
