package com.ssb.ajax.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ssb.ajax.db.ajaxDAO;
import com.ssb.cart.db.optionsDTO;

@WebServlet("*.aj")
public class ajaxController extends HttpServlet {
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*********************** 1. 가상주소 계산 시작 **************************/
		String requestURI = request.getRequestURI();
		String CTXPath = request.getContextPath();
		String command = requestURI.substring(CTXPath.length());
		/*********************** 1. 가상주소 계산 끝 **************************/
		
		/*********************** 2. 가상주소 매핑 시작 **************************/
		// GSON생성
		Gson gson = new Gson();
		String json = null;
		ajaxDAO dao = new ajaxDAO();
		if (command.equals("/getOptions.aj")) {
			// 정보저장
			String item_id = request.getParameter("item_id");
			// 정보처리
			List<optionsDTO> list = dao.getOptions(item_id);
			if (list != null) {
				json = gson.toJson(list);
			}else {
				json = gson.toJson("옵션없음");
			}
			
		} else if (command.equals("/updateCart.aj")) {
			// 정보저장
			String cart_id = request.getParameter("cart_id");
			String item_id = request.getParameter("item_id");
			String option_id = request.getParameter("option_id");
			String cart_quantity = request.getParameter("cart_quantity");
			//정보처리
			int result = dao.updateCart(cart_id,item_id,option_id,cart_quantity);
			json = gson.toJson(result);
		} else if (command.equals("/deleteCart.aj")) {
			// 정보저장
			String cart_id = request.getParameter("cart_id");
			String member_id = (String)request.getSession().getAttribute("member_id");
			//정보처리
			int result = dao.deleteCart(cart_id,member_id);
			json = gson.toJson(result);
		}
		
		/*********************** 2. 가상주소 매핑 끝 **************************/

		/*********************** 3. 가상주소 이동 시작 **************************/
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(json);
		/*********************** 3. 가상주소 이동 끝 **************************/
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
