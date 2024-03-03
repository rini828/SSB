package com.ssb.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.member.db.MemberDAO;
import com.ssb.member.db.MemberDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;


public class MemberJoinIdCheck implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		response.setContentType("application/json");
		// 정보 저장
		String member_user_id = request.getParameter("userId");
		
		PrintWriter out = response.getWriter();
		
	
		MemberDAO dao = new MemberDAO();
		int result = dao.checkId(member_user_id);
		
		
		// 성공여부 확인용
		if(result == -1) {
//			System.out.println("이미 존재하거나 'admin'을 포함하는 아이디");
		} else if (result == 0) {
//			System.out.println("공백");
		} else if (result == 1) {
//			System.out.println("영문,숫자");
		} else if(result == 2){
//			System.out.println("사용 가능한 아이디");
		} else {
//			System.out.println("예외 발생!");
		}
		
		out.write(result + ""); // -> ajax 결과값인 result가 됨
		// -> String으로 값을 내보낼 수 있도록 해준다
		
		
		return null;
	}

}
