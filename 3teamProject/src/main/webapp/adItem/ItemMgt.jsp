<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>SSB 상품관리</title>

<!-----------------------------------  현정씨 ▼ ---------------------------------------------->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
<meta name="author" content="AdminKit">
<meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="shortcut icon" href="img/icons/icon-48x48.png" />
<link rel="canonical" href="https://demo-basic.adminkit.io/" />
<!----------------- Bootstrap CSS ----------------->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/app.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap">
<!-----------------------------------  현정씨 ▲ ---------------------------------------------->


<!--------------------- jQuery -------------------->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<!------------------- common CSS ------------------>
<link rel="stylesheet" href="${pageContext.request.contextPath}/adItem/css/itemMgt.css">
<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
<!-- 사이드바 공통 토글 -->
<script src="${pageContext.request.contextPath}/admin/toggle.js"></script>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Archivo&display=swap');
  @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
</style>

</head>
<body>

<jsp:include page="../board/inc/top.jsp" />

	<div class="wrapper">
		<!-- 사이드바 -->
		<jsp:include page="../board/inc/sidebar.jsp" />

		<div class="main">



			<main id="item" style="width: 1500px; margin: 0 auto;">
				
				<section class="list">
				
				
					<!----------- 검색창 ----------->
					<span> <a href="./ItemMgt.it"> <img class="Mgt" src="./main/item_img/itemMgt.png" width="60" height="60"> </a>
						<h1>상품 관리</h1> 
							
							<form id="searchForm">
							    <input type="hidden" name="item_style" value="${empty param.item_style ? 'sale' : param.item_style }" />
								<input type="text" name="search" id="searchInput" placeholder="   상품명을 입력해 주세요">
								<button type="submit" class="searchButton">검색</button>
							</form>
					</span> 
					
					<!------- 판매/렌탈 구분 ------->
					<form action="./ItemMgt.it?item_style=${itemStyle }" method="get" class="search-bar" style="display: inline-block;">
						<select id="sel" name="item_style" class="form-select" aria-label="Default select example" style="width: 100px; float: left;" >
							<option value="sale" ${param.item_style == 'sale' || itemStyle == null ? 'selected="selected"' : ''}>판매</option>
							<option value="rental" ${param.item_style=='rental' ?'selected="selected"' : '' }>렌탈</option>
						</select>
							<input type="submit" value="검색" class="searchButton "></input>
				
					</form>
					<!------- 등록/수정 버튼 ------->
					<span id="btn-line" style="display: inline-block;">
						<button id="addButton" onclick="location.href='./itemAddForm.it';">상품 등록</button>
					</span>
				
					<!-------- 리스트 시작 --------->
					<table class="sort">
						<%-- 체크박스 / ID / 썸네일 / 상품명 / 가격 / 스포츠-소분류(대분류) / 브랜드 / 옵션명 옵션값 / 재고 --%>
						<colgroup>
							<col style="width: 3%" />
							<col style="width: 5%" />
							<col style="width: 7%" />
							<col style="width: 15%" />
							<col style="width: 7%" />
							<col style="width: 15%" />
							<col style="width: 7%" />
							<col style="width: 10%" />
							<col style="width: 5%" />
						</colgroup>
						
					<!------- 판매제품 일때 -------->
						<c:if test="${itemStyle==null || itemStyle=='sale' }">
						<button class="editButton" onclick="edit()">옵션 수정</button>
						<thead>
							<tr>
								<th><label class="checkbox-inline"> <input type="checkbox" id="cbx_chkAll_options"> </label></th>
								<th>ID</th>
								<th>이미지</th>
								<th>상품명</th>
								<th>판매가</th>
								<th>카테고리</th>
								<th>브랜드</th>
								<th>옵션</th>
								<th>재고</th>
							</tr>
						</thead>
            			<c:choose>
                		<c:when test="${not empty ItemMgt}">						
							<c:forEach var="dto" items="${ItemMgt }">
							<tr style="background-color: white;">
								<td><label class="checkbox-inline"> <input type="checkbox" name="options_id" value="${dto.options_id }">
								</label></td>

								<td  style="text-align: left; padding-left: 17px;">${dto.item_id}-${dto.options_id}</td>
								<td>
								<img src="${pageContext.request.contextPath}/upload/${dto.item_img_main }"
													style="width: 50px; height: 50px;" alt="제품이미지">
								</td>
								<td>${dto.item_name }</td>
								<td><fmt:formatNumber type="number" maxFractionDigits="3"
										value="${dto.item_price}" />원</td>
								<td>${dto.category_sport }- ${dto.category_sub }
									(${dto.category_major })</td>
								<td>${dto.category_brand }</td>
								<td>${dto.options_name } ${dto.options_value }</td>
								<td>${dto.options_quantity }</td>
							</tr>
							</c:forEach>
                		</c:when>
                		<c:otherwise>
                    		<tr>
                        		<td colspan="9">데이터가 없습니다</td>
                    		</tr>
                		</c:otherwise>
            			</c:choose>					
					</c:if>	
					
					
					<!------- 렌탈제품 일때 -------->
					<c:if test="${itemStyle=='rental' }">
					
						<thead>
							<tr>
								<th><label class="checkbox-inline"> <input type="checkbox" id="cbx_chkAll_rental"> </label></th>
								<th>ID</th>
								<th>이미지</th>
								<th>상품명</th>
								<th>판매가</th>
								<th>카테고리</th>
								<th>브랜드</th>
								<th>옵션</th>
								<th>재고</th>
							</tr>
						</thead>
            			<c:choose>
                		<c:when test="${not empty rItemMgt}">						
							<c:forEach var="rdto" items="${rItemMgt }">
							<tr style="background-color: white;">
								<td><label class="checkbox-inline"> <input
										type="checkbox" name="rental_item_id" value="${rdto.rental_item_id }">
								</label></td>

								<td><c:out value="${rdto.rental_item_id }"></c:out></td>
								<td><a href="#" class="thumb"> 
								<img src="${pageContext.request.contextPath}/upload/${rdto.rental_img_main }"
													style="width: 50px; height: 50px;" alt="제품이미지">
								</a></td>
								<td>${rdto.rental_item_name }</td>
								<td><fmt:formatNumber type="number" maxFractionDigits="3"
										value="${rdto.rental_item_price}" />원</td>
								<td>${rdto.category_sport }- ${rdto.category_sub }
									(${rdto.category_major })</td>
								<td>${rdto.category_brand }</td>
								<td>${rdto.rental_opt_name } ${rdto.rental_opt_value }</td>
								<td>${rdto.rental_opt_quantity }</td>
							</tr>
							</c:forEach>
                		</c:when>
                		<c:otherwise>
                    		<tr>
                        		<td colspan="9">데이터가 없습니다</td>
                    		</tr>
                		</c:otherwise>
            			</c:choose>					
					</c:if>	
					
					</table>
			

					<!------- 판매 페이징 -------->
					<c:if test="${itemStyle==null || itemStyle=='sale' }">
					<div class="paging">

						<c:if test="${startPage > pageBlock }">
							<a
								href="./ItemMgt.it?pageNum=${startPage-pageBlock }&search=${param.search}&item_style=${empty param.item_style ? 'sale' : param.item_style}"><
								이전</a>
						</c:if>

						<span class="num"> <c:forEach var="i" begin="${startPage }"
								end="${endPage }" step="1">
								<a href="./ItemMgt.it?pageNum=${i }&search=${param.search}&item_style=${empty param.item_style ? 'sale' : param.item_style}"
									class="on">${i }</a>
							</c:forEach>
						</span>

						<c:if test="${endPage < pageCount }">
							<a
								href="./ItemMgt.it?pageNum=${startPage+pageBlock }&item_style=${empty param.item_style ? 'sale' : param.item_style}&search=${param.search}">다음
								></a>
								
						</c:if>
					</div>
					</c:if>
			

					<!------- 렌탈 페이징 -------->
					<c:if test="${itemStyle=='rental' }">
					<div class="paging">

						<c:if test="${startPage > pageBlock }">
							<a
								href="./ItemMgt.it?pageNum=${startPage-pageBlock }&search=${param.search}&item_style=${param.item_style }"><
								이전</a>
						</c:if>

						<span class="num"> <c:forEach var="i" begin="${startPage }"
								end="${endPage }" step="1">
								<a href="./ItemMgt.it?pageNum=${i }&search=${param.search}&item_style=${param.item_style }"
									class="on">${i }</a>
							</c:forEach>
						</span>

						<c:if test="${endPage < pageCount }">
							<a
								href="./ItemMgt.it?pageNum=${startPage+pageBlock }&search=${param.search}&item_style=${param.item_style }">다음
								></a>
								
						</c:if>
					</div>
					</c:if>
					
				</section>
			</main>


			<footer class="footer">
			</footer>


			<script src="./adItem/js/item.js"></script>
			<script src="js/app.js"></script>
</body>
</html>