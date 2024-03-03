<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSB WishList</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="./wishlist/wishlist.js"></script>
<script type="text/javascript" src="./wishlist/wishlistPage.js"></script>
<link rel="stylesheet" href="./location/location.css">

<style type="text/css">
td{
vertical-align: middle;}

</style>


<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<header>
		<jsp:include page="../Mcommon/top.jsp" />
	</header>
	<main>

		<table class="table">
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="20%">
				<col width="30%">
				<col width="10%">
			</colgroup>
			<thead class="thead">
				<tr>
					<th><input type="checkbox" id="checkAll"></th>
					<th>제품번호</th>
					<th>제품이름</th>
					<th>제품이미지</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody class="tbody">
				<c:forEach var="dto" items="${dtoArray}">
					<tr>
						<td><input type="checkbox" name="wishlist_id"
							value="${dto.item_id}"></td>
						<td>${dto.item_id}</td>
						<td><a href="./itemDetails.in?item_id=${dto.item_id}"> ${dto.item_name} </a></td>
						<td><img width="50" height="50" src="./main/item_img/${dto.item_img_main}" /> </td>
						<td><fmt:formatNumber value="${dto.item_price}"/> 원</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="moveButton">
			<input type="hidden" id="checkArray" name="checkArray"> <input
				type="button" value="삭제" onclick="deleteWishlist()">
				
		</div>
	</main>
	<footer>
		<jsp:include page="../Mcommon/footer.jsp" />
	</footer>
</body>
</html>