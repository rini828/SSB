package com.ssb.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.cart.db.cartDAO;
import com.ssb.cart.db.cartDTO;
import com.ssb.cart.db.totalDTO;
import com.ssb.location.db.locationDAO;
import com.ssb.location.db.locationDTO;
import com.ssb.member.db.MemberDAO;
import com.ssb.member.db.MemberDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class OrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 정보 받아옴
		
		String strCartList= request.getParameter("checkArray");
		
		//DAO 뭉치들
		MemberDAO memberDAO = new MemberDAO();
		locationDAO locationDAO = new locationDAO();
		cartDAO cartDAO = new cartDAO();
		
		// 사용자 주소 가져옴
		HttpSession session  = request.getSession();
		String memberId = (String)session.getAttribute("userId");
		
		//로그인 유저 정보 가져오기
		// 현재 임시로 넣어둠 이후에 까먹지 말고 memberId로 변경할 것
		MemberDTO findMember = memberDAO.getMember(memberId);
		
		
		//배송지 가져오기 -> view에서는 select로 표시?
		List<locationDTO> locations = locationDAO.getlocation(String.valueOf(findMember.getMember_id()));
		
		
		List<cartDTO> cartList = cartDAO.getCartsV2(strCartList);
		
	
		totalDTO total = new totalDTO(); 
		for(cartDTO cart : cartList) {
			total.priceUpdate(cart.getItem_price()*cart.getCart_quantity());
		}
		
		//11월 23일 새로 추가한 내용
		request.setAttribute("user", findMember);
		
		
		//view로 넘길 내용들
		request.setAttribute("strCartList", strCartList); //주문생성시 재사용 할 예정
		request.setAttribute("locations", locations);	
		request.setAttribute("cartList", cartList); //보여지는 카트 목록
		request.setAttribute("totalPrice", total.getTotalPrice());
		
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./order/order_page.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
