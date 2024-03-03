<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
<meta name="author" content="AdminKit">
<meta name="keywords"
	content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

<title>공지사항&nbsp;|&nbsp;SSB</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./css/app.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap"
	rel="stylesheet">

<!--------  jQuery  --------->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<!-- 사이드바 공통 토글 -->
<script src="${pageContext.request.contextPath}/admin/toggle.js"></script>


<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<!-- 
	  로그인을 수행한 관리자만 접근 가능 
	  로그인 없이 접근한 경우 로그인페이지로 이동
   -->
	<c:if test="${empty userId }">
		<c:redirect url="./AdminLogin.ad" />
	</c:if>

	<div class="wrapper">

		<!-- 사이드바 -->
		<jsp:include page="../inc/sidebar.jsp" />
		<!-- 사이드바 -->

		<!-- 메인 -->
		<div class="main">
			<jsp:include page="../inc/top.jsp" />

			<!-- 게시판 -->
			<main class="content">
				<article>
					<div class="container" role="main">
						<div class="table-responsive">
							<h3>공지사항 관리</h3>
							<h6 style="text-align: right;">글 개수: ${count }</h6>
							<table class="table table-striped table-sm">
								<colgroup>
									<col style="width: 10%" />
									<col style="width: 70%" />
									<col style="width: 20%" />
								</colgroup>
								<thead>
									<tr style="text-align: center;">
										<th>작성자</th>
										<th>제목</th>
										<th>등록일</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="bdto" items="${noticeList }">
										<tr style="text-align: center;">
											<td><c:out value="${bdto.admin_name }" /></td>
											<td><a
												href="./NoticeContent.no?boardId=${bdto.board_id }&pageNum=${pageNum }"
												style="text-decoration: none;"> ${bdto.board_subject } </a>
											</td>
											<td>${bdto.board_writeTime }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div>
							<input type="button" value="작성하기" class="btn btn-sm btn-primary"
								onclick="location.href='./NoticeWrite.no';">
						</div>
					</div>
				</article>

				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled"><c:if
								test="${startPage > pageBlock }">
								<a href="./NoticeList.no?pageNum=${startPage-pageBlock }"
									class="page-link">Pre</a>
							</c:if></li>
						<c:forEach var="i" begin="${startPage }" end="${endPage }"
							step="1">
							<li class="page-item"><a
								href="./NoticeList.no?pageNum=${i }" class="page-link" href="#">${i }</a>
							</li>
						</c:forEach>
						<li class="page-item"><c:if test="${endPage < pageCount }">
								<a href="./NoticeList.no?pageNum=${startPage+pageBlock }"
									class="page-link">Next</a>
							</c:if>
					</ul>
				</nav>
			</main>
			<!-- 게시판 -->

		</div>
		<!-- 메인 -->

	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
	<script src="./js/app.js"></script>
</body>
</html>