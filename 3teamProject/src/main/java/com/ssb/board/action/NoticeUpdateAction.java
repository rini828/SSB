package com.ssb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.board.db.BoardDAO;
import com.ssb.board.db.BoardDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class NoticeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 전달정보 bno/pageNum 받기
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String pageNum = request.getParameter("pageNum");
				
		// 기존에 저장된 글정보를 가져오기
		BoardDAO bdao = new BoardDAO();		
		BoardDTO bdto = bdao.getBoard(boardId);
				
		// request 영역에 정보 저장 후 전달
		request.setAttribute("bdto", bdto);
		request.setAttribute("pageNum", pageNum);
				
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./board/notice/noticeUpdateForm.jsp");
		forward.setRedirect(false);
				
		return forward;
	}

}