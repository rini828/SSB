package com.ssb.login.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.util.Action;
import com.ssb.util.ActionForward;

@WebServlet("*.lg")
public class loginController extends HttpServlet {
	
	// doGet과 doPost 메서드에서 공통적으로 수행될 메서드
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// URI = URL - (프로토콜 - IP -포트번호)
		String requestURI = request.getRequestURI();
		String CTXPath = request.getContextPath();
		String command = requestURI.substring(CTXPath.length());
		Action action = null;
		ActionForward forward = null;
		
		// 요청이 "/login.lg"인 경우 로그인 페이지로 이동
		if(command.equals("/login.lg")) {
			forward = new ActionForward();
			forward.setPath("./login/login.jsp");
			forward.setRedirect(false);
			
		// 요청이 "/loginAction.lg"인 경우 로그인 처리 액션 실행
		}else if(command.equals("/loginAction.lg")) {
		
			action = new loginAction();
		
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		// 이동 정보가 존재할 때
		if(forward != null) { // Redirect 방식으로 이동
			if(forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
			}else { // Forward 방식으로 이동
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}
	
	
	// HTTP GET 요청 처리 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	// HTTP POST 요청 처리 메서드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
}