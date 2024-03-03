package com.ssb.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.order.db.OrdersDAO;
import com.ssb.order.db.OrdersDTO;
import com.ssb.order.vo.OrdersState;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;

public class OrderStateUpdateWithCancelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		long orderId = Long.parseLong(request.getParameter("orders_id"));
		
		OrdersDAO ordersDAO = new OrdersDAO();
		
		
		ordersDAO.updateOrdersState(orderId, OrdersState.CANCEL);
		
		OrdersDTO updateDTO = ordersDAO.findById(orderId);
		
		
		if(updateDTO.getOrders_state().equals(OrdersState.CANCEL)) {
			JSMoveFunction.alertLocation(response, "주문이 취소 되었습니다.", "./AdOrderDetail.od?orders_id="+orderId);
		}else {
			JSMoveFunction.alertBack(response, "실패했음 다시 시도하셈");
		}
		
		
//		ActionForward forward = new ActionForward();
//		forward.setPath("AdOrderDetail.od?orders_id="+orderId);
//		forward.setRedirect(true);
		return null;
	}

}
