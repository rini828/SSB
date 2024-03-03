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

@WebServlet("*.no")
public class NoticeFrontController extends HttpServlet{

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
		
		if(command.equals("/NoticeWrite.no")) {
			
			forward = new ActionForward();
			forward.setPath("./board/notice/noticeWriteForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/NoticeWriteAction.no")) {
			
			// NoticeWriteAction 객체 생성
			action = new NoticeWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/NoticeList.no")) {
			
			// NoticeListAction 객체 생성
			action = new NoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/NoticeContent.no")) {
			
			// NoticeContentAction 객체 생성
			action = new NoticeContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/NoticeUpdate.no")) {
			
			// NoticeUpdateAction 객체 생성
			action = new NoticeUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/NoticeUpdateProAction.no")) {
			
			// BoardUpdateProAction 객체 생성 
			action = new NoticeUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/NoticeDeleteAction.no")) {
			
			// NoticeDeleteAction 객체 생성
			action = new NoticeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		else if(command.equals("/Notice.no")) {
			
			// NoticeAction 객체 생성
			action = new NoticeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/NoticeView.no")) {
			
			// NoticeViewAction 객체 생성
			action = new NoticeViewAction();
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