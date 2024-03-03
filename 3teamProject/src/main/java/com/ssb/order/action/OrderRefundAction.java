package com.ssb.order.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.order.db.OrderDetailDAO;
import com.ssb.order.db.OrderDetailDTO;
import com.ssb.order.db.OrdersDAO;
import com.ssb.order.db.OrdersDTO;
import com.ssb.order.vo.OrdersState;
import com.ssb.payment.service.PaymentService;
import com.ssb.payment.vo.PortOneRefundResult;
import com.ssb.payment.vo.StoreInfo;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;

public class OrderRefundAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//환불에 필요한 정보가 무엇이 있을까? 
		// 주문 ID, 주문자 ID는 기본적으로 있어야함 , 환불받을 계좌번호 ,
		//long ordersId = Long.parseLong(request.getParameter("orders_id"));
		//long ordersId = 20231114000018L;

		String orderId = request.getParameter("orders_id");
		
		/*
		 * 요구하는 정보 (타입)
		 * imp_uid (String) -> 포트원 거래 고유번호
		 * merchant_uid (String) -> 가맹점 주문번호
		 * amount (number) -> 취소 요청 금액
		 * tax_free (number) -> 취소요청 금액중 면세 금액
		 * vat_amount (number) -> 취소요청금액 중 부가세 금액
		 * reason (String) -> 취소 사유
		 * refund_holder (String) -> 환불계좌 예금주
		 * refund_bank (String) -> 환금계좌 은행
		 * refund_account (String) 환불계좌 번호
		 * refund_tel (String) -> 환불계좌 예금주 연락처
		 */
		
		/*
		 * 해야 할 동작 
		 * -> 환불 처리 들어옴 
		 * -> 환불 가능 여부 확인 
		 * -> 환불 
		 */
		
		//-------------------배송상태 확인 해야함-------------------------
		
		//-----------------------------------------------------------
		
		
		
		
		//-------------------------확인 후---------------------------------
		OrdersDAO ordersDAO = new OrdersDAO();
		OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
		//주문번호에 해당하는 OrdersDTO 소환
		OrdersDTO orderDTO = ordersDAO.findById(Long.parseLong(orderId));
		
		
		if(orderDTO.getOrders_state().equals(OrdersState.DELIVERY)) {
			String message = "물품이 배송중이므로 현재 환불을 진행 할 수 없습니다. ";
			JSMoveFunction.alertBack(response, message);
			return null;
		}
		//---------------------------11월 16일 추가-----@@@@@@@@@@@@@@@@@@@@@@@@@@
		else if(orderDTO.getOrders_state().equals(OrdersState.DETERMINE)) {
			String message = "구매 확정 처리된 상품 입니다. 환불을 진행 할 수 없습니다. ";
			JSMoveFunction.alertBack(response, message);
			return null;
		}
		//-------------------------------------------@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		//주문번호에 해당하는 모든 ordersDTO 불러오기
		List<OrderDetailDTO> orderDetailDTOList = orderDetailDAO.findByOrdersId(Long.parseLong(orderId));
		
		//주문한 물품 전부 수량 변경
		for(OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
			orderDetailDAO.refundOrderDetail(orderDetailDTO);
		}
		
		//환불 처리 상태로 주문 업그레이드
		ordersDAO.updateOrdersState(Long.parseLong(orderId), OrdersState.REFUND);
		
		
		//실제 결제 내역 환불 조치
		PaymentService paymentService = new PaymentService();
		
		StoreInfo info = new StoreInfo();
		
		String myToken = paymentService.getTokenV3(info);
		
		PortOneRefundResult result = paymentService.refundPayment(myToken, Long.parseLong(orderId));
		
		if(result.equals(PortOneRefundResult.PASS)) {
			JSMoveFunction.alertLocation(response, "주문취소가 정상적으로 진행되었습니다.", "./myPage.mp");

		}
		
		
		ActionForward forward = new ActionForward();
		
		return forward;
	}

}
