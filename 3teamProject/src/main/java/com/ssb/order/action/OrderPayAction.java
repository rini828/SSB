package com.ssb.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.order.db.OrderDetailDAO;
import com.ssb.order.db.OrderDetailDTO;
import com.ssb.order.db.OrdersDAO;
import com.ssb.order.db.OrdersDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class OrderPayAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//필요한거 유저 정보
		//Member member = request.getSession();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		long orderId = Long.parseLong(request.getParameter("orderId"));
		// long userId = 1;
		//long orderId = 20231111000003L;
		
		
		
		
		
		
		//주문, 주문상세 모두 가져오기
		OrdersDAO ordersDAO = new OrdersDAO();
		OrderDetailDAO orderDDAO = new OrderDetailDAO();
		
		OrdersDTO ordersDTO = ordersDAO.findById(orderId);
		
		//----------------테스트 코드-------------------------------------
		//----------------------------------------------
		
		List<OrderDetailDTO> orderDDTOs = orderDDAO.findByOrdersId(orderId);
		
		//----------------------------------------------
		
		for(OrderDetailDTO order : orderDDTOs) {
		}
		
		request.setAttribute("ordersDTO", ordersDTO);
		request.setAttribute("orderDDTOs", orderDDTOs);
		
		
		
		//------------------------------------------------------------
		
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./order/order_pay.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
