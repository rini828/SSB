package com.ssb.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.login.action.LogoutAction;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// URI = URL - (프로토콜 - IP -포트번호)
		String requestURI = request.getRequestURI();
		String CTXPath = request.getContextPath();
		String command = requestURI.substring(CTXPath.length());
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MemberJoin.me")) {
			
			forward = new ActionForward();
			forward.setPath("./join/join.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberJoinAction.me")) {
			
			action = new MemberJoinAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberLogin.me")) {
			
			forward = new ActionForward();
			forward.setPath("./login/login.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/MemberJoinIdCheck.me")) { // Ajax 아이디 중복 체크 매핑
		    
		    action = new MemberJoinIdCheck();
		    try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberJoinPwCheck.me")) { // Ajax 비밀번호 체크 매핑
		    
		    action = new MemberJoinPwCheck();
		    try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberJoinPwDoubleCheck.me")) { // Ajax 비밀번호 체크 매핑
		    
		    action = new MemberJoinPwDoubleCheck();
		    try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}else if(command.equals("/MemberCloseAccount.me")) {
			
			forward = new ActionForward();
			forward.setPath("./closeAccount/closeAccount.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberCloseAccountAction.me")) {
			
			action = new MemberCloseAccountAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberCloseAccount.me")) {
			
			forward = new ActionForward();
			forward.setPath("./closeAccount/closeAccount.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberCloseAccountAction.me")) {
			
			action = new MemberCloseAccountAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberCloseAccountPwCheck.me")) {
			
			action = new MemberCloseAccountPwCheck();
			
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/AdMemberList.me")) {
			
			action = new AdMemberListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdMemberDeleteAction.me")) {
			
			action = new MemberDeleteAction();
			
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberLogout.me")) {

		    action = new LogoutAction();

		    try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		if(forward != null) { // 이동정보가 존재할때
			if(forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
			}else { // false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response); 
			}
		}

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
}
