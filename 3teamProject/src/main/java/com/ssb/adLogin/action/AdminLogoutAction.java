package com.ssb.adLogin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;

public class AdminLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 한글 처리 => web.xml에 필터 처리
		
		// 로그아웃 처리 => 세션 정보 초기화
		HttpSession session = request.getSession();
		session.invalidate();
		
		// JS 사용 페이지 이동
		JSMoveFunction.alertLocation(response, "안전하게 로그아웃 되었습니다.", "./AdminLogin.ad");
		return null;
	}

}