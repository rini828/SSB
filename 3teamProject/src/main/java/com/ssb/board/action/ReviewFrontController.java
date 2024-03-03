package com.ssb.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.util.Action;
import com.ssb.util.ActionForward;

@WebServlet("*.rv")
public class ReviewFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		/****************************** 1. 가상주소 계산 시작 ****************************/
		String requestURI = request.getRequestURI();
		String CTXPath = request.getContextPath();
		String command = requestURI.substring(CTXPath.length());
		/****************************** 1. 가상주소 계산 끝 ******************************/
		
		
		/****************************** 2. 가상주소 매핑 시작 ****************************/
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/ReviewList.rv")) {
			
			forward = new ActionForward();
			forward.setPath("./board/review/reviewList.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/ReviewWrite.rv")) {
			
			forward = new ActionForward();
			forward.setPath("./board/review/reviewWriteForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/ReviewWriteAction.rv")) {
			
			// ReviewWriteAction 객체 생성
			action = new ReviewWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/****************************** 2. 가상주소 매핑 끝 ******************************/
		
		
		/****************************** 3. 가상주소 이동 시작 ****************************/
		if(forward != null) {
			if(forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
			} else { // false
				
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		/****************************** 3. 가상주소 이동 끝 ******************************/
	}
	
		
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
}