package com.event.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.dao.EventDao;
import com.event.model.Event;

/**
 * Servlet implementation class EventSerchServlet
 */
@WebServlet("/EventSearchServlet")
public class EventSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventSearchServlet() {
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
		System.out.println(request.getParameter("page"));
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String keyWord = request.getParameter("keyWord");
		List<Event> events = new EventDao().search(startTime, endTime, keyWord);
		int count =  new EventDao().searchTotal(startTime, endTime, keyWord);
		StringBuffer sb = new StringBuffer();
		String trFormat1 = "{\"code\":0,\"msg\":\"\",\"count\":%d,\"data\":[";
		String tr1 = String.format(trFormat1, count);
		sb.append(tr1);
		
		String trFormat = "{\"id\":%d,\"occTime\":\"%s\",\"locale\":\"%s\",\"department\":\"%s\",\"level\":\"%s\",\"discPerson\":\"%s\",\"discTime\":\"%s\",\"handlePerson\":\"%s\",\"effBus\":\"%s\",\"incidence\":\"%s\",\"effTime\":\"%s\"},";
		
		for (Event event : events) {
			String tr = String.format(trFormat, event.getId(), event.getOccTime(), event.getLocale(), event.getDepartment(), event.getLevel(), event.getDiscPerson(), event.getDiscTime(), event.getHandlePerson(), event.getEffBus(), event.getIncidence(), event.getEffTime());
			sb.append(tr);
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		response.getWriter().write(sb.toString());
	}

}
