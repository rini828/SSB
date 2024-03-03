package com.ssb.rental.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.rental.db.RentalDAO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class RentalMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		String category_major = request.getParameter("category_major");
		String category_sub = request.getParameter("category_sub");
		RentalDAO rdao = new RentalDAO();
		
	
		
		ArrayList rentalList = null;
		
		 if(search==null&&category==null&&category_major==null&&category_sub==null) { 
				rentalList = rdao.getRentalList();
			}else if(search != null){ //검색어가 있을때 - 검색결과 유무에따라 추가적인 제어 필요
			  rentalList = rdao.getRentalList(search);
			}else if(search==null&& category != null){
				rentalList = rdao.getCategoryItem(category);
			}
			
			if(search==null&& category_sub == null && category != null && category_major != null) {
				rentalList = rdao.getMiddleCategoryItem(category, category_major);
			}else if(search==null&&category_major == null && category != null && category_sub != null) {
				rentalList = rdao.getSubCategoryItem(category, category_sub);
			}
		
		
		request.setAttribute("rentalList", rentalList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./rental/rental_page/rentalMain.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
