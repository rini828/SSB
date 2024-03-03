<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<!-- header js 코드 영역 접어둠. -->
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

<!-- 상단 메뉴바 오픈 및 영역 확장 js 코드 접어둠.-->
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


<!-- 이전 url 주소 호출 -->


</script>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SSB Layout</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./login/login.css" rel="stylesheet">
<link href="./main/main.css" rel="stylesheet">

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->
	<div class="header">
		<jsp:include page="../Mcommon/top.jsp" />
	</div>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->
	<div calss="doum"></div>
	<div id="login_wrap">
		<div class="main-container">

			<div class="form-container" style="width: 380px;">
				<h1 class="h3 mb-3 fw-normal">로그인</h1>
				<form action="./loginAction.lg" method="POST">
					<div class="form-floating">
						<input type="text" class="form-control" id="floatingInput"
							name="member_user_id" placeholder="ID"> <label
							for="floatingInput">아이디</label>
					</div>
					<div class="form-floating">
						<input type="password" class="form-control" id="floatingPassword"
							name="member_pw" placeholder="Password"> <label
							for="floatingPassword">비밀번호</label>
					</div>

					<div>
						<button class="login-button-custom" type="submit">로그인</button>
					</div>
				</form>
				<div class="info">
					<span>
						<button
							style="border: none; background-color: transparent; font-size: 12px; border: none;"
							onclick="window.location.href='./findID.fi'">아이디 찾기</button>
					</span> <span>&nbsp;</span> <span>
						<button
							style="border: none; background-color: transparent; font-size: 12px; border: none;"
							onclick="window.open('./findPassword/findPassword.jsp', '_blank')">비밀번호
							찾기</button>
					</span> <span>&nbsp;</span> <span>
						<button
							style="border: none; background-color: transparent; font-size: 12px; border: none;">
							<a href="./MemberJoin.me">회원가입</a>
						</button>
					</span>
				</div>

				<br>
				<div class="d-grid gap-2" style="text-align: center;">
					<a href="javascript:kakaoLogin();"><img
						src="./login/img/kakao_login.png" alt="카카오계정 로그인" /></a>
					<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
					<script>
                        window.Kakao.init('7dab86842cbd0450f16b4b7691565447');
                        function kakaoLogin() {
                            window.Kakao.Auth.login({
                                scope: 'profile_nickname',
                                success: function (response) {
                                    console.log(response);
                                    window.Kakao.API.request({
                                        url: '/v2/user/me',
                                        success: (res) => {
                                            const kakao_account = res.kakao_account;
                                            console.log(kakao_account);
                                        }
                                    });
                                    window.location.href = './Main.in';
                                },
                                fail: function (error) {
                                    console.log(error);
                                }
                            });
                        }
                    </script>
					<a
						href="https://accounts.google.com/o/oauth2/v2/auth?c lient_id=253324653095-4p0h7jpcqct6512k21j23k72qkphd812.apps.googleusercontent.com&redirect_uri=./main.in&response_type=code&scope=openid%20email%20profile">
						<img src="./login/img/google_login.png" alt="구글 계정 로그인" /> <!-- 여기서 임시로 리다이렉트할 주소를 네이버로 해뒀습니다. 메인페이지 주소를 추후에 google developer에서 api 관리에 들어가서 수정할수 있습니다. -->
					</a> <a
						href="https://nid.naver.com/nidlogin.login?mode=form&url=https://www.naver.com/">
						<img src="./login/img/naver_login.png" alt="네이버 계정 로그인" />
					</a>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/js/active.js"></script>
</body>
</html>
