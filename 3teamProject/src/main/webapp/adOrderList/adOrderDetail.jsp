<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<!--     <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    <title>Member Management</title>


<!-----------------------------------  현정씨 ▼ ---------------------------------------------->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
<meta name="author" content="AdminKit">
<meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="shortcut icon" href="img/icons/icon-48x48.png" />
<link rel="canonical" href="https://demo-basic.adminkit.io/" />

<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">    
<!-----------------------------------  현정씨 ▲ ---------------------------------------------->



<!--------  jQuery  --------->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="crossorigin="anonymous"></script>

<!------- common CSS  ------->
<link rel="stylesheet" href="${pageContext.request.contextPath}/adMemberList/css/adMemberList.css">

<!-- 사이드바 공통 토글 -->
<script src="${pageContext.request.contextPath}/admin/toggle.js"></script>

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>


  <div class="wrapper">
	
  <!-- 사이드바 -->
  <jsp:include page="../board/inc/sidebar.jsp"/>
  <!-- 사이드바 -->
		
  <!-- 메인 시작 -->
  <div class="main">
	<jsp:include page="../board/inc/top.jsp"/>
<!-----------------------------------  현정씨 ▲ ---------------------------------------------->

    <main id="item">
        <section class="list">
          
          <h1> 주문 번호 : ${orders_id}</h1> <h1> 주문 상태 : ${orders_state}</h1>
  
				
				
				                  <%-- 검색창 --%>
          
            <table class="sort">
            <c:if test="${orders.orders_sort=='SALE'}">
                <%-- 
                회원번호 / 아이디 / 비밀번호 / 이름 / 생년월일 / 성별 / 메일주소 / 전화번호 / 가입일시 / 결제금액 / 적립금 / 등급 / 상태 / 탈퇴일시 / 마케팅수신동의
                --%>
                <colgroup>
                    <col style="width: 7%" /> 
                    <col style="width: 10%" />
                    <col style="width: 10%" />
                    <col style="width: 10%" />
                    <col style="width: 10%" />
                    <col style="width: 15%" />
                    <col style="width: 10%" />
					<col style="width: 15%" />
                </colgroup>
                <thead>
                    <tr>
                        <th><label class="checkbox-inline">
                            <input type="checkbox" id="cbx_chkAll">
                        </label></th>
                        <th>상세 주문 번호 </th>
                        <th>상품번호</th>
                        <th>상품명</th>
                        <th>상품옵션</th>
                        <th>상품가격</th>
                        <th>상품수량</th>
                        <th>물품 총 가격</th>
                    </tr>
                </thead>
                
                <%-- 회원 리스트 --%> 
                <!--  판매 ㅎ -->
                
                	<c:forEach var="dto" items="${orderDetailDTO}">
                	
                    <tr style="background-color: white;">
                        <td>
                        	<label class="checkbox-inline">
                            <input type="checkbox" name="chk" value="">
                        	</label>
                        </td>
                        <td>${dto.orderD_id}</td>
                        <td>${dto.item_id}</td>
                        <td>${dto.item_name}</td>
                        <td>${dto.options_id}</td>
                        <td>${dto.price}</td>
                        <td>${dto.quantity}</td>
                        <td>${dto.price*dto.quantity}</td>
					</tr>
					</c:forEach>
			</c:if>
			
			<c:if test="${orders.orders_sort=='RENTAL'}">
				왜 안될까?
                <%-- 
                회원번호 / 아이디 / 비밀번호 / 이름 / 생년월일 / 성별 / 메일주소 / 전화번호 / 가입일시 / 결제금액 / 적립금 / 등급 / 상태 / 탈퇴일시 / 마케팅수신동의
                --%>
                <colgroup>
                    <col style="width: 7%" /> 
                    <col style="width: 10%" />
                    <col style="width: 10%" />
                    <col style="width: 10%" />
                    <col style="width: 10%" />
                    <col style="width: 10%" />
                    <col style="width: 10%" />
                    <col style="width: 10%" />
					<col style="width: 10%" />
					<col style="width: 10%" />
                </colgroup>
                <thead>
                    <tr>
                    	<td>
                        	<label class="checkbox-inline">
                            <input type="checkbox" name="chk" value="">
                        	</label>
                        </td>
                        <th>상세 주문 번호</th>
                        <th>상품번호</th>
                        <th>상품명</th>
                        <th>상품옵션</th>
                        <th>상품가격</th>
                        <th>상품수량</th>
                        <th>물품 총 가격</th>
                        <th>대여 시작일</th>
                        <th>대여 종료일</th>
                    </tr>
                </thead>
                
                <%-- 회원 리스트 --%> 
                <!--  판매 ㅎ -->
                
                	<c:forEach var="dto" items="${orderDetailDTO}">
                	
                    <tr style="background-color: white;">
                        <td>
                        	<label class="checkbox-inline">
                            <input type="checkbox" name="chk" value="">
                        	</label>
                        </td>
                        <td>${dto.orderD_id}</td>
                        <td>${dto.item_id}</td>
                        <td>${dto.item_name}</td>
                        <td>${dto.options_id}</td>
                        <td>${dto.price}</td>
                        <td>${dto.quantity}</td>
                        <td>${dto.price*dto.quantity}</td>
                        <td>${dto.rental_str}</td>
                        <td>${dto.rental_end}</td>
					</tr>
				</c:forEach>
			</c:if>
                
            </table>
            
            
            <div>
            
            <table class="sort">
                <colgroup>
                    <col style="width: 40%" /> 
                    <col style="width: 60%" />

                </colgroup>
                <thead>
                <tr>
                    <th> 받는 사람 </th>
                    <th>${location.location_name}</th>
                </tr>
                </thead>
                <tr style="background-color: white;">
                    <td>수령자 휴대폰 번호</td>
                    <td>${location.location_phone}</td>
				</tr>
				<tr style="background-color: white;">
                    <td>우편번호</td>
                    <td>${location.location_postcode}</td>
				</tr>
				<tr style="background-color: white;">
                    <td>배송지 정보</td>
                    <td>${location.location_add}</td>
				</tr>
				<tr style="background-color: white;">
                    <td>상세주소</td>
                    <td>${location.locationD_add}</td>
				</tr>
                <tr style="background-color: white;">
                    <td>배송 요청 사항</td>
                    <td>${location.location_requested}</td>
				</tr>
            </table>
            </div>
            
            <form action="./OrderStateUpdateRefund.od">
				<input type="hidden" value="${orders.id}" name="orders_id">
				<button id="editButton" style="margin:1%;">환불 처리</button>
			</form>
            
            <form action="./OrderStateUpdateCancel.od">
				<input type="hidden" value="${orders.id}" name="orders_id">
				<button id="editButton" style="margin:1%;">주문 취소시키기</button>
			</form>
            
            <form action="./OrderStateUpdateBeDelivered.od">
				<input type="hidden" value="${orders.id}" name="orders_id">
				<button id="editButton" style="margin:1%;">주문상태 변경 (배송완료)</button>
			</form>
            
			<form action="./OrderStateUpdateDelivery.od">
				<input type="hidden" value="${orders.id}" name="orders_id">
				<button id="editButton" style="margin:1%;">주문상태 변경 (배송중)</button>
			</form>
			
			<button id="editButton" style="margin:1%;" type="button" onclick="location.href='./AdOrderList.od'">목록으로</button>


		
            
        </section>
    </main>
    
    
    <footer class="footer">
   <!--      <p>&copy; 2023 SSB Style</p> -->
    </footer>
    
    </div>
    </div>
    

    <script src="./adMemberList/js/adMemberList.js"></script>
    <script src="js/app.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>
