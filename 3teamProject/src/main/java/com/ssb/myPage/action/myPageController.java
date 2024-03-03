package com.ssb.myPage.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.myPage.db.myPageDAO;
import com.ssb.myPage.db.myPageDTO;
import com.ssb.order.action.OrderRefundAction;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

@WebServlet("*.mp")
public class myPageController extends HttpServlet { // URL 매핑 설정, 해당 서블릿은 URL 패턴이 "*.mp"로 끝나는 모든 요청을 처리합니다.

    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // doProcess()메서드 , 모든 요청에 대한 처리를 담당하는 메서드
        // URI = URL - (프로토콜 - IP -포트번호)
        // 요청 처리 로직 , 요청된 URI 에서 컨텍스트 패스를 제외한 부분을 추출하여 명령(command)으로 사용합니다.
    	String requestURI = request.getRequestURI();
        String CTXPath = request.getContextPath();
        String command = requestURI.substring(CTXPath.length());
        

        Action action = null;
        ActionForward forward = null;

        // 로그인된 사용자 정보를 request에 추가
        // 세션을 이용한 사용자 정보 추가 , 로그인된 사용자가 있다면 해당 사용자의 정보를 DB에서 가져와서 request에 추가합니다.
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            // 사용자 정보를 DB에서 가져오기
            myPageDAO myPageDAO = new myPageDAO();
            myPageDTO currentMember = myPageDAO.getMember(userId);

            // 현재 로그인된 사용자의 정보를 request에 저장
            request.setAttribute("currentMember", currentMember);
        }
        
        
        // 요청 처리에 따른 Action 객체 생성 및 실행
        // 각 요청에 따른 Action 객체를 생성하고 execute() 메서드를 실행합니다.
        if (command.equals("/myPage.mp")) {

            // DB 처리를 Action 클래스에서 수행하도록 변경
            action = new myPageAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (command.equals("/myPageAction.mp")) {
            action = new myPageAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (command.equals("/myPageRefundAction.mp")) {
        	
        	// 주문 환불 처리를 담당하는 Action 클래스 호출
        	action = new OrderRefundAction();
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if(command.equals("/myPageOrderDetail.mp")) {
        	
        	// 주문 상세 정보 조회를 담당하는 Action 클래스 호출
        	action = new MyPageDetailAction();	
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        // 페이지 이동 처리
        // Action 객체에서 반환된 ActionForward에 따라 페이지 이동을 처리합니다. 페이지 리다이렉트 또는 포워드를 선택합니다.
        if (forward != null) { // 이동정보가 존재할때
            if (forward.isRedirect()) { // true
            	// 페이지 리다이렉트
                response.sendRedirect(forward.getPath());
            } else { // false
            	// 포워드를 이용한 페이지 이동
                RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
                dis.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

}
