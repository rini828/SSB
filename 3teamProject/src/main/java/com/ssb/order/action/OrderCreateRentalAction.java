package com.ssb.order.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.Mitem.ItemDAO;
import com.ssb.member.db.MemberDAO;
import com.ssb.member.db.MemberDTO;
import com.ssb.order.db.OrderDetailDAO;
import com.ssb.order.db.OrderDetailDTO;
import com.ssb.order.db.OrdersDAO;
import com.ssb.order.db.OrdersDTO;
import com.ssb.order.exception.OrderPriceException;
import com.ssb.order.exception.StockLackException;
import com.ssb.order.service.OrderService;
import com.ssb.order.vo.CreateOrderResult;
import com.ssb.order.vo.OrderCheckState;
import com.ssb.rental.db.RentalDAO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;

public class OrderCreateRentalAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		CreateOrderResult result = CreateOrderResult.FAILED;
		//받아올 정보
		
		HttpSession session = request.getSession();
		
		String userId =  (String)session.getAttribute("userId");
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO findMember =  memberDAO.getMember(userId);
		
		
		
		int location_id = Integer.parseInt(request.getParameter("location_id"));
		
		
		int rentalId = Integer.parseInt(request.getParameter("rental_item_id"));
		String rentalItem_name =  request.getParameter("rental_item_name");
		Date rentalStr = java.sql.Date.valueOf(request.getParameter("rental_str"));   
		Date rentalEnd = java.sql.Date.valueOf(request.getParameter("rental_end"));
		int rentalItemPrice =Integer.parseInt(request.getParameter("rental_item_price"));
		int rentalQuantity = Integer.parseInt(request.getParameter("rental_item_opt_quantity"));
		
		
		OrderCheckState orderCheckState;
		OrdersDTO ordersDTO = new OrdersDTO();
		List<OrderDetailDTO> orderDDTOs = new ArrayList<>();
		
	
		try {
			
			//재고수량 체크 확인 -> 재고 수량이 구매 하고자 하는 양보다 부족시 예외 발생 (렌탈 ID)
			orderCheckState = OrderService.rentalStockCheck(rentalId);
			
			//-----------------------재고 수량 체크에서 문제 없을시 아래 코드 진헹-----------------------------
			OrdersDAO orderDAO = new OrdersDAO();
			
			//orderㄴID생성
			long ordersID = orderDAO.createOrdersId();
			ordersDTO = OrdersDTO.createRentalOrder(ordersID, findMember.getMember_id(), location_id);
			
			int orderTotalPrice = 0;

			
			//----------------------------옵션 수량 깎는 메서드 들어갈 자리------------------------
			// 장바구니 기능을 쓰지 않으므로 rental Item 수량 직접 깍아야함 -> 메서드 구현
			RentalDAO rentalDAO = new RentalDAO();
			
			rentalDAO.decreaseQuantity(rentalId);
			
			//-------------------------------------------------------------------
			//할인율
			int discount = 0;
			//------------------------------------------------------------------
			
				
			//주문상세 생성
			OrderDetailDTO orderDetail = OrderDetailDTO.createRentalItem(ordersID, rentalId,rentalItem_name, rentalItemPrice , rentalStr, rentalEnd); //렌탈 시작 기간, 렌탈 종료 기간 );
				
			OrderDetailDAO orderDDAO = new OrderDetailDAO();
			//주문상세 저장
			orderDDAO.saveRentalDetail(orderDetail);
			orderDDTOs.add(orderDetail);
				
			//총 주문 금액 plus
			orderTotalPrice += orderDetail.getTotalPrice() + (orderDetail.getTotalPrice()*0.1);
			
			
			//-----------------------------11월 23일 포인트 추가
			
			Integer usePoint = Integer.parseInt(request.getParameter("usePoint"));
			
			int originalTotalPrice = 0;
			
			
			
			if(usePoint == null || usePoint == 0) {
				originalTotalPrice = orderTotalPrice;
				ordersDTO.setOriginal_total_price(originalTotalPrice);
						
			}else {
				originalTotalPrice = orderTotalPrice;			
				orderTotalPrice = orderTotalPrice - usePoint;
				memberDAO.updateUsePoint(findMember.getMember_id(), usePoint);
				ordersDTO.setOriginal_total_price(originalTotalPrice);
			}
			
			
			//---------------------11월 23일 포인트 추가
			
			
			//계싼된 총 주문 금액 DTO에 입력
			ordersDTO.changeTotalPrice(orderTotalPrice);
			
			//ordersDTO DB에 최종 저장
			orderDAO.saveRentalOrders(ordersDTO);
			
			result = CreateOrderResult.SUCCESS;
			
		} catch (StockLackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				
			result = CreateOrderResult.FAILED;
			String message = "수량이 부족한 제품이 있습니다.";
			JSMoveFunction.alertBack(response,message);
			
			
		} catch (OrderPriceException e) {

			e.printStackTrace();
			result = CreateOrderResult.FAILED;
			
			String message = "주문 금액에 문제가 발생하였습니다. 다시 시도 해주시길 바랍니다.";
			JSMoveFunction.alertBack(response,message);
			
		}


		request.setAttribute("ordersDTO", ordersDTO);
		request.setAttribute("orderDDTOs", orderDDTOs);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./order/order_pay.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
