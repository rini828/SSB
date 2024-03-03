package com.ssb.main.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;

import com.mysql.cj.Session;
import com.ssb.main.db.ItemDAO;
import com.ssb.main.db.ItemDTO;
import com.ssb.member.db.MemberDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	

		String search = request.getParameter("search");
		String category = request.getParameter("category");
		String category_sub = request.getParameter("category_sub");
		String category_major = request.getParameter("category_major");

		ItemDAO idao = new ItemDAO();

		ArrayList itemList = null;
		

		if(search==null&&category==null&&category_sub==null&&category_major==null) { 
			itemList = idao.getMainItemList();
		}else if(search != null) { //검색어가 있을때 - 검색결과 유무에따라 추가적인 제어 필요
		  itemList = idao.getItemList(search);
		}else if(search==null&&category != null) { // 상단 메인메뉴바에서 스포츠별 카테고리 선택시
			itemList = idao.getCategoryItem(category);
		}else if(search==null&&category_sub !=null) { // 상단 메인메뉴바에서 의류별 카테고리 선택시
			itemList = idao.getCategorySubItem(category_sub);
		}else if(search==null&&category_major != null) {
			itemList = idao.getCategoryMajorItem(category_major);
		}
		 

		request.setAttribute("itemList", itemList);
		ActionForward forward = new ActionForward();
		forward.setPath("./main/main.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
