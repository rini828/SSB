package com.ssb.adItem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.adItem.db.ItemDAO;
import com.ssb.adItem.db.ItemDTO;
import com.ssb.board.db.BoardDAO;
import com.ssb.board.db.BoardDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;
import com.ssb.util.JSMoveFunction;

public class ItemEditProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// 전달정보 저장
			// => ItemDTO 객체 저장
			ItemDTO dto = new ItemDTO();
			dto.setOptions_id(Integer.parseInt(request.getParameter("optionsId")));
			dto.setOptions_name(request.getParameter("name"));
			dto.setOptions_value(request.getParameter("value"));
			dto.setOptions_price(Integer.parseInt(request.getParameter("price")));
			dto.setOptions_quantity(Integer.parseInt(request.getParameter("quantity")));

			
			// 전달받은 정보를 사용해서 기존 정보 수정
			ItemDAO dao = new ItemDAO();
			dao.editOptions(dto);
					
			ActionForward forward = new ActionForward();
		    forward.setPath("./ItemMgt.it");
		    forward.setRedirect(true);

		   return forward;
		}

}
