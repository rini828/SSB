package com.ssb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.board.db.BoardDAO;
import com.ssb.board.db.BoardDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class NoticeWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 한글 처리 (생략 -> 필터 설정)
		
		// BoardDTO 객체 생성
		BoardDTO bdto = new BoardDTO();
		bdto.setAdmin_user_id(request.getParameter("adminId"));
		bdto.setBoard_subject(request.getParameter("subject"));
		bdto.setBoard_content(request.getParameter("content"));
		
		// BoardDAO 객체 생성 - 공지글 작성하기 메서드
		BoardDAO bdao = new BoardDAO();
		bdao.insertNotice(bdto);
		
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./NoticeList.no");
		forward.setRedirect(true);
		
		return forward;
	}

}