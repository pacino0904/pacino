package com.member.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dao.MemberDao;
import com.member.model.Member;

/**
 * Servlet implementation class MemberSearchServlet
 */
@WebServlet("/MemberSearchServlet")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchServlet() {
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
		
		String keyWord = request.getParameter("keyWord");
		
		List<Member> members = new MemberDao().search(keyWord);
		int count = new MemberDao().searchTotal(keyWord);
		StringBuffer sb = new StringBuffer();
		String trFormat1 = "{\"code\":0,\"msg\":\"\",\"count\":%d,\"data\":[";
		String tr1 = String.format(trFormat1, count);
		sb.append(tr1);
		
		String trFormat = "{\"id\":\"%d\",\"department\":\"%s\",\"subDepartment\":\"%s\",\"job\":\"%s\",\"name\":\"%s\",\"phoneNumber\":\"%s\",\"function\":\"%s\"},";
		
		for (Member member : members) {
			String tr = String.format(trFormat, member.getId(), member.getDepartment(), member.getSubDepartment(), member.getJob(), member.getName(), member.getPhoneNumber(), member.getFunction());
			sb.append(tr);
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		response.getWriter().write(sb.toString());
	}

}
