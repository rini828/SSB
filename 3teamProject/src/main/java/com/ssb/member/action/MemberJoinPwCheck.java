package com.ssb.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class MemberJoinPwCheck implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 정보 저장
		String member_pw = request.getParameter("userPw");
//		String result = "";
//		
		PrintWriter out = response.getWriter();

		
		boolean containsAlphabetCase = member_pw.matches(".*[a-zA-Z].*"); // 대소문자
		boolean containsNumber = member_pw.matches(".*\\d.*");	// 숫자
		boolean containsSpecialChar = member_pw.matches(".*[!@#$%^&*()_\\-+=].*"); // 특수문자
		boolean isLength= member_pw.length() >= 8 && member_pw.length() <= 20; // 8~20자 이내

		// 대소문자, 숫자, 특수문자, 8~20자 이내 여부를 체크
		int result = 0;
		
		// 1개 해당
		if (containsAlphabetCase) result=1;
		if (containsNumber) result=2;
		if (containsSpecialChar) result=3;
		if (isLength) result=4;

		// 2개 해당
		if (containsAlphabetCase && containsNumber) result=5;
		if (containsAlphabetCase && containsSpecialChar) result=6;
		if (containsAlphabetCase && isLength) result=7;
		if (containsNumber && containsSpecialChar) result=8;
		if (containsNumber && isLength) result=9;
		if (containsSpecialChar && isLength) result=10;

		// 3개 해당
		if (containsAlphabetCase && containsNumber && containsSpecialChar) result=11;
		if (containsAlphabetCase && containsNumber && isLength) result=12;
		if (containsAlphabetCase && containsSpecialChar && isLength) result=13;
		if (containsNumber && containsSpecialChar && isLength) result=14;

		// 4개 해당
		if (containsAlphabetCase && containsNumber && containsSpecialChar && isLength) result=15;
		
		
		
		out.write(result+"");
		
		
		return null;
	}

}
