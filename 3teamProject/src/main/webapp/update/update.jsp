<%@page import="com.ssb.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 정보 수정</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./update/update.css" rel="stylesheet">
<link href="./main/main.css" rel="stylesheet">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.4.min.js">
    </script>

<!-- 회원정보 수정관련 스크립트 -->
</script>
<script type="text/javascript">
    function updateMember() {
        var currentPw = $("input[name='current_pw']").val(); // 추가된 부분
        var selectedField = $("input[name='selectedField']:checked").val();
        var value;

        // 기존 비밀번호 체크
        if (currentPw !== '<%=request.getAttribute("memberPw")%>') {
            alert("기존 비밀번호가 일치하지 않습니다!");
            return false;
        }
            switch (selectedField) {
                case "name":
                    value = $("#member_name").val();
                    break;
                case "phone":
                    value = $("#member_phone").val();
                    break;
                case "pw":
                    value = $("#member_pw").val();
                    break;
                case "email":
                    value = $("#member_email").val();
                    break;
                default:
                    console.error("Invalid selection");
                    return;
            }

            $.ajax({
                type: "POST",
                url: "updateAction.ud",
                data: {
                    "selectedField": selectedField,
                    "value": value,
                    "currentPw": currentPw // 추가된 부분
                },
                dataType: "json",
                success: function(response) {
                    console.log(response);
                    // 성공 시 처리, 필요한 경우 추가
                    if (selectedField === "pw") {
                        // 비밀번호 변경 후 추가 작업
                        alert("비밀번호가 변경되었습니다.");
                        // 메인 페이지로 이동
                        window.location.href = "./main.jsp";
                    }
                },
                error: function(xhr, status, error) {
                    console.error("AJAX 에러:", status, error);
                }
            });
        }

        // 기존 정보 표시 함수
        function populateExistingInfo() {
            var existingName = '<%=request.getAttribute("memberName")%>';
            var existingPw = '<%=request.getAttribute("memberPw")%>';
            var existingPhone = '<%=request.getAttribute("memberPhone")%>';
            var existingEmail = '<%=request.getAttribute("memberEmail")%>';

            $("#member_name").val(existingName);
            $("#member_phone").val(existingPhone);
            $("#member_email").val(existingEmail);
        }

        // 페이지 로딩 시 기존 정보 표시
        $(document).ready(function() {
            populateExistingInfo();
        });

        // 비밀번호 변경 확인
        function confirmPasswordChange() {
            var currentPw = prompt("현재 비밀번호를 입력하세요.", "");
            if (currentPw === null) {
                return; // 사용자가 취소 버튼을 눌렀을 때
            }

            var newPw = prompt("새로운 비밀번호를 입력하세요.", "");
            if (newPw === null) {
                return; // 사용자가 취소 버튼을 눌렀을 때
            }

            var retypePw = prompt("새로운 비밀번호를 다시 입력하세요.", "");
            if (retypePw === null) {
                return; // 사용자가 취소 버튼을 눌렀을 때
            }

            if (currentPw !== '<%=request.getAttribute("memberPw")%>') {
                alert("비밀번호를 올바르게 입력해주세요.");
            } else if (newPw !== retypePw) {
                alert("변경할 비밀번호가 다릅니다.");
            } else {
                var confirmChange = confirm("비밀번호를 변경하시겠습니까?");
                if (confirmChange) {
                    // 서버로 비밀번호 변경 요청 전송
                    $.ajax({
                        type: "POST",
                        url: "updateAction.ud",
                        data: {
                            "selectedField": "pw",
                            "value": newPw
                        },
                        dataType: "json",
                        success: function(response) {
                            console.log(response);
                            // 성공 시 처리
                            alert("변경 완료");
                            // 메인 페이지로 이동
                            window.location.href = "./main.jsp";
                        },
                        error: function(xhr, status, error) {
                            console.error("AJAX 에러:", status, error);
                        }
                    });
                }
            }
        }

        // 이메일 변경 확인
        function confirmEmailChange() {
            var newEmail = prompt("새로운 이메일을 입력하세요.", "");
            if (newEmail === null) {
                return; // 사용자가 취소 버튼을 눌렀을 때
            }

            var confirmChange = confirm("이메일을 변경하시겠습니까?");
            if (confirmChange) {
                // 서버로 이메일 변경 요청 전송
                $.ajax({
                    type: "POST",
                    url: "updateAction.ud",
                    data: {
                        "selectedField": "email",
                        "value": newEmail
                    },
                    dataType: "json",
                    success: function(response) {
                        console.log(response);
                        // 성공 시 처리
                        alert("변경 완료");
                        // 메인 페이지로 이동
                        window.location.href = "./main.jsp";
                    },
                    error: function(xhr, status, error) {
                        console.error("AJAX 에러:", status, error);
                    }
                });
            }
        }

        // 이름 변경 확인
        function confirmNameChange() {
            var newName = prompt("새로운 이름을 입력하세요.", "");
            if (newName === null) {
                return; // 사용자가 취소 버튼을 눌렀을 때
            }

            var confirmChange = confirm("이름을 변경하시겠습니까?");
            if (confirmChange) {
                // 서버로 이름 변경 요청 전송
                $.ajax({
                    type: "POST",
                    url: "updateAction.ud",
                    data: {
                        "selectedField": "name",
                        "value": newName
                    },
                    dataType: "json",
                    success: function(response) {
                        console.log(response);
                        // 성공 시 처리
                        alert("변경 완료");
                        // 메인 페이지로 이동
                        window.location.href = "./main.jsp";
                    },
                    error: function(xhr, status, error) {
                        console.error("AJAX 에러:", status, error);
                    }
                });
            }
        }

        // 전화번호 변경 확인
        function confirmPhoneChange() {
            var newPhone = prompt("새로운 전화번호를 입력하세요.", "");
            if (newPhone === null) {
                return; // 사용자가 취소 버튼을 눌렀을 때
            }

            var confirmChange = confirm("전화번호를 변경하시겠습니까?");
            if (confirmChange) {
                // 서버로 전화번호 변경 요청 전송
                $.ajax({
                    type: "POST",
                    url: "updateAction.ud",
                    data: {
                        "selectedField": "phone",
                        "value": newPhone
                    },
                    dataType: "json",
                    success: function(response) {
                        console.log(response);
                        // 성공 시 처리
                        alert("변경 완료");
                        // 메인 페이지로 이동
                        window.location.href = "./main.jsp";
                    },
                    error: function(xhr, status, error) {
                        console.error("AJAX 에러:", status, error);
                    }
                });
            }
        }
    </script>
