package org.silkroad.controller.jump;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HistoryForward
 */
@WebServlet("/HistoryForward")
public class HistoryForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		String user_id = request.getParameter("user_id");
		String res_type = request.getParameter("res_type");
		
		request.setAttribute("user_id", user_id);
		request.setAttribute("res_type", res_type);
		
		// System.out.println("HistoryForward " + user_id);
		
		switch(res_type) {
		case "company" :
			request.getRequestDispatcher("history/companyHistory.jsp").forward(request, response);
			break;
		case "country" :
			request.getRequestDispatcher("history/countryHistory.jsp").forward(request, response);
			break;
		case "pebook" :
			request.getRequestDispatcher("history/pebookHistory.jsp").forward(request, response);
			break;
		case "regulation" :
			request.getRequestDispatcher("history/regulationHistory.jsp").forward(request, response);
			break;
		case "uansr" :
			request.getRequestDispatcher("history/uansrHistory.jsp").forward(request, response);
			break;
		case "uebook" :
			request.getRequestDispatcher("history/uebookHistory.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}