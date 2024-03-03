package com.ssb.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그아웃 처리 => 세션의 정보를 초기화
		HttpSession session = request.getSession();
		session.invalidate();// 현재 세션의 정보를 무효화, 로그아웃처리
		
		// 페이지 이동(js) -> 메인페이지로 이동	
		response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
		response.getWriter(); out.print("<script>");
		out.print(" alert('회원님의 정보가 안전하게 로그아웃 되었습니다!'); ");
		out.print(" location.href='./Main.in'; "); out.print("</script>");
		out.close();
		
		return null; // 이미 JS 페이지 이동을 완료, 컨트롤러 사용해서 페이지이동X
	}

}
