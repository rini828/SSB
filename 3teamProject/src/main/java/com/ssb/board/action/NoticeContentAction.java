package com.ssb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.board.db.BoardDAO;
import com.ssb.board.db.BoardDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class NoticeContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 전달정보 저장 (boardId, pageNum)
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String pageNum = request.getParameter("pageNum");
				
		// BoardDAO 객체 생성
		//      "        - 특정 공지글의 정보를 가져오는 메서드
		BoardDAO bdao = new BoardDAO();				
		BoardDTO bdto = bdao.getBoard(boardId);
						
		// 글정보를 request 영역에 저장
		request.setAttribute("bdto", bdto);
				
		// pageNum값도 request 영역에 저장
		request.setAttribute("pageNum", pageNum);
				
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./board/notice/noticeContent.jsp");
		forward.setRedirect(false);
				
		return forward;
	}

}