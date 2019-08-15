package com.change.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.change.dao.ChangeDao;
import com.change.model.Change;

/**
 * Servlet implementation class ChangeSearchServlet
 */
@WebServlet("/ChangeSearchServlet")
public class ChangeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeSearchServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String keyWord = request.getParameter("keyWord");
		List<Change> changes = new ChangeDao().search(startTime, endTime, keyWord);
		int count =  new ChangeDao().searchTotal(startTime, endTime, keyWord);
		StringBuffer sb = new StringBuffer();
		String trFormat1 = "{\"code\":0,\"msg\":\"\",\"count\":%d,\"data\":[";
		String tr1 = String.format(trFormat1, count);
		sb.append(tr1);
		
		String trFormat = "{\"id\":%d,\"bglx\":\"%s\",\"bgxm\":\"%s\",\"bgdj\":\"%s\",\"jhssr\":\"%s\",\"jhkssj\":\"%s\",\"jhjssj\":\"%s\",\"bgsqr\":\"%s\",\"sqsj\":\"%s\",\"bgyy\":\"%s\",\"bgfa\":\"%s\",\"bghtfa\":\"%s\",\"tjsj\":\"%s\"},";
		
		for (Change change : changes) {
			String tr = String.format(trFormat, change.getId(), change.getBglx(), change.getBgxm(), change.getBgdj(), change.getJhssr(), change.getJhkssj(), change.getJhjssj(), change.getBgsqr(), change.getSqsj(), change.getBgyy(), change.getBgfa(), change.getBghtfa(), change.getTjsj());
			sb.append(tr);
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		response.getWriter().write(sb.toString());
	}

}