<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->
	<div class="header">
		<jsp:include page="../Mcommon/top.jsp" />
	</div>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->

	<div class="content-container">
	
		<%
	String id = (String)session.getAttribute("userId");
	MemberDTO currentMember = (MemberDTO) request.getAttribute("currentMember");
	if (currentMember != null) { // null 체크 추가
    } else {
%>
		<p>현재 로그인된 계정 정보가 없습니다.</p>
		<%
    }
%>
		<fieldset style="text-align: center; margin: 0 auto; width: 50%;">
			<legend>회원정보 수정</legend>
			<form action="./updateProAction.ud" method="post" name="fr"
				onsubmit="return check();">
				<div style="text-align: center;">
					ID: <input type="text" name="user_id"
						value="<%= (currentMember != null) ? currentMember.getMember_user_id() : "" %>"
						readonly> <br> 이름: <input type="text" name="name"
						value="<%= (currentMember != null) ? currentMember.getMember_name() : "" %>">
					<br> 전화번호: <input type="text" name="phone"
						value="<%= (currentMember != null) ? currentMember.getMember_phone() : "" %>">
					<br> 이메일: <input type="text" name="email"
						value="<%= (currentMember != null) ? currentMember.getMember_email() : "" %>">
					<br> 기존 비밀번호: <input type="password" name="current_pw"
						placeholder="기존 비밀번호 입력"> <br>
					<!-- 추가된 부분 -->
					새로운 비밀번호: <input type="password" name="pw"
						placeholder="새로운 비밀번호 입력">
				</div>
				<br>
				<br>
				
				<input type="submit" value="수정완료"
					style="text-align: center; display: block; margin: 0 auto;">
			</form>
		</fieldset>

		<script type="text/javascript">
        $("input[name='selectedField']").change(function() {
            var selectedField = $(this).val();
            $("div[id$='Field']").hide();
            $("#" + selectedField + "Field").show();
        });
    </script>
</body>
</html>
