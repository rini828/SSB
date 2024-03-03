<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<!--     <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<title>SSB 회원관리</title>


<!-----------------------------------  현정씨 ▼ ---------------------------------------------->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
<meta name="author" content="AdminKit">
<meta name="keywords"
	content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="shortcut icon" href="img/icons/icon-48x48.png" />
<link rel="canonical" href="https://demo-basic.adminkit.io/" />

<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/app.css">
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap"
	rel="stylesheet">
<!-----------------------------------  현정씨 ▲ ---------------------------------------------->



<!--------  jQuery  --------->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>

<!-- 사이드바 공통 토글 -->
<script src="${pageContext.request.contextPath}/admin/toggle.js"></script>



<!------- common CSS  ------->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/adMemberList/css/adMemberList.css">

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>


	<div class="wrapper">

		<!-- 사이드바 -->
		<jsp:include page="../board/inc/sidebar.jsp" />
		<!-- 사이드바 -->

		<!-- 메인 시작 -->
		<div class="main">
			<header class="navbar navbar-expand navbar-light navbar-bg">
				<a class="sidebar-toggle js-sidebar-toggle" onclick="toggleSidebar()"> <i
					class="hamburger align-self-center"></i>
				</a> <a href="./Main.in"> <img class="home" alt="home"
					src="./adImg/icons/home.png" width="22" height="22">
				</a>
				<c:if test="${sessionScope.admin_Id == null }">
					<a href="./AdminLogin.ad"> <img class="log-button" alt="login"
						src="./adImg/icons/login.png" width="25" height="25">
					</a>
				</c:if>
				<c:if test="${sessionScope.admin_Id != null }">
					<a href="./AdminLogout.ad"> <img class="log-button"
						alt="logout" src="./adImg/icons/logout.png" width="25" height="25">
					</a>
				</c:if>
			</header>
			<!-----------------------------------  현정씨 ▲ ---------------------------------------------->

			<main id="item">
		        <section class="list">
		            <span>
		                <h1> 회원 관리</h1>
		                
		                <%-- 검색창 --%>
		                <form action="./AdMemberList.me" method="get" class="search-bar">
		                    <input type="text" name="search" id="searchInput" placeholder=" 회원정보조회">
		                    <input type="submit" value="검색" id="searchButton"></input>
		                      <!-- <button id="addButton" onclick="">회원 등록</button> -->
		<!--                     <button id="editButton">회원 수정</button> -->
		                </form>
		            </span>
		           	<form action="./AdMemberDeleteAction.me" method="post" id="deleteForm">
		          		<table class="sort">
			                <%-- 
			                회원번호 / 아이디 / 비밀번호 / 이름 / 생년월일 / 성별 / 메일주소 / 전화번호 
			                가입일시 / 결제금액 / 적립금 / 등급 / 상태 / 탈퇴일시 / 마케팅수신동의
			                --%>
			                <colgroup>
			                    <col style="width: 2.5%" /> 
			                    <col style="width: 4%" />
			                    <col style="width: 7%" />
			                    <col style="width: 9%" />
			                    <col style="width: 5%" />
			                    <col style="width: 6.9%" />
			                    <col style="width: 2.5%" />
			                    <col style="width: 14%" />
			                    <col style="width: 8%" />
			                    <col style="width: 6%" />
			                    <col style="width: 5%" />
			                    <col style="width: 5%" />
			                    <col style="width: 2.5%" />
			                    <col style="width: 2.7%" />
			                    <col style="width: 6%" />
			                    <col style="width: 5%" />
			                </colgroup>
			                <thead>
			                    <tr>
			                        <th><label class="checkbox-inline">
			                            <input type="checkbox" id="cbx_chkAll">
			                        </label></th>
			                        <th>회원<br>번호</th>
			                        <th>아이디</th>
			                        <th>비밀번호</th>
			                        <th>이름</th>
			                        <th>생년월일</th>
			                        <th>성별</th>
			                        <th>메일주소</th>
			                        <th>전화번호</th>
			                        <th>가입일시</th>
			                        <th>결제금액</th>
			                        <th>적립금</th>
			                        <th>등급</th>
			                        <th>상태</th>
			                        <th>탈퇴일시</th>
			                        <th>마케팅<br>수신동의</th>
			                    </tr>
			                </thead>
			                
			                <%-- 회원 리스트 --%>
			                <c:forEach var="dto" items="${memberList }">
			                    <tr style="background-color: white;">
			                        <td>
			                        	<label class="checkbox-inline">
			                            	<input type="checkbox" name="chk" value="${dto.member_id}">
			                        	</label>
			                        </td>
			                        <td>${dto.member_id}</td>
			                        <td>${dto.member_user_id}</td>
			                        <td>${dto.member_pw }</td>
			                        <td>${dto.member_name}</td>
			                        <td>${dto.member_birth }</td>
			                        <td>${dto.member_gender }</td>
			                        <td>${dto.member_email }</td>
			                        <td>${dto.member_phone }</td>
			                        <td>${dto.member_regdate }</td>
			                        <td>${dto.member_payment }</td>
			                        <td>${dto.member_point }</td>
			                        <td>${dto.member_grade }</td>
			                        <td>${dto.member_situation }</td>
			                        <td>${dto.member_outdate }</td>
			                        <td>${dto.member_agree }</td>
								</tr>
			                </c:forEach>
		           		</table>	
		               	<button type="button" id="deleteButton" onclick="confirmDelete()">회원 삭제</button>
		            </form>
		            
					<!--- 페이징 --->
		            <div class="paging">
		                <c:if test="${startPage > pageBlock }">
		                    <a href="./AdMemberList.me?pageNum=${startPage-pageBlock }&search=${param.search}">이전</a>
		                </c:if>
		                
		                <c:forEach var="i" begin="${startPage }" end="${endPage }" step="1"> 
		                <span class="num">
							<a href="./AdMemberList.me?pageNum=${i }&search=${param.search}" class="on" >${i }</a> 
		                </span>
						</c:forEach>
		                
		                <c:if test="${endPage < pageCount }">
		                    <a href="./AdMemberList.me?pageNum=${startPage + pageBlock}&search=${param.search}">다음</a>
		                </c:if>
		            </div>
		        </section>
		    </main>


			<footer class="footer">
				<!--      <p>&copy; 2023 SSB Style</p> -->
			</footer>


			<script src="./adMemberList/js/adMemberList.js"></script>
			<script src="js/app.js"></script>
			<script
				src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
				crossorigin="anonymous"></script>
</body>
</html>
