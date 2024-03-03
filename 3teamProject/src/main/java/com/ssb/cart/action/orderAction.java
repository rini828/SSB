package com.ssb.cart.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.cart.db.cartDAO;
import com.ssb.cart.db.orderDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class orderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//받은정보저장
		String checkArray = request.getParameter("checkArray");
		//데이터 처리
		cartDAO dao = new cartDAO();
		ArrayList<orderDTO> dtoArray = dao.getOrder(checkArray);
		//보낼정보저장
		request.setAttribute("dtoArray", dtoArray);
		// 페이지 이동준비 
		ActionForward forward = new ActionForward();
		forward.setPath("./Order.od");
		forward.setRedirect(false);

		return forward;
	}

}
