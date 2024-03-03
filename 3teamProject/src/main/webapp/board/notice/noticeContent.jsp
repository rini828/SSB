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

<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="shortcut icon" href="img/icons/icon-48x48.png" />
<link rel="canonical" href="https://demo-basic.adminkit.io/" />

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

<!-- 공지사항 본문 페이지 -->
<style>
.board_title {
	border-bottom: 1px solid #ddd;
	padding-bottom: 5px;
	font-weight: 700;
	font-size: 20pt;
	margin: 10pt;
}

.board_info_box {
	color: #6B6B6B;
	margin: 10pt;
}

.board_author {
	font-size: 10pt;
}

.board_date {
	font-size: 10pt;
}

.board_content {
	color: #444343;
	font-size: 12pt;
	margin: 10pt;
}

.board_content>pre {
	font-family: 'Archivo', 'Noto Sans KR', sans-serif;
}

.board_tag {
	font-size: 11pt;
	margin: 10pt;
	padding-bottom: 10pt;
}
</style>

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
	<!-- 로그인 세션 제어 -->
	<c:if test="${empty userId }">
		<c:redirect url="./AdminLogin.ad" />
	</c:if>

	<div class="wrapper">

		<!-- 사이드바 -->
		<jsp:include page="../inc/sidebar.jsp" />

		<!-- 메인 -->
		<div class="main">
			<jsp:include page="../inc/top.jsp" />

			<!-- 게시판 -->
			<main class="content">
				<article>
					<div class="container" role="main">
						<div class="table-responsive">
							<h3></h3>
							<div class="bg-white rounded shadow-sm">
								<div class="board_title">
									<c:out value="${bdto.board_subject}" />
								</div>
								<div class="board_info_box">
									<span class="board_date"><c:out
											value="${bdto.board_writeTime}" />/View:</span><span
										class="board_author"><c:out
											value="${bdto.board_readCount}" /></span>
								</div>
								<div class="board_content">
									<pre>
										<c:out value="${bdto.board_content}" />
									</pre>
								</div>
							</div>
							<div style="margin-top: 20px">
								<button type="button" class="btn btn-sm btn-primary"
									id="btnUpdate"
									onclick="location.href='./NoticeUpdate.no?boardId=${bdto.board_id }&pageNum=${pageNum }';">수정</button>
								<button type="button" class="btn btn-sm btn-primary"
									id="btnDelete"
									onclick="location.href='./NoticeDeleteAction.no?boardId=${bdto.board_id }&pageNum=${pageNum }';">삭제</button>
								<button type="button" class="btn btn-sm btn-primary"
									id="btnList"
									onclick="location.href='./NoticeList.no?pageNum=${pageNum }';">목록</button>
							</div>
						</div>
					</div>
				</article>
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