<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항&nbsp;|&nbsp;SSB</title>
<!----------- Bootstrap CSS ----------->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<!------------ common CSS ------------->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/adItem/css/itemD.css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/Mcommon/top.css"> --%>
<!-------------- jQuery --------------->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<!------------- 공통 헤더 ------------->

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">

<!-- 공지사항 본문 CSS -->
<style>
html {height: 100%;}

body {
   height: 100%;
    display: flex;
    flex-direction: column;} 

main {
   width: 1500px; 
   margin: 0 auto; 
   margin-top: 200px;
   flex: 1;
}

.board_container {
	width: 80%;
    margin: 40px auto 80px auto;
}

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
   font-family: 'Noto Sans KR', sans-serif;
} 

.board_tag {
	font-size: 11pt;
	margin: 10pt;
	padding-bottom: 10pt;
}
</style>
</head>
<body>
	<div class="header">
		<c:import url="../Mcommon/top.jsp" charEncoding="UTF-8" />
	</div>
	
	<main style="width: 1500px;">
		<section>
		<form class="board_container">
			<div>
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
				<div style="margin-top: 20px">
					<button type="button" class="btn btn-sm btn-dark" id="btnList"
						onclick="location.href='./Notice.no?pageNum=${pageNum }';">목록</button>
				</div>
			</div>
		</form>
		</section>
		
		
	</main>

	<!-- footer 시작 -->
    <footer style="background: black; color:white; text-align: center; padding:10px; width:100vw;">
      <p>&copy; 2023 SSB Style</p>
    </footer>
    <!-- footer 끝 -->		
	<script src="../adItem/js/itemD.js"></script>

</body>
</html>