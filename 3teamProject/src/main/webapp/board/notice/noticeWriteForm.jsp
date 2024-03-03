<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<h3 style="margin-top: -0.5em; margin-bottom: 1em;">공지사항 작성</h3>
					<div class="container" role="main">
						<form action="./NoticeWriteAction.no" method="post">
							<input type="hidden" name="adminId"
								value="${sessionScope.userId }">
							<div class="mb-3">
								<label for="subject">제목</label> <input type="text"
									class="form-control" name="subject" id="subject"
									placeholder="제목을 입력해 주세요" required>
							</div>

							<div class="mb-3">
								<label for="content">내용</label>
								<textarea class="form-control" rows="5" name="content"
									id="content" placeholder="내용을 입력해 주세요" required></textarea>
							</div>

							<div>
								<input type="submit" value="작성하기" class="btn btn-sm btn-primary"
									id="btnSave">
								<button type="button" class="btn btn-sm btn-primary"
									id="btnList" onclick="location.href='./NoticeList.no';">목록</button>
							</div>
						</form>
					</div>
				</article>
			</main>
			<!-- 게시판 -->

		</div>
		<!-- 메인 -->

	</div>
	<script src="./js/app.js"></script>
</body>
</html>