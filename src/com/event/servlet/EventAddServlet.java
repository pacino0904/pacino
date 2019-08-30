package com.event.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

import com.event.model.Event;
import com.event.dao.EventDao;
import com.log.model.Log;
import com.log.dao.LogDao;

public class EventAddServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		Event event = new Event();
		event.setOccTime(request.getParameter("occTime"));
		event.setLocale(request.getParameter("locale"));
		event.setDepartment(request.getParameter("department"));
		event.setLevel(request.getParameter("level"));
		event.setDiscPerson(request.getParameter("discPerson"));
		event.setDiscTime(request.getParameter("discTime"));
		event.setHandlePerson(request.getParameter("handlePerson"));
		event.setEventDesc(request.getParameter("eventDesc"));
		event.setEffBus(request.getParameter("effBus"));
		event.setIncidence(request.getParameter("incidence"));
		event.setEffTime(request.getParameter("effTime"));
		event.setEventHandle(request.getParameter("eventHandle"));
		event.setEventReason(request.getParameter("eventReason"));
		event.setEventResult(request.getParameter("eventResult"));
		event.setTjsj(request.getParameter("tjsj"));
		event.setEventNumber(request.getParameter("eventNumber"));
		event.setStatus(request.getParameter("status"));
		new EventDao().add(event);
		
		String name = (String) SecurityUtils.getSubject().getPrincipal();
		
		
		Log log = new Log();
		log.setUser(name);
		log.setTime(request.getParameter("tjsj"));
		log.setIp(request.getRemoteAddr());
		log.setType("add");
		log.setContent1("event");
		log.setContent2(request.getParameter("eventNumber"));
		new LogDao().add(log);
		
	}
	
	
	
	
	
	

}
