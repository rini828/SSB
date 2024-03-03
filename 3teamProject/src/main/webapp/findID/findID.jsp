<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find ID</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="findID.css">
    <!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<div class="container">
        <h1>아이디 찾기</h1>
        <form id="emailForm" onsubmit="sendEmail(); return false;">
            <label for="email">이메일 입력</label>
            <input type="email" id="email" name="email" required>
            <button type="submit">인증 코드 받기</button>
        </form>

        <h3>인증 코드 입력</h3>
        <form id="codeForm" onsubmit="submitCode(); return false;">
            <label for="code">인증 코드</label>
            <input type="text" id="code" name="code" required>
            <button type="submit">완료</button>
        </form>
	</div>

<script>
    function sendEmail() {
        const userEmail = document.getElementById("email").value;

        // AJAX 요청을 사용하여 서버에 이메일 전송 요청을 보내는 코드
        $.ajax({
            type: 'POST',
            url: 'findIDController.fi', // 수정된 URL
            data: { email: userEmail },
            success: function(data) {
                alert(data);
                document.getElementsByTagName("h1")[0].innerText = "인증 코드 입력 페이지";
                document.getElementsByTagName("label")[0].innerText = "인증 코드";
                document.getElementsByTagName("button")[0].innerText = "완료";
                document.forms[0].setAttribute("onsubmit", "submitCode(); return false;");
            },
            error: function(error) {
                console.error("Error:", error);
            }
        });
    }

    function submitCode() {
        const userCode = document.getElementById("code").value;

        // AJAX 요청을 사용하여 서버에 인증 코드 검증 요청을 보내는 코드
        $.ajax({
            type: 'POST',
            url: 'findIDController.fi', // 수정된 URL
            data: { code: userCode },
            success: function(data) {
                alert(data);
            },
            error: function(error) {
                console.error("Error:", error);
            }
        });
    }
</script>

</body>
</html>
