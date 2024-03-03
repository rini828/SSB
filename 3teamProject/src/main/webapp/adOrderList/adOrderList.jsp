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
          
          <h1> 주문 관리</h1> 
  
				
				
				                  <%-- 검색창 --%>
          <form action="./AdOrderList.od" method="get" class="search-bar">
				<select name="orders_state"  class="form-select" aria-label="Default select example" style="width :50%; float:left; ">
					<option value="PURCHASE" ${param.orders_state=='PURCHASE'?'selected="selected"' : ''}>결제상품</option>
					<option value="DETERMINE" ${param.orders_state=='DETERMINE'?'selected="selected"' : ''}>구매확정 상품</option>
					<option value="DELIVERY" ${param.orders_state=='DELIVERY'?'selected="selected"' : ''}>배송중인 상품</option>
					<option value="BEDELIVERED" ${param.orders_state=='BEDELIVERED'?'selected="selected"' : ''}>배송완료 상품</option>
					<option value="REFUND" ${param.orders_state=='REFUND'?'selected="selected"' : ''}>환불된 상품</option>
				</select>
                <input type="submit" value="검색" id="searchButton" style="float:left"></input>

          </form>
            <table class="sort">
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
                        <th>주문번호<br>번호</th>
                        <th>회원번호</th>
                        <th>회원이름</th>
                        <th>주문상태</th>
                        <th>주문종류</th>
                        <th>주문날짜</th>
                        <th>가격</th>
                        <th>상세보기</th>
                    </tr>
                </thead>
                
                <%-- 회원 리스트 --%>
                <c:forEach var="dto" items="${orderList }">
                    <tr style="background-color: white;">
                        <td>
                        	<label class="checkbox-inline">
                            <input type="checkbox" name="chk" value="${dto.member_id}">
                        	</label>
                        </td>
                        <td>${dto.id}</td>
                        <td>${dto.member_id}</td>
                        <td>${dto.member_user_name }</td>
                        <td>${dto.orders_state}</td>
                        <td>${dto.orders_sort }</td>
                        <td>${dto.orders_date }</td>
                        <td><fmt:formatNumber value="${dto.total_price }"/> </td>
                        <td>
                        <button id="editButton" onclick="location.href='./AdOrderDetail.od?orders_id=${dto.id}'" style="margin-right: 45px; border: none; background: transparent;">
                        상세보기</button>
                        </td>
					</tr>
                </c:forEach>
            </table>
            
        <!--- 페이징 --->
            <div class="paging">
                <c:if test="${startPage > pageBlock }">
                    <a href="./AdOrderList.od?pageNum=${startPage-pageBlock }&state=${param.state}">이전</a>
                </c:if>
                
                <c:forEach var="i" begin="${startPage }" end="${endPage }" step="1"> 
                <span class="num">
					<a href="./AdOrderList.od?pageNum=${i }&state=${param.state}" class="on" >${i }</a> 
                </span>
				</c:forEach>
                
                <c:if test="${endPage < pageCount }">
                    <a href="./AdOrderList.od?pageNum=${startPage + pageBlock}&state=${param.state}">다음</a>
                </c:if>
            </div>
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
