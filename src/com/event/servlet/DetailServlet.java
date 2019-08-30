package com.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

import com.event.dao.EventDao;
import com.event.model.Event;
import com.log.dao.LogDao;
import com.log.model.Log;

import net.sf.json.JSONObject;


/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Event detailEvent = new Event();
		detailEvent.setId(Integer.parseInt(request.getParameter("id")));
		new EventDao().detail(detailEvent);
		
		JSONObject json = JSONObject.fromObject(detailEvent);
		out.println(json.toString());
		
		String name = (String) SecurityUtils.getSubject().getPrincipal();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Log log = new Log();
		log.setUser(name);
		log.setTime(df.format(new Date()));
		log.setIp(request.getRemoteAddr());
		log.setType("detail");
		log.setContent1("event");
		log.setContent2(detailEvent.eventNumber);
		log.setContent3(request.getParameter("id"));
		new LogDao().add(log);
	}

}
