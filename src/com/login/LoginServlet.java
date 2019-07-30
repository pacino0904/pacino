package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");  
        String password = req.getParameter("password");
        Subject subject = SecurityUtils.getSubject();  
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);  
        try {  
            subject.login(token);
            Session session=subject.getSession();
            session.setAttribute("subject", subject);
            resp.sendRedirect("index.html");
        } catch (AuthenticationException e) {
            req.setAttribute("error", "—È÷§ ß∞‹");  
            req.getRequestDispatcher("login.html").forward(req, resp); 
        }
	}
}