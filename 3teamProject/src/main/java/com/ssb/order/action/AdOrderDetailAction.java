package com.ssb.order.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.location.db.locationDAO;
import com.ssb.location.db.locationDTO;
import com.ssb.order.db.OrderDetailDAO;
import com.ssb.order.db.OrderDetailDTO;
import com.ssb.order.db.OrdersDAO;
import com.ssb.order.db.OrdersDTO;
import com.ssb.order.vo.OrdersSort;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class AdOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orderId = request.getParameter("orders_id");
		
		
		OrdersDAO ordersDAO = new OrdersDAO();
		OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
		
		OrdersDTO findOrderDTO = ordersDAO.findById(Long.parseLong(orderId));
		
		
		
		List<OrderDetailDTO> orderDetailDTO = new ArrayList<>();
		
		if(findOrderDTO.getOrders_sort().equals(OrdersSort.SALE)) {
			orderDetailDTO = orderDetailDAO.findByOrdersId(Long.parseLong(orderId));
		}else if(findOrderDTO.getOrders_sort().equals(OrdersSort.RENTAL)) {
			orderDetailDTO = orderDetailDAO.findByOrdersIdForRental(Long.parseLong(orderId));
			
		}
		
		
		
		
		//주문 배송지 정보 소환
		locationDAO locationDAO = new locationDAO();
		
		locationDTO locationDTO = locationDAO.findById(findOrderDTO.getLocation_id());
		
		
		
		
		request.setAttribute("orders_id", orderId);
		request.setAttribute("orders_state", findOrderDTO.getOrders_state());
		request.setAttribute("orders", findOrderDTO);
		request.setAttribute("orderDetailDTO", orderDetailDTO);
		request.setAttribute("location", locationDTO);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./adOrderList/adOrderDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
	

}
