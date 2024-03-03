<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSB 회원탈퇴</title>
<link href="../main/main.css" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link href="./closeAccount/closeAccount.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>	
	
<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<jsp:include page="../Mcommon/top.jsp"></jsp:include>

	<!-- section 시작 -->
	<div class="bl"></div>

	<section class="section">
		<div class='sectionIn'>
			<h3>회원 탈퇴</h3>
			<div class="bl2">
				<div id="withdrawalForm2">
					<form action="./MemberCloseAccountAction.me" id="reasonForm" name="rf">

						<!-- 비밀 번호 확인 -->
						<div class="checkPw">
							<font size="3"><b>비밀번호 :</b></font> <input type="password" class="inputPw" id="checkPw" name="userPw" placeholder="비밀번호 확인">
							<span class="pwChecking"></span>
						</div>
						<!-- 비밀 번호 확인 -->
						
						<!-- 탈퇴 사유 -->
						<div class="reasonOption">
							<label for="withdrawalReason"><b>탈퇴 사유 :</b></label>
					            <select id="withdrawalReason" name="withdrawalReason">
					                <option value="r1">낮은 이용 빈도</option>
					                <option value="r3">사용하기 불편</option>
					                <option value="r2">다른 서비스 이용 중</option>
					                <option value="r3">서비스 불만족</option>
					                <option value="r4">마음에 드는 스타일이 없음</option>
					                <option value="r5">개인정보 유출 우려</option>
					                <option value="r6">기타</option>
					        </select>
						</div>
						<!-- 탈퇴 사유 -->
						
						<!-- 유의 사항 -->						
						<div class="mb">
							<p class="p1">회원 탈퇴 후 아이디 재사용 및 복구 불가</p>
							<ul>
								<li>탈퇴 후 <b>아이디는 30일 동안 재사용 불가하며, 복구 역시 불가</b>합니다.
								</li>
							</ul>
						</div>

						<div class="mb">
							<p class="p1">탈퇴 후 재가입 시 가입 혜택 제한</p>
							<ul>
								<li>탈퇴 후 <b>재가입 시 신규 가입 혜택에 제한</b>이 있을 수 있습니다.
								</li>
							</ul>
						</div>

						<div class="mb">
							<p class="p1">회원정보/서비스 이용기록 삭제</p>
							<ul>
								<li>회원등급, 적립금, 포인트 등 <b>데이터가 영구히 삭제되며 복구가 불가</b>합니다.
								</li>
							</ul>
						</div>

						<div class="mb">
							<p class="p1">등록 게시물 유지</p>
							<ul>
								<li>각종 게시판의 게시글, 댓글, 후기 등의 데이터는 삭제되지 않습니다. <b>삭제를 원하는
										게시글이 있다면 반드시 탈퇴 전 직접 삭제</b>하시기 바랍니다.
								</li>
							</ul>
						</div>

						<div class="mb">
							<p class="p1">개인 정보 보관</p>
							<ul>
								<li>회원 탈퇴 후 <b>개인 정보는 30일 동안 보관하며</b>, 그 후 삭제됩니다.
								</li>
							</ul>
						</div>
						
						<!-- 유의 사항 -->						
						<div id="fs">
							<input type="checkbox" id="checkBox" /> 
							<label for="checkBox"> <span></span> 유의사항을 모두 확인하였으며, 이에 동의합니다.</label>
						</div>
							
						<div id="buttons">
							<button type="button" class="checkButton" onclick="confirmCloseAccount()" disabled="disabled">탈퇴하기</button>
							<button type="button" style="margin-left: 1%" onclick="location.href='./Main.in'">취소</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- section 끝  -->

	<!-- footer 시작 -->
	<footer class="footer" style="text-align: center;">
		<p>&copy; 2023 SSB Style</p>
	</footer>
	<!-- footer 끝 -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="./closeAccount/closeAccount.js"></script>
</body>
</html>