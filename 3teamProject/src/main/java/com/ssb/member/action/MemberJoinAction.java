package com.ssb.member.action;


import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.member.db.MemberDAO;
import com.ssb.member.db.MemberDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;


public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달정보 저장 전 가공
		// member_email
		String member_email = request.getParameter("member_email");
		String member_email2 = request.getParameter("member_email2");
		String domain = request.getParameter("domain");
		String completeEmail = member_email +"@"+(domain.equals("type")? member_email2:domain);
		// member_birth
		String member_birth = request.getParameter("member_birth");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		java.util.Date Date = sdf.parse(member_birth);
		Date sqlBirth = new java.sql.Date(Date.getTime());
		// member_agree
		String member_agree = request.getParameter("member_agree");
		if(member_agree==null) {
			member_agree = "N";
		}
		
		// 전달정보 저장(파라메터) 저장 + 회원가입일
		MemberDTO dto = new MemberDTO();
		dto.setMember_user_id(request.getParameter("member_user_id"));
		dto.setMember_pw(request.getParameter("member_pw"));
		dto.setMember_name(request.getParameter("member_name"));
		dto.setMember_birth(sqlBirth);
		dto.setMember_gender(request.getParameter("member_gender"));
		dto.setMember_email(completeEmail);
		dto.setMember_phone(request.getParameter("member_phone"));
		dto.setMember_agree(member_agree);
		dto.setMember_situation(request.getParameter("member_situation"));
		dto.setMember_regdate(new Timestamp(System.currentTimeMillis()));
		
		
		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();
		
		// 회원가입 메서드를 호출
		dao.insertMember(dto);
		
		// 페이지 이동
//		ActionForward forward = new ActionForward();
//		forward.setPath("./MemberLogin.me");
//		forward.setRedirect(true);
//		
		
		// 페이지 이동(js) -> 메인페이지로 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("if(confirm('로그인하시겠습니까?')){");
		out.println("location.href='./MemberLogin.me';");
		out.println("}else{");
		out.println("location.href='./Main.in';");
		out.println("}");
		out.println("</script>");
		out.close();
		
		return null; // 이미 JS페이지 이동을 완료, 컨트롤러 사용해서 페이지이동X
		
//		return forward;
	}

}
