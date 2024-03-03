package com.ssb.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;

import com.ssb.login.db.loginDAO;
import com.ssb.member.db.MemberDAO;
import com.ssb.member.db.MemberDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;


public class loginAction implements Action {

	// Action 인터페이스의 execute 메서드를 구현
	@Override
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		
		// 한글처리 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 전달정보 저장(id,pw)

		MemberDTO dto = new MemberDTO();
		dto.setMember_user_id(request.getParameter("member_user_id"));
		dto.setMember_pw(request.getParameter("member_pw"));
		
		
		// DAO객체 생성 -> 로그인 체크 메서드
		loginDAO dao = new loginDAO();
		
		//로그인 체크 결과값
		int result = dao.loginMember(dto);
		
		
		ActionForward forward = null;
		
		 // 로그인 체크 결과에 따른 분기 처리
		 if(result == 2) {
			// 회원정보가 없는 경우
			 JSMoveFunction.alertBack(response, "회원정보가 없습니다.");
		 }else if(result == 1) {
	        // 로그인 성공

	        // 세션에 아이디 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("userId", dto.getMember_user_id());
			
			// 회원 정보를 가져와 세션에 저장
			MemberDAO mdao = new MemberDAO();
			MemberDTO mdto =  mdao.getMember(dto.getMember_user_id());
			session.setAttribute("member_id", String.valueOf(mdto.getMember_id()));
		
			
			// 페이지 이동 설정
			forward = new ActionForward();
			forward.setPath("./Main.in");
			forward.setRedirect(true);
			
			return forward;			
		}else if(result == 0) {
			// 사용자 비밀번호 오류
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(" <script> ");
			out.println("  alert(' 사용자 비밀번호 오류! '); ");
			out.println("  history.back(); ");
			out.println(" </script> ");
			out.close();
			
			return null; // ActionForward 정보가 null => 컨트롤러 페이지 이동하지 않음
		}else{ //result == -1
			// 회원정보 없음
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(" <script> ");
			out.println("  alert('회원정보 없음!!'); ");
			out.println("  history.back(); ");
			out.println(" </script> ");
			out.close();
		}
		
		return forward;
	}//execute

}
