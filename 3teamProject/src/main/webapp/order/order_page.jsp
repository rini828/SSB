<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    <script src="./location/locationPopup.js"></script>

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
<link href="./main/main_css/main.css" rel="stylesheet">

<style>
	        .coupon-select {
            width: 100%;
            padding: 20px;
            margin-bottom: 20px;
            text-align: left;
        }

        textarea,
        select {
            width: 100%;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 6px;
            box-sizing: border-box;
            margin-top: 8px;
            margin-bottom: 16px;
            font-size: 16px;
        }
</style>

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">

</head>

<body>

	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->
	<div class="header">
		<jsp:include page="../Mcommon/top.jsp" />
	</div>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->



	<!-- section 시작 -->

	<section class="section" style="margin:5%; margin-left: 100px;">
		
		<!-- left-container 시작 -->
		<div class="left-container" style="margin: 15%; width: 70%;">
			<div class="col-md-7 col-lg-8" style="width: 100%;">
				<h4 class="mb-3">주문서</h4>
				<hr>
				<h4 class="mb-3">구매 희망 상품</h4>
				
				<form action="./OrderSalePay.od" method="post">
				
				<table class="table table-dark table-striped">
					<thead>
						<tr>
							<th scope="col">품번</th>
							<th scope="col">상품명</th>
							<th scope="col">가격</th>
							<th scope="col">수량</th>
							<th scope="col">옵션</th>
							<th scope="col">옵션추가금</th>
							
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${requestScope.cartList}">
						<tr>
							<td>${item.item_id}</td>
							<td>${item.item_name }</td>
							<td><fmt:formatNumber value="${item.item_price }"/> </td>
							<td>${item.cart_quantity}</td>
							<td>${item.options_value}</td>
							<td style="text-align: center;">${item.options_price}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

				
				<hr class="my-4">



				<h4 class="mb-3">쿠폰</h4>
				<div class="coupon-select">
					<label for="coupon">쿠폰 선택:</label> 
					<select id="coupon" name="coupon">
						<option value="coupon1">사용할수 있는 쿠폰이 없습니다.</option>
						<!-- 추가 쿠폰 옵션 -->
					</select>
				</div>
				
				<!-- 11월 23일 추가 (마일리지 사용) 시작 -->
					<h4 class="mb-3">포인트</h4>
					<div class="point">
						<label for="point" class="form-label">사용가능한 포인트 : ${user.member_point}</label> 
						<input type="number" class="form-control" max=${user.member_point } name="usePoint" id="usePoint">
					</div><br>
					

				<!-- 11월 23일 추가 (마일리지 사용) 끝 -->
				
				<h4 class="mb-3">배송지</h4>
				<select class="form-select form-select-lg mb-3" aria-label="Large select example" name="location_id" id="location_id">
 	 				<c:forEach var="ldto" items="${locations}">
  					<option value="${ldto.location_id}">${ldto.location_name},${ldto.location_add }</option>
  					</c:forEach>
				</select>
				<div onclick="listPopup()">
					배송지 목록
				</div>
				
				<div>
					<input type="hidden" name=strCartList value="${strCartList}">
				</div>
				

				<hr>
				<h4 class="mb-3">할인적용</h4>
				<hr>
			
				
				<h4 class="mb-3">총 가격</h4>  <h4 class="mb-3"><fmt:formatNumber value="${totalPrice }"/>  원</h4> 
		
				<div style="margin-left:25%">
					<input type="submit" class="btn btn-secondary btn-lg" style="width:25%" value="결제"></button>
					<button type="button" class="btn btn-secondary btn-lg" style="width:25%">취소</button>
				</div>
				</form>
		</div>
		
	</div>
<!-- right-container 끝 -->

	</section>

	<!-- footer 시작 -->
	<footer class="footer">
		<p>&copy; 2023 SSB Style</p>
	</footer>
	<!-- footer 끝 -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>

<script>
	document.getElementById("usePoint").defaultValue = '0';
</script>


</body>

</html>




