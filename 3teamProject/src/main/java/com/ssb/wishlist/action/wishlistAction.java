package com.ssb.wishlist.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.wishlist.db.wishlistDAO;
import com.ssb.wishlist.db.wishlistDTO;

public class wishlistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 받은정보저장
		String member_id = (String)request.getSession().getAttribute("member_id");
		// 데이터 처리
		wishlistDAO dao = new wishlistDAO();
		ArrayList<wishlistDTO> dtoArray = dao.getWishlist(member_id);
		// 보낼정보저장
		request.setAttribute("dtoArray", dtoArray);
		// 페이지 이동준비
		ActionForward forward = new ActionForward();
		forward.setPath("./wishlist/wishlist.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
