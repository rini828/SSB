package com.ssb.update.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.member.db.MemberDTO;
import com.ssb.update.db.updateDAO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class updateAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	// 사용자 아이디를 파라미터에서 가져옵니다.
    	// 클라이언트로부터 전달된 사용자 아이디를 파라미터로부터 가져옵니다.
        String userId = request.getParameter("user_id");
       
        // updateDAO 객체 생성
        // 사용자 정보를 업데이트하기 위해 데이터베이스와 상호작용하는 updateDAO 객체를 생성합니다.
        updateDAO dao = new updateDAO();

        // 현재 로그인된 사용자 정보 가져오기
        // updateDAO를 통해 현재 로그인된 사용자의 정보를 가져옵니다.
        MemberDTO mdto = dao.getMember(userId);

        // 가져온 정보를 request에 설정합니다.
        // 가져온 사용자 정보를 request에 설정하여 JSP 페이지에서 해당 정보에 접근할 수 있도록 합니다.
        request.setAttribute("currentMember", mdto);
        
        // 페이지 이동(./update/updateForm.jsp)
        // 업데이트 페이지로 이동하기 위한 ActionForward 객체를 생성하고 설정합니다.
        // 페이지 이동 방식이 포워딩이므로 setRedirect(false)를 호출합니다.
        ActionForward forward = new ActionForward();
        forward.setPath("./update/update.jsp");
        forward.setRedirect(false);

        return forward;
    }
}
