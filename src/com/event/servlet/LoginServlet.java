package com.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); 
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		String html = null;
		
        if ("admin".equals(name) && "123".equals(password))
            html = "<div style='color:green'>µÇÂ¼³É¹¦</div>";
        else
            html = "<div style='color:red'>µÇÂ¼Ê§°Ü</div>";
 
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println(html);
		
	}
}