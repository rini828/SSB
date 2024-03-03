package com.ssb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.board.db.BoardDAO;
import com.ssb.board.db.BoardDTO;
import com.ssb.main.db.ItemDTO;
import com.ssb.rental.db.RentalDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class InquiryAWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 전달정보 저장 (boardId, pageNum)
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO();
		
		ItemDTO idto = bdao.getItemInquiry(boardId);
		RentalDTO rdto = bdao.getRItemInquiry(boardId);
		
		request.setAttribute("idto", idto);
		request.setAttribute("rdto", rdto);
								
		//      "        - 특정 글의 정보를 가져오는 메서드
		BoardDTO bdto = bdao.getBoard(boardId);
						
		// 글정보를 request 영역에 저장
		request.setAttribute("bdto", bdto);
				
		// pageNum값도 request 영역에 저장=
		request.setAttribute("pageNum", pageNum);
				
		// 페이지 출력 (./board/inquiry/inquiryAWriteForm.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/inquiry/inquiryAWriteForm.jsp");
		forward.setRedirect(false);
				
		return forward;
	}

}