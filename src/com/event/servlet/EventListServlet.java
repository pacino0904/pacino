package com.event.servlet;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.shiro.SecurityUtils;

import com.event.model.Event;
import com.log.dao.LogDao;
import com.log.model.Log;
import com.event.dao.EventDao;

/**
 * Servlet implementation class EventListServlet
 */
@WebServlet("/EventListServlet")
public class EventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventListServlet() {
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
		response.setContentType("text/html; charset=UTF-8");
		
		List<Event> events = new EventDao().list();
		int count = new EventDao().total();
		StringBuffer sb = new StringBuffer();
		String trFormat1 = "{\"code\":0,\"msg\":\"\",\"count\":%d,\"data\":[";
		String tr1 = String.format(trFormat1, count);
		sb.append(tr1);
		
		String trFormat = "{\"id\":%d,\"occTime\":\"%s\",\"locale\":\"%s\",\"department\":\"%s\",\"level\":\"%s\",\"discPerson\":\"%s\",\"discTime\":\"%s\",\"handlePerson\":\"%s\",\"effBus\":\"%s\",\"incidence\":\"%s\",\"effTime\":\"%s\",\"eventNumber\":\"%s\"},";
		
		for (Event event : events) {
			String tr = String.format(trFormat, event.getId(), event.getOccTime(), event.getLocale(), event.getDepartment(), event.getLevel(), event.getDiscPerson(), event.getDiscTime(), event.getHandlePerson(), event.getEffBus(), event.getIncidence(), event.getEffTime(), event.getEventNumber());
			sb.append(tr);
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		response.getWriter().write(sb.toString());
		
//		String name = (String) SecurityUtils.getSubject().getPrincipal();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		
//		Log log = new Log();
//		log.setUser(name);
//		log.setTime(df.format(new Date()));
//		log.setIp(request.getRemoteAddr());
//		log.setType("list");
//		log.setContent1("event");
//
//		new LogDao().add(log);
	}
}
