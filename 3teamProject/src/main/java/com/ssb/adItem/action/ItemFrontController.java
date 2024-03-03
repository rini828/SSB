package com.ssb.adItem.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.location.action.locationPopupAction;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

/**
 *  Item 정보 처리를 수행하는 컨트롤러 
 *  
 */

@WebServlet("*.it")	// 컨트롤러 주소 매핑
public class ItemFrontController extends HttpServlet {
	
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


			/***********************1. 가상주소 계산 시작 **************************/
			String requestURI = request.getRequestURI();
			String CTXPath = request.getContextPath();
			String command = requestURI.substring(CTXPath.length());
			/***********************1. 가상주소 계산 끝  ***************************/
			
			
			/***********************2. 가상주소 매핑 시작 **************************/
			Action action = null;
			ActionForward forward = null;


			//------------ 상품 관리 리스트------------
			if (command.equals("/ItemMgt.it")) {
				action = new ItemMgtAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
            //----------------상품 등록----------------
			}else if (command.equals("/itemAddForm.it")) {
				forward = new ActionForward();
				forward.setPath("./adItem/itemAddForm.jsp");
				forward.setRedirect(false);	
			}			
			else if (command.equals("/ItemAddAction.it")) {
				action = new ItemAddAction();
				try {
				  forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//----------------상품 수정----------------
			else if (command.equals("/itemEditForm.it")) {
				action = new ItemEditAction();
				try {
				  forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (command.equals("/ItemEditProAction.it")) {
				action = new ItemEditProAction();
				try {
				  forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
			
			//----------------상품 삭제----------------
			else if (command.equals("/ItemDeleteAction.it")) {
				forward = new ActionForward();
				action = new ItemDeleteAction();
				try {
				  forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			
			
			/***********************2. 가상주소 매핑 끝  **************************/
			
			
			/***********************3. 가상주소 이동 시작**************************/
			if(forward != null) { // ActionForward 포워딩 방식에 따라 수행
				if(forward.isRedirect()) { // true
					response.sendRedirect(forward.getPath()); 
				}else { 				   // false
					RequestDispatcher dis = 
							request.getRequestDispatcher(forward.getPath());
					dis.forward(request, response);
				}			
			}		
			/***********************3. 가상주소 이동 끝**************************/
			
	} //doProcess
	
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

	
} // ItemFrontController
