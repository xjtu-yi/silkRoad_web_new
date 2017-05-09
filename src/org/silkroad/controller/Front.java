package org.silkroad.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Front
 */
@WebServlet("/Front")
public class Front extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
        String type = request.getParameter("type");
        
        if(type.equals("4")) // 
        	response.sendRedirect("http://personalize.silkroadst.ikcest.org/silkAdmin/index.html");
        else {
			String user_id = null;
			switch(type) {
			case "1":
				user_id = "453"; // student
				break;
			case "2":
				user_id = "101"; // engineer
				break;
			case "3":
				user_id = "104"; // scholar
				break;
			default:
				user_id = "453";
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_type", type);
		    
		    request.getRequestDispatcher("profiles.html").forward(request, response);
	    }
	}
}
