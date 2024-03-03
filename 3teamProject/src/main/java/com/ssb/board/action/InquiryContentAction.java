package com.ssb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.board.db.BoardDAO;
import com.ssb.board.db.BoardDTO;
import com.ssb.reply.db.ReplyDAO;
import com.ssb.reply.db.ReplyDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class InquiryContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 전달정보 저장 (boardId, pageNum)
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String pageNum = request.getParameter("pageNum");
				
		// BoardDAO 객체 생성
		// 문의글 정보를 가져오는 메서드
		BoardDAO bdao = new BoardDAO();
		BoardDTO bdto = bdao.getBoard(boardId);
								
		// ReplyDAO 객체 생성
		// 문의글 답변의 정보를 가져오는 메서드
		ReplyDAO rdao = new ReplyDAO();		
		ReplyDTO rdto = rdao.getReply(boardId);
				
		// 문의글 정보를 request 영역에 저장
		request.setAttribute("bdto", bdto);
		
		// 문의글 답변 정보를 request 영역에 저장
		request.setAttribute("rdto", rdto);
				
		// pageNum값도 request 영역에 저장
		request.setAttribute("pageNum", pageNum);
				
		// 페이지 이동준비 (./board/inquiryContent.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/inquiry/inquiryContent.jsp");
		forward.setRedirect(false);
				
		return forward;
	}

}