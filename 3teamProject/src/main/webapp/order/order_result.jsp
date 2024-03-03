<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<script type="text/javascript">
	function toggleCategory() {
		var categoryContent = document.querySelector('.category-content');
		var brandContent = document.querySelector('.brand-content');

		categoryContent.style.display = 'block';
		brandContent.style.display = 'none';
	}

	function toggleBrand() {
		var categoryContent = document.querySelector('.category-content');
		var brandContent = document.querySelector('.brand-content');

		categoryContent.style.display = 'none';
		brandContent.style.display = 'block';
	}
</script>

<!-- 상단 메뉴바 오픈 및 영역 확장 -->
<script>
	document.addEventListener("DOMContentLoaded", function() {

		const submenus = document.querySelectorAll(".menu .submenu");
		const submenuContents = document.querySelectorAll(".submenu-content");

		submenus.forEach(function(submenu, index) {
			const submenuContent = submenuContents[index];

			// 메뉴 항목을 호버할 때
			submenu.addEventListener("mouseover", function() {
				// 해당 메뉴 항목의 하위 메뉴가 표시되면 메뉴의 높이를 조절
				submenuContent.style.display = "block";
				submenu.style.height = submenuContent.clientHeight + "px";
			});

			// 메뉴 항목에서 마우스가 나갈 때
			submenu.addEventListener("mouseout", function() {
				// 해당 메뉴 항목의 하위 메뉴가 숨겨지면 메뉴의 높이를 원래대로 복원
				submenuContent.style.display = "none";
				submenu.style.height = "auto";
			});
		});
	});
</script>


<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SSB Layout</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link href="./rental/rental_css/rental.css" rel="stylesheet">

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>

<body>

	<!-- 판매 일 때 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->
	<div class="header">
		<jsp:include page="../rental/Rcommon/top.jsp" />
	</div>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->

	

	<!-- 렌탈 일 때 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->
	<div class="header">
		<jsp:include page="../rental/Rcommon/top.jsp" />
	</div>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->



	<!-- section 시작 -->

	<section class="section" style="margin: 5%;">

		<!-- 본인 페이지에 맞게 수정하려면 여기 아래서부터 삭제하고, 새로 만들면 됩니다. 혹시 문제 생기면 섹션까지 삭제 해보는거 추천!!!!-->

		<!-- left-container 시작 -->
		<div class="left-container" style="margin: 10%; margin-left:400px; width: 60%;">
			<div class="col-md-7 col-lg-8">
				<h4 class="mb-3">결제 정보</h4>
				<hr>
				<h4 class="mb-3">결제 내역</h4>
				<table class="table table-dark table-striped">
					<thead>
						<tr>
							<th scope="col">결제 번호</th>
							<td>${payment.merchantUid}</td>
						</tr>

						<tr>
							<th scope="col">결제금액</th>
							<td><fmt:formatNumber value="${payment.paidAmount}"/>원</td>
						</tr>

						<tr>
							<th scope="col">결제방식</th>
							<td>${payment.paidMethod}</td>
						</tr>

						<tr>
							<th scope="col">결제 상태</th>
							<td>${payment.pgProvider}</td>
						<tr>
						<tr>
							<th scope="col">결제 상태</th>
							<td>${payment.status}</td>
						<tr>
					</thead>

				</table>

				<hr>

				<hr class="my-4">

				<a class="btn btn-secondary" role="button" aria-disabled="true">메인페이지로
					가기</a> <a class="btn btn-secondary" role="button" aria-disabled="true" href="./myPage.mp">
					주문내역</a>


			</div>
		</div>
		</main>

		</div>



		<!-- 본인 페이지에 맞게 수정하려면 여기까지 삭제하고, 새로 만들면 됩니다. 혹시 문제 생기면 섹션까지 삭제 해보는거 추천!!!!-->
	</section>
	<!-- section 끝  -->

	<!-- footer 시작 -->
	<footer class="footer">
		<p>&copy; 2023 SSB Style</p>
	</footer>
	<!-- footer 끝 -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>




</body>

</html>