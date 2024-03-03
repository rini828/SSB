<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSB Cart</title>
<!-- css -->
<link rel="stylesheet" href="./cart/cartList.css">
<link rel="stylesheet" href="./location/location.css">
<!-- css -->
<!-- jquery cnd -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<!-- jquery cnd -->
<!-- 자바스크립트 -->
<script src="./Mcommon/util.js"></script>
<script src="./cart/cartListJS.js"></script>
<!-- 자바스크립트 -->
<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
<!-- 파비콘 -->
</head>
<body>
	<header>
		<jsp:include page="../Mcommon/top.jsp" />
	</header>
	<main>
		<!-- 옵션 변경창(숨겨져있음) -->
		<div id="optionSelecter" hidden="">
			<div>
			<h1>옵션변경</h1>
				<div class="input">
					<div>
						<!-- 옵션 선택 셀렉터 -->
						<select id="chooseOptions"></select>
					</div>
					<div>
						<!-- 갯수 선택 인풋 -->
						<input type="number" max="10" min="1" id="selectedQuantitiy">
					</div>
				</div>
				<div class="button">
					<!-- 변경 버튼 -->
					<input type="button" value="변경" onclick="updateCart()" id="changeButton">
					<!-- 취소 버튼 -->
					<input type="button" value="취소" onclick="optionSelecterClose()" id="closeButton">
				</div>
			</div>
		</div>
		<!-- 주문 폼 -->
		<form action="./Order.od" method="post">
			<table class="table">
				<colgroup>
					<col width="5%">
					<col width="15%">
					<col width="10%">
					<col width="20%">
					<col width="10%">
					<col width="20%">
					<col width="10%">
					<col width="10%">
				</colgroup>
				<thead class="thead">
					<tr>
						<th><input type="checkbox" checked="checked" id="checkAll"></th>
						<th>이미지</th>
						<th>제품번호</th>
						<th>제품명</th>
						<th>옵션</th>
						<th>장바구니 수량</th>
						<th>가격</th>
						<th>옵션변경</th>
					</tr>
				</thead>
				<tbody class="tbody">
					<c:forEach var="dto" items="${dtoArray}">
						<tr name="cart" value="${dto.cart_id}">
							<td><input type="checkbox" name="cart_id" value="${dto.cart_id}" checked="checked"></td>
							<td><img width="35" height="35" alt="제품이미지" src="./main/item_img/${dto.item_img_main}"></td>
							<td name="item_id">${dto.item_id}</td>
							<td name="item_name"><a href="./itemDetails.in?item_id=${dto.item_id}">${dto.item_name}</a></td>
							<td name="options_value">${dto.options_value}</td>
							<td name="cart_quantity">${dto.cart_quantity}</td>
							<td name="options_price">${(dto.item_price + dto.options_price) * dto.cart_quantity}</td>
							<td><input type="button" value="변경" onclick="getOptions('${dto.cart_id}','${dto.item_id}','${dto.cart_quantity}');"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="moveButton">
				<input type="hidden" id="checkArray" name="checkArray">
				<input type="button" value="삭제" onclick="deleteCart()">
				<input type="submit" value="주문" onclick="arrayData()">
				<label id="totalPrice">options_price</label>
			</div>
		</form>
	</main>
	<footer>
		<jsp:include page="../Mcommon/footer.jsp" />
	</footer>
</body>
</html>
