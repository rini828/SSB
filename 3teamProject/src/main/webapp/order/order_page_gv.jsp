<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>이쁜 결제창</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-color: #f7f7f7;
}

.payment-container {
	width: 800px;
	background-color: #fff;
	padding: 30px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	border-radius: 12px;
	text-align: center;
}

.product-table {
	margin-bottom: 20px;
	width: 100%;
	border-collapse: collapse;
}

.product-table th, .product-table td {
	border: 1px solid #ddd;
	padding: 15px;
	text-align: left;
}

.address-input, .shipping-note, .coupon-select {
	width: 100%;
	padding: 20px;
	margin-bottom: 20px;
	text-align: left;
}

textarea, select {
	width: 100%;
	padding: 15px;
	border: 1px solid #ddd;
	border-radius: 6px;
	box-sizing: border-box;
	margin-top: 8px;
	margin-bottom: 16px;
	font-size: 16px;
}

.payment-button {
	width: 100%;
	padding: 20px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	font-size: 18px;
}
</style>

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<div class="payment-container">
		<table class="product-table">
			<thead>
				<tr>
					<th>상품 번호</th>
					<th>상품 이름</th>
					<th>가격</th>
					<th>수량</th>
					<th>총 가격</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>상품 1</td>
					<td>$10.00</td>
					<td>2</td>
					<td>$20.00</td>
				</tr>
				<tr>
					<td>2</td>
					<td>상품 2</td>
					<td>$20.00</td>
					<td>1</td>
					<td>$20.00</td>
				</tr>
				<!-- 추가 상품 행 -->
			</tbody>
		</table>

		<div class="address-input">
			<label for="address">주소 입력:</label>
			<textarea id="address" name="address" placeholder="배송 주소를 자세히 입력하세요"></textarea>
		</div>

		<div class="shipping-note">
			<label for="shippingNote">배송시 주의사항:</label>
			<textarea id="shippingNote" name="shippingNote"
				placeholder="배송시 유의할 사항을 입력하세요"></textarea>
		</div>

		<div class="coupon-select">
			<label for="coupon">쿠폰 선택:</label> <select id="coupon" name="coupon">
				<option value="coupon1">쿠폰 1</option>
				<option value="coupon2">쿠폰 2</option>
				<!-- 추가 쿠폰 옵션 -->
			</select>
		</div>

		<button class="payment-button" onclick="processPayment()">결제하기</button>
	</div>

	<script>
        function processPayment() {
            // 결제 처리 로직을 추가하세요
            alert('결제가 완료되었습니다!');
        }
    </script>
</body>
</html>