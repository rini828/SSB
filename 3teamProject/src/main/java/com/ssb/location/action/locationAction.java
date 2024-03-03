package com.ssb.location.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.location.db.locationDAO;
import com.ssb.location.db.locationDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class locationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 받은정보저장
		
		//String member_id = (String)request.getSession().getAttribute("member_id");
		
		
		HttpSession session = request.getSession();
		
		String member_id =  String.valueOf(session.getAttribute("member_id"));
		
		// 데이터 처리
		locationDAO dao = new locationDAO();
		ArrayList<locationDTO> dtoArray = dao.getlocation(member_id);
		// 보낼정보저장
		request.setAttribute("dtoArray", dtoArray);
		// 페이지 이동준비
		ActionForward forward = new ActionForward();
		forward.setPath("./location/location.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
