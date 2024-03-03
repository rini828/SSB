package com.ssb.rental.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.Comment;

import com.ssb.util.Action;
import com.ssb.util.ActionForward;


@WebServlet("*.re")

public class RentalFrontController extends HttpServlet{
	

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	String requestURI = request.getRequestURI();
	
	String CTXPath = request.getContextPath(); //Context = 프로젝트
	
	String command = requestURI.substring(CTXPath.length());
	
	
	
	
	
	Action action = null;
	ActionForward forward = null; 
	
	if(command.equals("/RentalMain.re")) {
		
		action = new RentalMainAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}else if(command.equals("/RentalItem.re")) {
		
		action = new RentalItemAction();
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/RentalReserve.re")) {
		
		
		action = new RentalReserveAction();
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	if(forward != null) {//이동정보가 존재할 때 실행
		if(forward.isRedirect()) { //true
			response.sendRedirect(forward.getPath());
		}else {//false
			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
			dis.forward(request, response);
			
			
		}
	}
	
	
	
	
	
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}
	
	

}

