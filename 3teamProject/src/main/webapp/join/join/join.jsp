<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link href="./join/join.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script type="text/javascript">
	
	
</script>
</head>
<body>


<!--  -------------------------------------------탑 컨테이너 넣는 곳 ---------------------------------------------- -->
<!-- <div class="container-fluid"> -->
	<!-------------------  로고 넣는 곳 시작 --------------->
<!-- 	<div class="logo" style="margin-left: 24%;"> -->
<!-- 		<img alt="SSB_black" src="../img/SSB_black.png" width="150px"> -->
<!-- 	</div> -->
	<!------------------  로고 넣는 곳  끝----------------->
<!-- </div> -->
<!--  -------------------------------------------탑 컨테이너 끝 ---------------------------------------------- -->

<!---------------------------left-panel 시작---------------------------------->
<!-- <div class="left-panel"></div> -->

<!-- 메인 컨테이너 -->
<div class="main-container" >
		<img alt="SSB_black" src="./main/img/SSB_black.png" width="150px" >
	

	<div class="form-container">

		<main class="form-signin m-auto">
			<!-- form 시작 -->
			<form action="./MemberJoinAction.me" method="post" name="fr" onsubmit="return check();">
				<h1 class="h3 mb-3 fw-normal">회원가입</h1>
				<!-- 아이디 -->
				<div class="col">
					<div class="input-group">
						<div class="form-floating">
							<input type="text" name="member_user_id" class="form-control input-cc inputId" id="floatingId" maxlength="40" placeholder=""> 
							<label for="floatingId">아이디(필수)</label>
							<font id="checkId" size="2" class="position-absolute top-50 start-20 translate-middle-y" style="right: 9px; margin-top: 2px"></font>
						</div>
					</div>
				</div>
				
				<!-- 비밀번호  -->
				<div class="col">
					<div class="input-group">
						<div class="form-floating">
							<input type="password" name="member_pw" class="form-control input-cc inputPw" id="floatingPassword" maxlength="40" placeholder=""> 
							<label for="floatingPassword">비밀번호(필수)</label>
							<div class="password-hints">
								<font class="hint1" color="red">대소문자 &#10003;</font>
								<font class="hint2" color="red">숫자 &#10003;</font>
								<font class="hint3" color="red">특수문자 &#10003;</font>
								<font class="hint4" color="red">8~20자 이내 &#10003;</font>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 비밀번호 확인 -->				
				<div class="col">
					<div class="input-group">
						<div class="form-floating">
							<input type="password" name="member_pw2" class="form-control input-cc inputPw2" id="floatingCheckPassword" maxlength="40" placeholder=""> 
							<label for="floatinCheckPassword">비밀번호 확인(필수)</label>
							<font id="pwDoubleCheck" size="2" color="red" class="position-absolute top-50 start-20 translate-middle-y" style="right: 9px; margin-top: 2px"></font>
						</div>
					</div>
				</div>
				
				<!-- 이메일 입력-->
				<div class="form-floating d-flex">
				    <input type="text" name="member_email" class="form-control flex-grow-1 input-cc " id="floatingEmail" placeholder="">
				    <label for="floatingEmail" class="flex-grow-4">이메일 입력</label>
				    
             			 <span class="input-group-text align-items-end">@</span>
             			 
				    <input type="text" name="member_email2" class="form-control flex-grow-1 input-cc " id="domain-txt" placeholder=''>
				    <select name="domain" class="form-control flex-grow-1" id="domain-list">
				        <option value="type" style="vertical-align: middle;">직접 입력</option>
				        <option value="naver.com">naver.com</option>
				        <option value="google.com">google.com</option>
				        <option value="hanmail.net">hanmail.net</option>
				        <option value="nate.com">nate.com</option>
				        <option value="kakao.com">kakao.com</option>
				    </select>
				</div>
				
				<!-- 이름 -->
				<div class="form-floating d-flex mt-2">
					<input type="text" name="member_name" class="form-control input-cc " id="floatingName" placeholder=""> 
          			<label for="floatingName">이름</label>
				</div>
				
				<!-- 생년월일 -->
				<div class="form-floating d-flex">
					<input type="text" name="member_birth" class="form-control input-cc " id="floatingBirth" placeholder="" > 
          			<label for="floatingBirth">생년월일 ex)'20000101'</label>
				</div>
				
				<!-- 성별 -->
				<div class="form-floating d-flex">
				    <div class="form-check form-check-inline">
				        <input type="radio" name="member_gender" class="form-check-input" id="floatingKor" value="M" checked>
				        <label class="form-check-label" for="floatingKor">남</label>
				    </div>
				    <div class="form-check form-check-inline">
				        <input type="radio" name="member_gender" class="form-check-input" id="floatingFor" value="W">
				        <label class="form-check-label" for="floatingFor">여</label>
				    </div>
				</div>
				
				<!-- 전화번호 -->
				<div class="form-floating d-flex">
					<input type="text" name="member_phone" class="form-control input-cc" id="floatingTel" placeholder=""> 
					<label for="floatingTel"><span>전화번호 '-' 제외 입력</span></label>
				</div>
				
				<!-- 마케팅 수신 동의 -->
				<div class="form-floating d-flex">
				    <div class="form-check form-check-inline">
				        <input type="checkbox" name="member_agree" class="form-check-input" id="floatingAgree" value="Y">
				        <label class="form-check-label" for="floatingAgree">마케팅 수신 동의(선택)</label>
				    </div>
				</div>
				
<!-- 				<div class="info"> -->
<!-- 					<span><button style="border: none; background-color: none; font-size: 8px;" onclick="location.href='#'">고객 센터</button></span> -->
<!-- 				</div> -->
				<div class="form-floating" id="buttons">
					<button class="btn btn-outline-dark w-25 py-2" id="submitButton" type="submit" name="member_situation" value="가입" disabled>회원가입</button>
					<button class="btn btn-outline-dark w-25 py-2" type="button" style="margin-left: 10%" onclick=history.go(-1);>취소</button>
				</div>
			</form>
			<!-- form 끝 -->
		</main>
	</div>

</div>
<!-- 메인 컨테이너-->


<!---------------------------left-panel 종료---------------------------------->


<br>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="./join/join.js"></script>
</body>

</html>