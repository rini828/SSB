package com.ssb.location.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.location.db.locationDAO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class locationDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 받은정보저장
		int location_id = Integer.parseInt(request.getParameter("location_id"));
		int member_id = Integer.parseInt((String)request.getSession().getAttribute("member_id"));
		// 데이터 처리
		locationDAO dao = new locationDAO();
		int result = dao.deleteLocation(location_id, member_id);
		return null;
	}

}
