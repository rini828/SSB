<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
<meta name="author" content="AdminKit">
<meta name="keywords"
	content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

<title>SSB 관리자 로그인</title>

<!-- Bootstrap CSS -->
<link href="css/app.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap"
	rel="stylesheet">
<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
<style>
  @import url('https://fonts.googleapis.com/css2?family=Archivo&display=swap');
  @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
  
* {
	font-family: 'Archivo', 'Noto Sans KR', sans-serif;
}
</style>
</head>
<body>
	<main class="d-flex w-100">
		<div class="container d-flex flex-column">
			<div class="row vh-100">
				<div
					class="col-sm-10 col-md-8 col-lg-6 col-xl-5 mx-auto d-table h-100">
					<div class="d-table-cell align-middle">
						<div class="text-center mt-4">
							<h1 class="h2">SSB 관리자 로그인</h1>
							<p class="lead"></p>
						</div>
						<div class="card">
							<div class="card-body">
								<div class="m-sm-3">
									<form action="./AdminLoginAction.ad" method="post">
										<div class="mb-3">
											<label class="form-label">아이디</label> <input
												class="form-control form-control-lg" type="text"
												name="adminId" placeholder="아이디를 입력하세요" />
										</div>
										<div class="mb-3">
											<label class="form-label">비밀번호</label> <input
												class="form-control form-control-lg" type="password"
												name="adminPw" placeholder="비밀번호를 입력하세요" />
										</div>
										<div class="d-grid gap-2 mt-3">
											<input type="submit" value="로그인"
												class="btn btn-lg btn-primary">
										</div>
										<div>
											<a href="./Main.in" style="color: lightgray;">home</a>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<script src="js/app.js"></script>
</body>
</html>