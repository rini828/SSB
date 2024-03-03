package com.ssb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.board.db.BoardDAO;
import com.ssb.board.db.BoardDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class InquiryItemQWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 한글 처리 (생략 -> 필터 설정)
		
		// BoardDTO 객체 생성
		BoardDTO bdto = new BoardDTO();
		bdto.setMember_user_id(request.getParameter("userId"));
		bdto.setItem_id(Integer.parseInt(request.getParameter("itemId")));
		bdto.setInquiry_type(request.getParameter("iqType"));
		bdto.setBoard_subject(request.getParameter("subject"));
		bdto.setBoard_content(request.getParameter("content"));
		
		// BoardDAO 객체 생성 - 문의글 작성하기 메서드
		BoardDAO bdao = new BoardDAO();
		bdao.insertInquiryItem(bdto);
		
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.in");
		forward.setRedirect(true);
		
		return forward;
	}

}