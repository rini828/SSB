<%@page import="com.ssb.myPage.db.myPageDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./myPage/myPage.css" rel="stylesheet">
<link href="./main/main.css" rel="stylesheet">
<!-- <link rel="stylesheet" type="text/css"
	href="path/to/order-list-styles.css"> -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
        // 기존 정보 표시 함수
        function populateExistingInfo() {
            var currentMember = <%= (myPageDTO)request.getAttribute("currentMember") %>;

            if (currentMember) {
                $("#member_user_id").val(currentMember.getMember_user_id());
                $("#member_name").val(currentMember.getMember_name());
                $("#member_birth").val(currentMember.getMember_birth());
                $("#member_gender").val(currentMember.getMember_gender());
                $("#member_email").val(currentMember.getMember_email());
                $("#member_phone").val(currentMember.getMember_phone());
                $("#member_regdate").val(currentMember.getMember_regdate());
                $("#member_payment").val(currentMember.getMember_payment());
                $("#member_point").val(currentMember.getMember_point());
                $("#member_grade").val(currentMember.getMember_grade());
            }
        }

        // 페이지 로딩 시 기존 정보 표시
        $(document).ready(function() {
            populateExistingInfo();
        });
        
        // 메뉴 토글 함수
        function toggleMenu() {
            $(".category-buttons").slideToggle();
        }
        
    </script>

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
<style>
	
#item > .list > .paging a.on {
    font-weight: bold;
}


#item > .list > .paging {
    border-top: 2px solid black;
    width: 100%;
    padding: 20px 0;
    text-align: center;
}


#item > .list > .paging a {
    color: #333;
    font-size: 13px;
    text-decoration: none;
    margin: 0 5px;
}

#item > .list > .paging > .num > a {
    display: inline-block;
    min-width: 14px;
    margin: 0 3px;
    padding: 5px 5px;
    border: 1px solid #c4c4c4;
    color: #000;
    font-size: 12px;
    text-align: center;
    text-decoration: none;
}
	
</style>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="./Mcommon/top.js" charset="UTF-8"></script>
<link href="./Mcommon/top.css" rel="stylesheet">
</head>
<body>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->
	<div class="header">
		<jsp:include page="../Mcommon/top.jsp" />
    </div>
		<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->

		<!-- 상단 메인 메뉴바 끝 -->
		<div class="content-container">
			<h1 style="text-align: left; font-weight: bold;">My Page</h1>


			<!-- 내용 수정된 부분 -->
			<div class="right-section">
				<button class="menu-toggle" onclick="toggleCategory()"><img src="./main/img/menu_bar.png" alt="메뉴바" width="40" height="25" /></button>
				<div class="category-content m-2" id="categoryContent"
					style="display: none;">

					<div class="accordion accordion-flush" id="accordionFlushExample">
						<div class="accordion-item">
							<h2 class="accordion-header">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
									aria-expanded="false" aria-controls="flush-collapseOne">
									<a href="update.ud"style="color: black; text-decoration: none;">
									정보수정
									</a>
								</button>
							</h2>
						</div>
						<div class="accordion-item">
							<h2 class="accordion-header">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse" data-bs-target="#flush-collapseThree"
									aria-expanded="false" aria-controls="flush-collapseThree">
									<a href="./location.lo" style="color: black; text-decoration: none;">
									배송지
									</a>
								</button>
							</h2>
						</div>
					</div>
				</div>
			</div>
	 <!-- div 자리 이동해봄  -->
			<script>
    function toggleCategory() {
        var categoryContent = document.getElementById("categoryContent");
        categoryContent.style.display = (categoryContent.style.display === 'none' || categoryContent.style.display === '') ? 'block' : 'none';
    }
