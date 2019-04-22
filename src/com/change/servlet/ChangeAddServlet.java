package com.change.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.change.dao.ChangeDao;
import com.change.model.Change;

public class ChangeAddServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		Change change = new Change();
		change.setBglx(request.getParameter("bglx"));
		change.setBgxm(request.getParameter("bgxm"));
		change.setBgdj(request.getParameter("bgdj"));
		change.setJhssr(request.getParameter("jhssr"));
		change.setJhkssj(request.getParameter("jhkssj"));
		change.setJhjssj(request.getParameter("jhjssj"));
		change.setBgsqr(request.getParameter("bgsqr"));
		change.setSqsj(request.getParameter("sqsj"));
		change.setBgyy(request.getParameter("bgyy"));
		change.setBgfa(request.getParameter("bgfa"));
		change.setBghtfa(request.getParameter("bghtfa"));
		new ChangeDao().add(change);
	}
}
