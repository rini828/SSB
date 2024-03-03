package com.ssb.adItem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.adItem.db.ItemDAO;
import com.ssb.adItem.db.ItemDTO;
import com.ssb.rental.db.RentalDAO;
import com.ssb.rental.db.RentalDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class ItemEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int optionsId = Integer.parseInt(request.getParameter("optionsId"));
				
		// 기존에 저장된 상품 정보를 가져오기 (판매)
		ItemDAO dao = new ItemDAO();		
		ItemDTO dto = dao.getOptions(optionsId);
		
		// request 영역에 정보 저장 후 전달
		request.setAttribute("dto", dto);
				
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./adItem/itemEditForm.jsp");
		forward.setRedirect(false);
				
		return forward;
	}

}
