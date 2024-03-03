package com.ssb.myPage.action;

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

public class MyPageDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 주문 번호 파라미터에서 추출
		// 클라이언트로부터 전달받은 주문 번호 파라미터를 추출합니다.
		String orderId = request.getParameter("orders_id");
		
		// DAO 초기화
		// 주문 정보와 상세 주문 정보를 조회하는 DAO 초기화
		OrdersDAO ordersDAO = new OrdersDAO();
		OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
		
		// 주문 번호를 기반으로 주문 정보 조회
		OrdersDTO findOrderDTO = ordersDAO.findById(Long.parseLong(orderId));
		
		// 주문 상태에 따라 상세 주문 정보 조회
		List<OrderDetailDTO> orderDetailDTO = new ArrayList<>();
		if(findOrderDTO.getOrders_sort().equals(OrdersSort.SALE)) {
			orderDetailDTO = orderDetailDAO.findByOrdersId(Long.parseLong(orderId));
		}else if(findOrderDTO.getOrders_sort().equals(OrdersSort.RENTAL)) {
			orderDetailDTO = orderDetailDAO.findByOrdersIdForRental(Long.parseLong(orderId));
			
		}
		
		// 배송지 정보 조회
		// 주문 정보에 연결된 배송지 정보를 조회합니다.
		locationDAO locationDAO = new locationDAO();
		locationDTO locationDTO = locationDAO.findById(findOrderDTO.getLocation_id());
		
		// request 영역에 필요한 정보 저장
		// 조회한 주문 정보, 상세 주문 정보, 배송지 정보를 request 영역에 저장합니다.
		request.setAttribute("orders_id", orderId);
		request.setAttribute("orders_state", findOrderDTO.getOrders_state());
		request.setAttribute("orders", findOrderDTO);
		request.setAttribute("orderDetailDTO", orderDetailDTO);
		request.setAttribute("location", locationDTO);
		
		// 페이지 이동 설정
		// 페이지 이동 정보를 설정합니다. 페이지는 "./myPage/myPageDetail.jsp"로 이동하며, 리다이렉트 방식이 아닙니다.
		ActionForward forward = new ActionForward();
		forward.setPath("./myPage/myPageDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