</script>




			<% myPageDTO currentMember = (myPageDTO) request.getAttribute("currentMember"); %>

			<% if (currentMember != null) { %>
			<div style="display: flex;">
				<div
					style="width: 300px; margin-right: 10px; text-align: right; font-style: bold;">
					<p style="font-size: 150px;"><%= currentMember.getMember_grade() %></p>
					<!-- 등급부분 -->
				</div>
				<div style="flex-grow: 1; text-align: left;">
					<table style="margin: auto; text-align: left; width: 80%;">
						<tr>
							<td style="font-size: 20px;">ID:</td>
							<td style="font-size: 20px;"><%= currentMember.getMember_user_id() %></td>
						</tr>
						<tr>
							<td style="font-size: 20px;">이메일:</td>
							<td style="font-size: 20px;"><%= currentMember.getMember_email() %></td>
						</tr>
						<tr>
							<td style="font-size: 20px;">전화번호:</td>
							<td style="font-size: 20px;"><%= currentMember.getMember_phone() %></td>
						</tr>
						<tr>
							<td style="font-size: 20px;">가입일시:</td>
							<td style="font-size: 20px;"><%= currentMember.getMember_regdate() %></td>
						</tr>
						<tr>
							<td style="font-size: 20px;">누적결제금액:</td>
							<td style="font-size: 20px;"><fmt:formatNumber
									value="${currentMember.getMember_payment() }" /> 원</td>
						</tr>
						<tr>
							<td style="font-size: 20px;">포인트:</td>
							<td style="font-size: 20px;"><fmt:formatNumber
									value="${currentMember.getMember_point() }" /> Point</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="search-bar-container"
				style="width: 80%; margin: 20px auto; text-align: center;">
				<form action="./myPage.mp" method="get" class="search-bar"
					style="display: inline-block;">
					<h2 style="display: inline-block; margin-right: 20px;">주문 내역</h2>
					<select name="orders_state" class="form-select"
						aria-label="Default select example"
						style="width: 80%; float: left;">
						<option value="PURCHASE" ${param.orders_state=='PURCHASE'?'selected="selected"' : ''}>결제상품</option>
						<option value="DETERMINE" ${param.orders_state=='DETERMINE'?'selected="selected"' : ''}>구매확정 상품</option>
						<option value="DELIVERY" ${param.orders_state=='DELIVERY'?'selected="selected"' : ''}>배송중인 상품</option>
						<option value="BEDELIVERED" ${param.orders_state=='BEDELIVERED'?'selected="selected"' : ''}>배송완료 상품</option>
						<option value="REFUND" ${param.orders_state=='REFUND'?'selected="selected"' : ''}>환불된 상품</option>
						<option value="CANCEL" ${param.orders_state=='CANCEL'?'selected="selected"' : ''}>취소된 상품</option>
					</select> <input type="submit" value="검색" id="searchButton "
						style="float: left;"></input>
				</form>
			</div>

			<table class="order-table" style="width: 80%; margin: 0 auto;">
				<thead>
					<tr>
						<th>주문 번호</th>
						<th>회원번호</th>
						<th>회원이름</th>
						<th>주문상태</th>
						<th>주문종류</th>
						<th>주문날짜</th>
						<th>가격</th>
						<th>상세보기</th>
						<th>주문 취소</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${orderList}">
						<tr>
							<td>${dto.id}</td>
							<td>${dto.member_id}</td>
							<td>${dto.member_user_name}</td>
							<td>${dto.orders_state}</td>
							<td>${dto.orders_sort}</td>
							<td>${dto.orders_date}</td>
							<td><fmt:formatNumber value="${dto.total_price}"/>원</td>
							<td>
								<button class="edit-button" type="button"
									onclick="window.open('./myPageOrderDetail.mp?orders_id=${dto.id}','detail','width=720;, height=720px;')">상세보기</button>
							</td>
							<td>
							<c:if test="${dto.orders_state=='PURCHASE'}">
							<form action="./myPageRefundAction.mp">
								<input type="hidden" value="${dto.id}" name="orders_id">
								<button class="cancel-button" type="submit">주문취소</button>
							</form>
							</c:if>
							
							<c:if test="${dto.orders_state=='BEDELIVERED'}">
							<form action="./MyPageUserDetermine.od">
								<input type="hidden" value="${dto.id}" name="orders_id">
								<button class="cancel-button" type="submit">구매결정</button>
							</form>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>

			<% } else { %>
			<p>현재 로그인된 계정 정보가 없습니다.</p>
			<% } %>
			
			<div style="text-align: center;">
				<!--- 페이징 --->
				<div class="test">
		            <div class="paging">
		                <c:if test="${startPage > pageBlock }">
		                    <a href="./myPage.mp?pageNum=${startPage-pageBlock }&state=${param.state}">이전</a>
		                </c:if>
		                
		                <c:forEach var="i" begin="${startPage }" end="${endPage }" step="1"> 
		                <span class="num" style="margin: 0 auto;">
							<a href="./myPage.mp?pageNum=${i }&state=${param.state}" class="on" >${i }</a> 
		                </span>
						</c:forEach>
		                
		                <c:if test="${endPage < pageCount }">
		                    <a href="./myPage.mp?pageNum=${startPage + pageBlock}&state=${param.state}">다음</a>
		                </c:if>
		            </div>
				</div>
				<div style="text-align: right;">
					<button class="withdrawal-button">
							<a href="./MemberCloseAccount.me" style="color: black;">회원 탈퇴</a>
					</button>
				</div>
			</div>
		</div>
		
</body>
</html>
