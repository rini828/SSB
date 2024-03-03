package com.ssb.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.order.db.OrdersDAO;
import com.ssb.order.vo.OrdersState;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class OrderRentalTurnInAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		long orderId = Long.parseLong(request.getParameter("orders_id"));
		
		OrdersDAO ordersDAO = new OrdersDAO();
		
		
		ordersDAO.updateOrdersState(orderId, OrdersState.TURNIN);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdOrderDetail.od?orders_id="+orderId);
		forward.setRedirect(true);
		return forward;
	}
	
	

}
