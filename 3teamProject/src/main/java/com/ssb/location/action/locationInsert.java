package com.ssb.location.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.location.db.locationDAO;
import com.ssb.location.db.locationDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class locationInsert implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 받은정보저장
		int location_id = Integer.parseInt(request.getParameter("location_id"));
		String member_id = (String)request.getSession().getAttribute("member_id");
		// 데이터 처리
		locationDAO dao = new locationDAO();
		if (location_id != -1) {
			location_id = Integer.parseInt(request.getParameter("location_id"));
			locationDTO dto = dao.getReWrite(location_id,member_id);
			request.setAttribute("dto", dto);
		}
		
		
		// 페이지 이동준비
		ActionForward forward = new ActionForward();
		forward.setPath("/location/locationInsert.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
