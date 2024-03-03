package com.ssb.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.payment.service.PaymentService;
import com.ssb.payment.vo.StoreInfo;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class OrderTestAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 주문 생성과 동시에 결제창으로 이동
		
		
		
		PaymentService paymentService = new PaymentService();
		
		StoreInfo info = new StoreInfo();
		
		String myToken = paymentService.getTokenV3(info);
		
		paymentService.refundPayment(myToken, 20231112000012L);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./ssm/order/test.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
