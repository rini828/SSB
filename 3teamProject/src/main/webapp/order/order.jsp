<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<table border="1px solid black">
		<tr>
			<td>제품명</td>
			<td>옵션명</td>
			<td>옵션값</td>
			<td>장바구니 수량</td>
			<td>제품가격</td>
		</tr>
		<c:forEach var="dto" items="${dtoArray }">
			<tr>
				<td>${dto.item_name }</td>
				<td>${dto.options_name }</td>
				<td>${dto.options_value }</td>
				<td>${dto.cart_quantity }</td>
				<td>${(dto.item_price + dto.options_price) * dto.cart_quantity }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>