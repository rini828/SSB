package com.ssb.update.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.member.db.MemberDAO;
import com.ssb.member.db.MemberDTO;
import com.ssb.update.db.updateDAO;
import com.ssb.update.db.updateDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

@WebServlet("*.ud")
public class updateController extends HttpServlet { // 이 서블릿은 URL 패턴이 *.ud인 요청을 처리합니다.

    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // 서블릿의 핵심 처리를 담당하는 메서드입니다.
    	
    	// URI = URL - (프로토콜 - IP -포트번호)
    	// 요청된 URI, 컨텍스트 패스, 그리고 실제 명령어를 추출합니다.
        String requestURI = request.getRequestURI();
        String CTXPath = request.getContextPath();
        String command = requestURI.substring(CTXPath.length());
        Action action = null;
        ActionForward forward = null;

        // HttpSession을 통한 사용자 정보 추가
        // 세션에서 사용자 아이디를 가져와 사용자 정보를 DB에서 조회한 후, request에 추가합니다.
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            // 사용자 정보를 DB에서 가져오기
            updateDAO memberDAO = new updateDAO();
            MemberDTO currentMember = memberDAO.getMember(userId);

            // 현재 로그인된 사용자의 정보를 request에 저장
            request.setAttribute("currentMember", currentMember);
        }
        
        
        // 명령어에 따른 처리 분기
        // 명령어에 따라 처리를 분기합니다. update.ud는 사용자 정보 업데이트 폼으로 이동하고,
        // updateAction.ud 및 updateProAction.ud는 각각 업데이트 처리를 위한 액션 클래스를 호출합니다.
        if (command.equals("/update.ud")) {
        	// update.jsp로 이동하기 위한 설정
            forward = new ActionForward();
            forward.setPath("./update/update.jsp");
            forward.setRedirect(false);

        } else if (command.equals("/updateAction.ud")) {
        	// 사용자 정보 업데이트를 위한 Action 클래스 호출
        	action = new updateAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/updateProAction.ud")) {
        	// 사용자 정보 업데이트 처리를 위한 Action 클래스 호출
        	action = new updateProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 페이지 이동
        if (forward != null) { // 이동정보가 존재할때
            if (forward.isRedirect()) { // true
            	// 리다이렉트 방식으로 페이지 이동
                response.sendRedirect(forward.getPath());
            } else { // false
            	// 포워딩 방식으로 페이지 이동
                RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
                dis.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }
}
