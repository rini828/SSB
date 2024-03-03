<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script defer
	src="${pageContext.request.contextPath}/board/review/rating.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#file").on("change", function(e) {
			var file = e.target.files[0];
			if (isImageFile(file)) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#preview").attr("src", e.target.result);
				}
				reader.readAsDataURL(file);
			} else {
				alert("이미지 파일만 첨부 가능합니다.");
				$("#file").val("");
				$("#preview").attr("src", "");
			}
		});
	});

	// 업로드 파일 이미지 파일인지 확인
	function isImageFile(file) {
		// 파일명에서 확장자를 가져옴
		var ext = file.name.split(".").pop().toLowerCase();
		return ($.inArray(ext, [ "jpg", "jpeg", "gif", "png" ]) === -1) ? false
				: true;
	}
</script>

<link href="${pageContext.request.contextPath}/board/review/rating.css"
	rel="stylesheet" />
	
<!-- 리뷰 작성 팝업 CSS -->
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

/* 컨텐츠, 버튼 영역 padding */
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
	height: 38px;
	color: #fff;
	font-size: 14px;
	line-height: 18px;
}

.enroll_btn {
	background-color: #000000;
	padding-top: 5px;
	display: inline-block;
	width: 100px;
	height: 38px;
	color: #fff;
	font-size: 14px;
	line-height: 18px;
}

/* 제품명 영역 */
.item_div h2 {
	margin: 0;
}

/* 리뷰 작성 영역 */
.content_div {
	padding-top: 10px;
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
	color: #a9a9a9;
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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<div class="wrapper_div">
		<div class="subject_div">리뷰 작성</div>
	</div>

	<form method="post" enctype="multipart/form-data" id="reviewForm">
		<input type="hidden" name="userId" value="${sessionScope.userId }">
		<input type="hidden" name="itemId" value="${param.itemId }">
		<input type="hidden" name="orders_sort" value="${param.orders_sort }">
		<div class="input_wrap">

			<div class="r_div1">
				<strong>상품은 어떠셨나요?</strong>
			</div>
			<div class="r_div2">상품에 대한 별점을 매겨주세요.</div>

			<div class="rating_box">
				<div class="rating">
					★★★★★ <span class="rating_star">★★★★★</span> <input type="range"
						name="rating" value="1" step="1" min="0" max="10">
				</div>
			</div>

			<div class="content_div">
				<h4>내용</h4>
				<textarea name="content" placeholder="최소 10자 이상 작성해 주세요"></textarea>
				<label for="file"><img id="preview"
					src="${pageContext.request.contextPath}/board/review/file.png"
					style="max-width: 200px;"></label> <input type="file" name="file"
					id="file" style="display: none" accept=".png, .jpeg">
			</div>
			<div class="checkbox">
				<input type="checkbox">보다 나은 후기 서비스 제공을 위해 정보 수집ㆍ이용에 동의합니다.
				(선택)
			</div>
		</div>

		<div class="btn_wrap">
			<button class="cancel_btn">취소</button><button class="enroll_btn">등록</button>
		</div>
	</form>

	<script>
		/* 취소 버튼 */
		$(".cancel_btn").on("click", function(e) {
			window.close();
		});

		/* 등록 버튼 */
		$(".enroll_btn").on("click", function(e) {

			e.preventDefault();
			var form = $('#reviewForm')[0];
			var data = new FormData(form);

			$.ajax({
				url : './ReviewWriteAction.rv',
				type : 'POST',
				enctype : 'multipart/form-data',
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert("소중한 리뷰 감사합니다.");
					window.close();
				},
				error : function(e) {
					console.log("ERROR: ", e);
					alert("리뷰 등록에 실패했습니다. 다시 작성 해주세요.");
				}
			});

		});
	</script>
</body>
</html>