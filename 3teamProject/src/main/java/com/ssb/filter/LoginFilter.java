package com.ssb.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/*")
public class LoginFilter implements Filter{

	private List<String> paths = Arrays.asList("/cartList.ca","/OrderSalePay.od","/location.lo","/locationPopup.lo","/OrderRentalPay.od","/OrderResult.od",
			"/OrderRefund.od","/myPage.mp","/insertCart.ca","/myPageOrderDetail.mp","/Order.od","/MemberCloseAccount.me","/update.ud",
			"/OrderStateUpdateRefund.od","/OrderStateUpdateCancel.od","/OrderStateUpdateBeDelivered.od","/OrderStateUpdateDelivery.od");

	private boolean pathCheck(HttpServletRequest request) {
	    String requestURI = request.getRequestURI();
	    return paths.stream().anyMatch(path -> requestURI.startsWith(request.getContextPath() + path));
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		 String url = httpRequest.getRequestURI();
	
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		try {
			
			System.out.println("호출된 URI" + url);
			
			if(pathCheck(httpRequest)) {
				
				HttpSession session = httpRequest.getSession(false);
				if(session == null || session.getAttribute("userId") == null) {
					PrintWriter w = response.getWriter();
					String msg = "로그인 후 이용해주세요.";
					w.write("<script>alert('"+msg+"');</script>");
					httpResponse.sendRedirect("./MemberLogin.me");
					
					return;
				}
			}
			
			chain.doFilter(request,response);
		}catch (Exception e) {
			
		}finally {
			
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}
	
	
	
	
}
