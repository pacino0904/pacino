package com.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.change.dao.ChangeDao;
import com.event.dao.EventDao;
import com.event.model.Event;
import com.google.gson.Gson;
import com.member.dao.MemberDao; 

/**
 * Servlet implementation class IndexData
 */
public class IndexData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @return 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int eventSum = new EventDao().total();
		int changeSum = new ChangeDao().total();
		int memberSum = new MemberDao().total();
		Map indexdata = new HashMap();
		indexdata.put("eventSum", eventSum);
		indexdata.put("changeSum", changeSum);
		indexdata.put("memberSum", memberSum);
		out.println(new Gson().toJson(indexdata));
	}

}
