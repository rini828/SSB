<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 작성</title>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<style type="text/css">
/* 창 여분 없애기 */
body {
	margin: 0;
}

/* 전체 배경화면 색상 */
.wrapper_div {
	background-color: #f5f5f5;
	height: 100%;
}

/* 팝업창 제목 */
.subject_div {
	background-color: #000000;
	color: white;
	font-weight: bold;
	padding: 10px;
	width: 96%;
}

/* 컨텐츠, 버튼 영역 */
.input_wrap {
	padding: 20px;
}

.btn_wrap {
	padding: 5px 30px 30px 30px;
	text-align: center;
}

/* 버튼 영역 */
.cancel_btn {
	background-color: #000000;
	padding-top: 5px;
	margin-right: 5px;
	display: inline-block;
	width: 100px;
	height: 40px;
	color: #fff;
	font-size: 14px;
	line-height: 30px;
}

.enroll_btn {
	background-color: #000000;
	padding-top: 5px;
	display: inline-block;
	width: 100px;
	height: 40px;
	color: #fff;
	font-size: 14px;
	line-height: 30px;
}

/* 문의 작성 영역 */
.subject {
	border: 1px solid #dadada;
	width: 97%;
	height: 10px;
	padding: 12px 8px 12px 8px;
	font-size: 15px;
	color: #000000;
	resize: none;
	margin-top: 10px;
	margin-bottom: 10px;
}

.content_div1 {
	padding-top: 10px;
}

.content_div2 {
	padding-top: 20px;
}

.content_div h4 {
	margin: 0;
}

textarea {
	border: 1px solid #dadada;
	width: 97%;
	height: 100px;
	padding: 12px 8px 12px 8px;
	font-size: 15px;
	color: #000000;
	resize: none;
	margin-top: 10px;
	margin-bottom: 5px;
}
</style>

<!--------  jQuery  --------->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<!-- 사이드바 공통 토글 -->
<script src="${pageContext.request.contextPath}/admin/toggle.js"></script>


<!-- 파비콘 -->
<link rel="shortcut icon" href="./favicon/favicon.ico">
</head>
<body>
	<div class="wrapper_div">
		<div class="subject_div">문의 작성</div>
	</div>

	<form id="inquiry">
		<input type="hidden" name="userId" value="${sessionScope.userId }">
		<input type="hidden" name="itemId" value="${param.itemId }">
		<div class="input_wrap">
			<div class="content_div1">
				<label style="margin-right: 10px;"><strong>문의유형</strong></label> <input
					type="radio" id="type_0" name="iqType" value="사이즈"><label>사이즈</label>
				<input type="radio" id="type_1" name="iqType" value="배송"><label>배송</label>
				<input type="radio" id="type_2" name="iqType" value="재입고"><label>재입고</label>
				<input type="radio" id="type_3" name="iqType" value="제품상세"><label>제품상세</label>
			</div>

			<div class="content_div2">
				<label style="font-weight: bold;">제목</label> <input type="text"
					class="subject" name="subject" placeholder="제목을 입력해 주세요" required>
				<label style="font-weight: bold;">내용</label>
				<textarea name="content" rows="5" placeholder="내용을 입력해 주세요" required></textarea>
			</div>
		</div>
	</form>

	<div class="btn_wrap">
		<a class="cancel_btn">취소</a><a class="enroll_btn">작성하기</a>
	</div>

	<script>
		/* 취소 버튼 */
		$(".cancel_btn").on("click", function(e) {
			window.close();
		});

		/* 작성하기 버튼 */
		$(".enroll_btn").on("click", function(data) {

			var inquiry = $('inquiry').serialize();

			$.ajax({
				url : './InquiryItemQWriteAction.iq',
				type : 'POST',
				data : $('#inquiry').serialize(),
				success : function(data) {
					alert("문의글이 저장되었습니다.");
					window.close();
				}

			});

		});
	</script>
</body>
</html>