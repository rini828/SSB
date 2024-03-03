package com.ssb.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * JSMoveFunction : 컨트롤러 사용 없이 JS만 사용해서 페이지 이동처리하는 객체
 *
 */
public class JSMoveFunction {

	// alert + location.href 기능
	public static void alertLocation(HttpServletResponse response, String msg, String url) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('" + msg + "'); ");
			out.println(" location.href='" + url + "'; ");
			out.println("</script>");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// alert + history.back() 기능
	public static void alertBack(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('" + msg + "'); ");
			out.println(" history.back(); ");
			out.println("</script>");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
