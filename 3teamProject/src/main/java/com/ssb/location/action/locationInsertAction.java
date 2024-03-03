package com.ssb.location.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ssb.location.db.locationDAO;
import com.ssb.location.db.locationDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class locationInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 받은정보저장
		Gson gson = new Gson();
		locationDTO dto = new locationDTO();
		boolean location_idCheck = request.getParameter("location_id").equals("");
		int result;
		
		if (!location_idCheck) {
			dto.setLocation_id(Integer.parseInt(request.getParameter("location_id")));
		}
		dto.setLocation_name(request.getParameter("location_name"));
		dto.setLocation_phone(request.getParameter("location_phone"));
		dto.setLocation_postcode(request.getParameter("location_postcode"));
		dto.setLocation_add(request.getParameter("location_add"));
		dto.setLocationD_add(request.getParameter("locationD_add"));
		dto.setLocation_title(request.getParameter("location_title"));
		dto.setLocation_requested(request.getParameter("location_requested"));
		dto.setMember_id(Integer.parseInt((String)request.getSession().getAttribute("member_id")));// 세션에서 받아오기
		// 데이터 처리
		locationDAO dao = new locationDAO();
		if (!location_idCheck) {
			result = dao.rewriteLocation(dto);
		}else {
			result = dao.insertLocation(dto);
		}
		
		return null;
	}

}
