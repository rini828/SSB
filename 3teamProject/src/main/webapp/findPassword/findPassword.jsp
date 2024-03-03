<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Find Password</title>
    
    <!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
    <h1>비밀번호 찾기</h1>
    <form onsubmit="sendEmail(); return false;">
        <label for="email">이메일</label>
        <input type="email" id="email" name="email" required>
        <label for="username">아이디</label>
        <input type="text" id="username" name="username" required>
        <button type="submit">인증 코드 받기</button>
    </form>

    <h1>인증 코드 입력</h1>
    <form onsubmit="submitCode(); return false;">
        <label for="code">인증 코드</label>
        <input type="text" id="code" name="code" required>
        <button type="submit">완료</button>
    </form>

    <script>
        function sendEmail() {
            // 이메일 전송 코드
            const userEmail = document.getElementById("email").value;
            const username = document.getElementById("username").value;
            // userEmail 변수를 이용하여 이메일을 전송

            // 이메일 전송 성공 시 아래의 코드 실행
            alert("인증 코드가 이메일로 전송되었습니다.");

            // 입력창 변경
            document.getElementsByTagName("h1")[0].innerText = "인증 코드 입력 페이지";
            document.getElementsByTagName("label")[0].innerText = "인증 코드";
            document.getElementsByTagName("button")[0].innerText = "완료";
            document.forms[0].setAttribute("onsubmit", "submitCode(); return false;");
        }

        function submitCode() {
            // 인증 코드 확인 및 처리 코드
            const userCode = document.getElementById("code").value;
            // userCode 변수를 이용하여 인증 코드 처리

            // 인증 코드 확인 성공 시 아래의 코드 실행
            alert("비밀번호 찾기가 완료되었습니다.");
        }
    </script>
</body>
</html>
