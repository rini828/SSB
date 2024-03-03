package com.ssb.update.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.member.db.MemberDTO;
import com.ssb.update.db.updateDAO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;

public class updateProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	// 세션에서 현재 로그인된 사용자의 아이디 가져오기
    	// 현재 로그인된 사용자의 아이디를 세션에서 가져옵니다.
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
  
        
        // 전달받은 정보 저장
        // 사용자가 입력한 정보를 MemberDTO 객체에 저장합니다.
        // 새로운 비밀번호(new_member_pw)를 받아오는데, 만약 비밀번호 변경 기능을 추가한다면 해당 부분에 대한 로직이 필요합니다.
        MemberDTO mdto = new MemberDTO();
        mdto.setMember_user_id(userId);
        mdto.setMember_name(request.getParameter("name"));
        mdto.setMember_phone(request.getParameter("phone"));
        mdto.setMember_email(request.getParameter("email"));
        mdto.setMember_pw(request.getParameter("current_pw"));
        mdto.setNew_member_pw(request.getParameter("pw")); 
        // 비밀번호 변경이 있는 경우 새로운 비밀번호 설정
        // (비밀번호 변경 기능을 추가하려면 해당 부분에 대한 로직이 필요)

        // updateDAO 객체 생성
        // 사용자 정보를 업데이트하기 위해 DAO 객체를 생성합니다.
        updateDAO dao = new updateDAO();

        // 회원 정보 수정 메서드 호출
        // DAO를 통해 회원 정보를 업데이트하는 메서드를 호출하고 그 결과를 저장합니다.
        int result = dao.updateMember(userId, mdto);

        // 결과에 따른 페이지 이동(JS)
        // 결과에 따라 적절한 페이지 이동을 JavaScript를 이용하여 처리합니다.
        // JSMoveFunction 클래스는 JavaScript로 페이지 이동을 처리하는 유틸리티 클래스로 가정하고 있습니다.
        if (result == 1) {
            // 수정완료 -> 메인페이지로 이동
            JSMoveFunction.alertLocation(response, "회원정보 수정완료!", "./Main.in");
        } else if (result == 0) {
            // 수정실패 -> 비밀번호 오류 -> 뒤로가기 이동
            JSMoveFunction.alertBack(response, "수정실패X- 비밀번호 오류");
        } else {
            // result == -1
            JSMoveFunction.alertBack(response, "회원정보가 없음!!!");
        }

        return null;
    }
}
