<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 <link href="./rental/rental_css/rental.css" rel="stylesheet">
 <!-- 폰트 CSS -->	
<style>
  @import url('https://fonts.googleapis.com/css2?family=Archivo&display=swap');
  @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
  
* {
	font-family: 'Archivo', 'Noto Sans KR', sans-serif;
}
</style>


	<!-- header 시작 -->
	<header class="header">
		<div class="logo">
			<a href="./RentalMain.re"><img src="./main/img/SSB_gold.png" alt="SSB 로고"
				style="width: 200px;"></a>
		</div>
		<div class="fm">
			<form role="search" class="search-box"> <!--style="display: flex;"  -->
				<input class="search-input" type="text" placeholder="내 스타일 찾기" name="search"
					style="width: 80%;">
				<button type="submit" class="search-icon">
					<img width="38" height="38" src="./main/img/camping.png" alt="검색버튼" />
				</button>
			</form>
		</div>

			<!-- ID 세션값이 null 일때 보일 버튼 (로그아웃/ 비회원상태)-->
		<c:if test="${empty sessionScope.userId }">
			<div class="button-container">
				<button class="login-button"><a href="./MemberLogin.me">login</a></button>
				<button class="signup-button"><a href="./cartList.ca">
					<img width="20" height="15" src="./main/img/market.png" alt="장바구니" />cart</a>
				</button>
			</div>
		</c:if>
		<!-- ID 세션값이 null 일때 보일 버튼 (로그아웃/ 비회원상태) -->

		<!-- ID 세션값이 있을때 보일 버튼(로그인 상태) -->
		<c:if test="${!empty sessionScope.userId && not fn:containsIgnoreCase(sessionScope.userId, 'admin')}">
			<div class="button-container">
				<button class="signup-button"><a href="./myPage.mp" style="white">My Page</a></button>
				<button class="signup-button">
				<img width="20" height="15" src="./main/img/redHeart.png" alt="하트" />
				<a href="./wishlist.wl" > 찜</a></button>
				<button class="signup-button">
				<img width="20" height="15" src="./main/img/market.png" alt="장바구니" />
				<a href="./cartList.ca" > cart</a>
				</button>
				<button class="login-button"><a href="./MemberLogout.me">logout
				</a></button>
			</div>
		</c:if>
		<!-- ID 세션값이 있을때 보일 버튼 (로그인 상태) -->
		
		<!-- ID 세션값이 admin일때 보일 버튼 (관리자전용) -->
		<c:if test="${fn:containsIgnoreCase(sessionScope.userId, 'admin')}">
			<div class="button-container">
				<a style="background-color: transparent; margin-top:10px; " href="./NoticeList.no">
				<img width="30" height="30" src="./main/img/admin.png" /></a>
			</div>
		</c:if>
		<!-- ID 세션값이 admin일때 보일 버튼 (관리자전용) -->
	</header>
	<!-- header 끝 -->
	
	
	<!-- 상단 메인 메뉴바 시작 -->
	<div class="menu">
		<div class="submenu">
			<a href="./RentalMain.re?category=캠핑"><b>Camping</b></a>
			<div class="submenu-content">
				<a href="./RentalMain.re?category=캠핑&category_sub=텐트">텐트</a> <a href="./RentalMain.re?category=캠핑&category_sub=테이블">테이블</a> 
				<a href="./RentalMain.re?category=캠핑&category_sub=의자">의자</a> <a href="./RentalMain.re?category=캠핑&category_sub=기타">기타</a>
			</div>

		</div>
		<div class="submenu">
			<a href="./RentalMain.re?category=스키/보드"><b>Ski/SnowBoard</b></a>
			<div class="submenu-content">
				<a href="./RentalMain.re?category=스키/보드&category_sub=스키">스키장비</a> 
				<a href="./RentalMain.re?category=스키/보드&category_sub=보드">보드장비</a> 
				<a href="./RentalMain.re?category=스키/보드&category_sub=고글">고글</a> 
				<a href="./RentalMain.re?category=스키/보드&category_major=의류">의류</a>
			</div>
		</div>

		<div class="submenu">
			<a href="./RentalMain.re?category=골프"><b>Golf</b></a>
			<div class="submenu-content">
				<a href="./RentalMain.re?category=골프&category_sub=모자">모자</a> <a href="./RentalMain.re?category=골프&category_sub=상의">상의</a> 
				<a href="./RentalMain.re?category=골프&category_sub=하의">하의</a> <a href="./RentalMain.re?category=골프&category_sub=원피스">원피스</a> 
				<a href="./RentalMain.re?category=골프&category_major=신발">신발</a>
			</div>
		</div>

		<div class="submenu">
			<a href="./Main.in"><b>For Buy</b></a>
		</div>
		
		<div class="submenu">
			<a href="./Notice.no"><b>Notice</b></a>
		</div>
	</div>
	<!-- 상단 메인 메뉴바 끝 -->