package com.ssb.cart.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.ssb.cart.db.cartDAO;
import com.ssb.cart.db.cartDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class insertCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 정보저장
		int member_id = Integer.parseInt((String)request.getSession().getAttribute("member_id"));
		String type = (String)request.getParameter("type");
		JSONParser parser = new JSONParser();
		ArrayList<cartDTO> dtoArray = new ArrayList<cartDTO>();
		cartDTO dto = null;
		try {
			JSONArray arr = (JSONArray) parser.parse(request.getParameter("arr"));
			for (int i = 0; i < arr.size(); i++) {
				JSONObject obj = (JSONObject) arr.get(i);
				dto = new cartDTO();
				dto.setItem_id(Integer.parseInt((String)obj.get("item_id"))); 
				dto.setCart_quantity(Integer.parseInt((String)obj.get("cart_quantity"))) ;
				dto.setOptions_id(Integer.parseInt((String)obj.get("options_id"))); 
				dtoArray.add(dto);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cartDAO dao = new cartDAO();
		//정보처리
		Gson gson = new Gson();
		String json = null;
		int check = -3;
		ArrayList<cartDTO> result = dao.duplicateCheck(member_id,dtoArray);
		boolean duplicate = result.size() == dtoArray.size();
		int num = dao.insertCart(member_id,result);
		if (duplicate && type.equals("buy")) {
			String cart_id = dao.getCart_id(member_id,dtoArray);
			json = gson.toJson(cart_id);
		}else {
			if (!duplicate) {
				check = -1;
			}else {
				check = -2;
			}
			json = gson.toJson(check);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(json);

		return null;
	}

}
