package com.ssb.adLogin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.adLogin.db.AdminDAO;
import com.ssb.adLogin.db.AdminDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;

public class AdminLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 한글 처리 => web.xml에 필터 처리
		
		// 전달정보 저장 (아이디, 비밀번호)
		AdminDTO adto = new AdminDTO();
		adto.setAdmin_user_id(request.getParameter("adminId"));
		adto.setAdmin_pw(request.getParameter("adminPw"));
		
		// AdminDAO 객체 생성 => 로그인 체크 메서드
		AdminDAO adao = new AdminDAO();
		int result = adao.loginAdmin(adto);
		
		ActionForward forward = null;
		if(result == 1) {
			// 페이지(JSP) 이동, 아이디 정보 세션에 저장
			HttpSession session = request.getSession();
			session.setAttribute("userId", adto.getAdmin_user_id());
			
			forward = new ActionForward();
			forward.setPath("./NoticeList.no");
			forward.setRedirect(true);
			
			return forward;
		} else if(result == 0) {
			// JS 사용 페이지 이동
			JSMoveFunction.alertBack(response, "비밀번호가 일치하지 않습니다.");
			return null;
		} else { // result == -1
			JSMoveFunction.alertBack(response, "정보가 존재하지 않습니다. 다시 입력해주세요.");
		}
		
		return null;
	}

}