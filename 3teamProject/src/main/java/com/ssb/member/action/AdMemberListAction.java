package com.ssb.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.adItem.db.ItemDAO;
import com.ssb.member.db.MemberDAO;
import com.ssb.member.db.MemberDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class AdMemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		HttpSession session = request.getSession();
//		String admin_id = (String) session.getAttribute("adminId");
		
//		ActionForward forward = new ActionForward(); // 사실상 admin페이지는 이 부분이 필요없다?..
//		if(admin_id == null || !admin_id.equals("admin")) {
//			forward.setPath("./AdminLogin.ad");
//			forward.setRedirect(true);
//			return forward;
//		}
		
		// 전달정보 검색어 정보 저장
		String search = request.getParameter("search");
		
		// 회원목록 조회 메서드()
		MemberDAO dao = new MemberDAO();
//		ArrayList<MemberDTO> memberList = dao.getMemberList();
//		request.setAttribute("memberList", memberList);
		
		
		// 기존에 저장된 상품 정보를 가져와서 화면에 출력
		// MemberDAO 객체 생성 - 회원목록 조회 메서드() 
		
		int count = 0;
		if(search == null) { // 검색어 X
			count = dao.getMemberCount();
		}else { // 검색어 O - 검색결과O/X 
			count = dao.getMemberCount(search);
		}		

		/********************* 페이징처리 1 *******************/
		// 한 페이지에 출력할 회원 개수 설정
		int pageSize = 15;

		// 현 페이지가 몇페이지 인지확인
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		// 시작행 번호 계산하기
		// 1 11 21 31 41 .....
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;

		// 끝행 번호 계산
		// 10 20 30 40 50 .....
		int endRow = currentPage * pageSize;

		/********************* 페이징처리 1 *******************/

		// DAO - 모든 회원 정보를 가져오는 메서드 호출
		ArrayList memberList = null;
		if (count > 0 && search == null) {
			memberList = dao.getMemberList(startRow, pageSize);
		}else if(count > 0 && search != null ) {
			memberList = dao.getMemberList(startRow, pageSize, search);
		}else {
			// 상품이 없는경우
		}

		// request영역에 정보를 저장
		// 리스트를 출력 => 연결된 뷰페이지에서 출력하도록 정보 전달
		request.setAttribute("memberList", memberList);


		/******************* 페이징처리 2 *********************/
		// 페이지 블럭(1,2,3,.....,10) 생성

		// 전체 페이지수
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

		// 한 화면에 보여줄 페이지 블럭개수
		int pageBlock = 10;

		// 페이지 블럭의 시작번호 계산
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// 페이지 블럭의 마지막번호 계산
		int endPage = startPage + pageBlock - 1;
		
		// 페이지에 상품이 없는경우
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		/******************* 페이징처리 2 *********************/

		// 페이징 처리에 필요한 정보 모두를 request영역에 저장해서 전달
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", count);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);	
		
		ActionForward forward = new ActionForward();
		forward.setPath("./adMemberList/adMemberList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
