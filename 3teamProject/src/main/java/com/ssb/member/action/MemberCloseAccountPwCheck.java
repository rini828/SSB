package com.ssb.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.member.db.MemberDAO;
import com.ssb.member.db.MemberDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class MemberCloseAccountPwCheck implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userId =  (String) session.getAttribute("userId"); 
		String userPw = request.getParameter("userPw");
		
		MemberDTO dto = new MemberDTO();
		dto.setMember_user_id(userId);
		dto.setMember_pw(userPw);
		
		MemberDAO dao = new MemberDAO();
		boolean result = dao.checkPw(dto);
		int checkPwCase = 0;
		
		PrintWriter out = response.getWriter();
		
		if(result) {
			checkPwCase = 1;
		}else {
			checkPwCase = 0;
		}
		out.write(checkPwCase+"");
		
		return null;
	}

}
