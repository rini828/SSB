package com.ssb.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class MemberJoinPwDoubleCheck implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String member_pw2 = request.getParameter("userPw2");
		PrintWriter out = response.getWriter();
		
		out.write(member_pw2);
		
		
		return null;
	}

}
