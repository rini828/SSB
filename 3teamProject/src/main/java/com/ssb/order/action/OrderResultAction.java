package com.ssb.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.payment.db.PaymentDAO;
import com.ssb.payment.db.PaymentDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class OrderResultAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		long mid = Long.parseLong(request.getParameter("mid"));
		
		
		PaymentDAO paymentDAO = new PaymentDAO();
		PaymentDTO paymentDTO = new PaymentDTO();
		
		paymentDTO = paymentDAO.findById(mid);
		
		request.setAttribute("payment", paymentDTO);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./order/order_result.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
