package com.ssb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;
import com.ssb.board.db.BoardDAO;
import com.ssb.board.db.BoardDTO;
import com.ssb.reply.db.ReplyDAO;
import com.ssb.reply.db.ReplyDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class InquiryAWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 한글 처리 (생략 -> 필터 설정)
		
		// 전달 정보 pageNum 저장
		String pageNum = request.getParameter("pageNum");
				
		// ReplyDTO 객체 생성
		ReplyDTO rdto = new ReplyDTO();
		rdto.setBoard_id(Integer.parseInt(request.getParameter("boardId")));
		rdto.setAdmin_user_id(request.getParameter("adminId"));
		rdto.setReply_content(request.getParameter("content"));
		
		// ReplyDAO 객체 생성 - 문의글 답변 작성하기 메서드
		ReplyDAO rdao = new ReplyDAO();
		rdao.insertInquiryABoard(rdto);
		
		// BoardDTO 객체 생성
		BoardDTO bdto = new BoardDTO();
		bdto.setBoard_id(Integer.parseInt(request.getParameter("boardId")));
		
		BoardDAO bdao = new BoardDAO();
		bdto = bdao.updateAnswerState(bdto);
		
		request.setAttribute("bdto", bdto);
		
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./InquiryList.iq");
		forward.setRedirect(true);
		
		return forward;
	}

}