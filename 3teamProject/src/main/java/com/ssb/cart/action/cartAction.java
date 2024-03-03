package com.ssb.cart.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.cart.db.cartDAO;
import com.ssb.cart.db.cartDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class cartAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//받은정보저장
		String member_id = (String)request.getSession().getAttribute("member_id");
		//데이터 처리
		cartDAO dao = new cartDAO();
		ArrayList<cartDTO> dtoArray = dao.getCart(member_id);
		//보낼정보저장
		request.setAttribute("dtoArray", dtoArray);
		// 페이지 이동준비 
		ActionForward forward = new ActionForward();
		forward.setPath("./cart/cartList.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
