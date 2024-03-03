<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSB Location</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="./location/locationInsert.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="./location/locationInsert.css">

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<main>
		<div class="head">
			<h1>배송지 등록</h1>
		</div>
		<div class="talbe">
			<table>
			<colgroup>
				<col width="30%">
				<col width="70%">
			</colgroup>
				<tr>
					<td><label>받는사람</label></td>
					<td><input type="text" id="location_name" placeholder="받는사람" value="${dto.location_name}" required="required"></td>
				</tr>
				<tr>
					<td><label>전화번호</label></td>
					<td>
						<!-- <input type="tel" name="location_phone" placeholder="수취인 전화번호" value="${dto.location_phone}" required="required"> -->
						<input id="location_phone0" type="text" name="location_phone[]" pattern="\d*" maxlength="3" value="${dto.location_phone.substring(0,3)}" required="required">
						<label> - </label>
						<input id="location_phone1" type="text" name="location_phone[]" pattern="\d*" maxlength="4" value="${dto.location_phone.substring(3,7)}" required="required">
						<label> - </label>
						<input id="location_phone2" type="text" name="location_phone[]" pattern="\d*" maxlength="4" value="${dto.location_phone.substring(7,11)}" required="required">
					</td>
				</tr>
				<tr>
					<td><label>주소</label></td>
					<td>
						<input type="text" id="sample6_postcode" placeholder="우편번호" value="${dto.location_postcode}" readonly="readonly">
						<input type="button" id="locationButton"  onclick="sample6_execDaumPostcode()" value="검색">
						<br>
						<input type="text" id="sample6_address" placeholder="주소" value="${dto.location_add}" readonly="readonly">
						<br>
						<input type="text" id="sample6_extraAddress" placeholder="상세주소" value="${dto.locationD_add}" required="required">
						<br>
						<input type="text" id="location_title" placeholder="배송지 이름" value="${dto.location_title}" required="required">
					</td>
				</tr>
				<tr>
					<td><label>배송요청사항</label></td>
					<td>
						<select id="sample6_detailAddress" >
							<option label="배송 시 요청사항을 선택해주세요" selected="selected">
							<option label="부재시 경비실에 맡겨주세요" value="부재 시 경비실에 맡겨주세요">
							<option label="부재 시 택배함에 넣어주세요" value="부재 시 택배함에 넣어주세요">
							<option label="부재 시 집 앞에 놓아주세요" value="부재 시 집 앞에 놓아주세요">
							<option label="배송 전 연락 바랍니다" value="배송 전 연락 바랍니다">
							<option label="파손의 위험이 있는 상품입니다.배송 시 주의해 주세요" value="파손의 위험이 있는 상품입니다.배송 시 주의해 주세요">
							<option label="직접 입력">
						</select>
						<br>
						<textarea id="directInput" hidden=""></textarea>
					</td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="location_id" value="${dto.location_id}">
		<input type="hidden" id="location_phone">
		<div class="button">
			<!-- 세션에서 회원ID 받아오게 바꾸기 -->
			<input type="button" value="등록" onclick="return validityCheck()">
			<input type="button" value="취소" onclick="window.close();">
		</div>
	</main>
</body>
</html>