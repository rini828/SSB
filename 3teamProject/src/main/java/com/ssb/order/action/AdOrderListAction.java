package com.ssb.order.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.order.db.OrdersDAO;
import com.ssb.order.db.OrdersDTO;
import com.ssb.order.vo.OrdersState;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class AdOrderListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 전달정보 검색어 정보 저장
		String orders_state = request.getParameter("orders_state");
		
		OrdersDAO ordersDAO = new OrdersDAO();
		
		
		if(orders_state==null) {
			orders_state = OrdersState.PURCHASE.toString();
		}else {
			orders_state = request.getParameter("orders_state");
		}
		
		
		int count = 0;
		count = ordersDAO.getOrderCount(orders_state);
		
		

		/********************* 페이징처리 1 *******************/
		// 한 페이지에 출력할 상품 개수 설정
		int pageSize = 15;

		// 현 페이지가 몇페이지 인지확인
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		// 시작행 번호 계산하기
		// 1 11 21 31 41 .....
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;

		// 끝행 번호 계산
		// 10 20 30 40 50 .....
		int endRow = currentPage * pageSize;

		/********************* 페이징처리 1 *******************/

		// DAO - 모든 회원 정보를 가져오는 메서드 호출
		List<OrdersDTO> orders = new ArrayList<>();
		orders = ordersDAO.findByStateForAdmin(orders_state, startRow, pageSize);
		
		

		for(OrdersDTO tt : orders) {
		}
		
		// request영역에 정보를 저장
		// 리스트를 출력 => 연결된 뷰페이지에서 출력하도록 정보 전달
		request.setAttribute("orderList", orders);


		/******************* 페이징처리 2 *********************/
		// 페이지 블럭(1,2,3,.....,10) 생성

		// 전체 페이지수
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

		// 한 화면에 보여줄 페이지 블럭개수
		int pageBlock = 10;

		// 페이지 블럭의 시작번호 계산
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// 페이지 블럭의 마지막번호 계산
		int endPage = startPage + pageBlock - 1;
		
		// 페이지에 상품이 없는경우
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		/******************* 페이징처리 2 *********************/

		// 페이징 처리에 필요한 정보 모두를 request영역에 저장해서 전달
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", count);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);	
		request.setAttribute("orders_state",orders_state);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./adOrderList/adOrderList.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

	
}
