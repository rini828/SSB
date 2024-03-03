package com.ssb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.member.db.MemberDAO;
import com.ssb.member.db.MemberDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;

public class MemberCloseAccountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 마이페이지에서 회원탈퇴 버튼을 누를때 MemberCloseAccount.me 페이지 이동
		// (사유 선택 => 탈퇴 확인 버튼 누를 시) MemberCloseAccountAction에서 세션 영역의 member_user_id를 저장 후 dto에 저장
		//- 탈퇴 확인 버튼 누를시 MemberCloseAccountAction.me 에서 상태(탈퇴), 탈퇴일시 update 한 후
		//- 세션 해재 및 메인페이지
		
		// 정보 저장
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		// 정보저장
		MemberDTO dto = new MemberDTO();
		dto.setMember_user_id(userId);
		
		MemberDAO dao = new MemberDAO();
		boolean result = dao.updateMember(dto);
		
		if(result == true) {
			// 세션정보 초기화
			session.invalidate();
			
			// 회원탈퇴 성공
			JSMoveFunction.alertLocation(response, "회원탈퇴 완료", "./Main.in");
		}else {
			JSMoveFunction.alertBack(response, "회원탈퇴 실패");
		}
		
		return null;
	}

}
