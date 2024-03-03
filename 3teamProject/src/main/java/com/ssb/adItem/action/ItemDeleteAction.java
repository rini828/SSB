// ItemDeleteAction.java

package com.ssb.adItem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.adItem.db.ItemDAO;
import com.ssb.rental.db.RentalDAO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class ItemDeleteAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 받은 정보 저장
        int options_id = Integer.parseInt(request.getParameter("options_id"));
        // 데이터 처리
        ItemDAO dao = new ItemDAO();
        dao.deleteItem(options_id);
        

        // 페이지 이동 준비
        ActionForward forward = new ActionForward();
        forward.setPath("./adItem/ItemMgt.jsp"); // 이동할 JSP 페이지 경로를 설정
        forward.setRedirect(false); // 현재 페이지 내에서 이동할 때는 false, 외부 URL로 이동할 때는 true

        return forward;
    }
}
