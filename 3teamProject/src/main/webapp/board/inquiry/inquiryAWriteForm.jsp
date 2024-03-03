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

<title>Q&A&nbsp;|&nbsp;SSB</title>

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

<style>
.info {
	width: 18em; 
	height: 15em; 
	float: left; 
	margin-right: 1em;
}

.board_title {
	font-weight: 700;
	font-size: 14pt;
	margin: 10pt;
}

.board_info_box {
	color: #6B6B6B;
	border-bottom: 1px solid #ddd;
	/* padding-bottom: 5px; */
	margin: 10pt;
	margin-top: -0.5em;
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
		<!-- 사이드바 -->

		<!-- 메인 -->
		<div class="main">
			<jsp:include page="../inc/top.jsp" />

			<!-- 게시판 -->
			<main class="content">
				<article>
					<div class="container" role="main">
						<div class="table-responsive">
						   <div class="bg-white rounded shadow-sm info">
						     <c:if test="${!empty idto.item_name }">
						     <div class="board_title" style="font-size: 15px;">
						       ${idto.item_name}
						     </div>
						     <div class="board_content">
						       <img src="./main/item_img/${idto.item_img_main }" width="180" height="150">
						     </div>
						     </c:if>
						     <c:if test="${!empty rdto.rental_item_name }">
						     	<div class="board_title" style="font-size: 15px;">
						       		${rdto.rental_item_name }
						    	</div>
						     	<div class="board_content">
						       		<img src="./main/rental_item/${rdto.rental_img_main }" width="180" height="150">
						     	</div>						     
						   	 </c:if>
						   </div>
							<div class="bg-white rounded shadow-sm" style="overflow: hidden; height: 15em;">
								<div class="board_title">${bdto.board_subject}</div>
								<div class="board_info_box">
									<span class="board_date">작성자:<c:out
											value="${bdto.member_user_id}" />&nbsp;/&nbsp;문의유형:
									</span><span class="board_author">${bdto.inquiry_type}</span>
								</div>
								<div class="board_content">
									<pre>${bdto.board_content}</pre>
								</div>
								<div style="text-align: right; margin-top: 9%;">
									<button type="button" class="btn btn-sm btn-primary"
										id="btnList"
										onclick="location.href='./InquiryList.iq?pageNum=${pageNum }';">목록</button>
									<button type="button" class="btn btn-sm btn-primary"
										id="btnDelete"
										onclick="location.href='./NoticeDeleteAction.no?boardId=${bdto.board_id }&pageNum=${pageNum }';">삭제</button>
								</div>
							</div>
						</div>

							<form
								action="./InquiryAWriteProAction.iq?boardId=${bdto.board_id }&pageNum=${pageNum}"
								method="post">
								<input type="hidden" name="adminId"
									value="${sessionScope.userId }">
								<div class="bg-white rounded shadow-sm">
									<div class="board_title">[답변]&nbsp;${bdto.board_subject}
									</div>
									<div class="mb-3">
										<textarea class="form-control" rows="5" name="content"
											id="content" placeholder="내용을 입력해 주세요" required></textarea>
									</div>
									<div style="text-align: right;">
										<input type="submit" value="작성하기"
											class="btn btn-sm btn-primary" id="btnSave">
									</div>
								</div>
							</form>
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