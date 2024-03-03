<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>



<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>SSB rentalItem</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link href="./rental/rental_css/rentalItem.css" rel="stylesheet">

<link href="./rental/rental_page/calendar.css" rel="stylesheet"
	type="text/css">
<script src="./rental/rental_page/calendar.js" charset="UTF-8"></script>
<script src="./rental/rental_page/rentalItem.js"></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
/*Q&A 작성하기 버튼 클릭 시 팝업창 띄우기*/

function openInquiryPop(){
		var popup = window.open('./InquiryQWrite.iq?rItemId=${rdto.rental_item_id }', '문의팝업', 'width=550px,height=450px,scrollbars=yes');
	}

</script>

<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">

<!-- 달력 datepicker -->
<script>
   $(function() {
       //input을 datepicker로 선언
       $("#datepicker").datepicker({
           dateFormat: 'yy-mm-dd' //달력 날짜 형태
           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
           ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
           ,changeYear: true //option값 년 선택 가능
           ,changeMonth: true //option값  월 선택 가능                
           ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
           ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
           ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
           ,buttonText: "선택" //버튼 호버 텍스트              
           ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
           ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
           ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
           ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
           ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
           ,minDate: 2// today부터 선택 가능
           
           ,beforeShowDay: function(date){       
        	   /* 일요일만 선택 불가 */      
        	   return [(date.getDay() != 0)]; }

       });  
       
       
       //초기값을 오늘 날짜로 설정
       $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
   		
       
   
   });
   
   
   
   
</script>

</head>
<body>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->
	<div class="header">
		<jsp:include page="../Rcommon/top.jsp" />
	</div>
	<!-- 헤더/ 상단메뉴바 영역 top.jsp 공통 페이지 사용 -->



	<!-- 전달정보 저장 -->


	<section class="subscript">
		<div class="product-container">
			<div class="product-image">
				<img src="${pageContext.request.contextPath}/upload/${rdto.rental_img_main }" alt="제품 이미지">
			</div>
			<div class="product-details">
				<h1>${rdto.rental_item_name }</h1>
				<hr>
				<h2>대여기간 : ${rdto.rental_opt_value }</h2>
				<p>
					금액 :
					<fmt:formatNumber value="${rdto.rental_item_price }" />
					원
				</p>
				
				<div>
					적립금: <fmt:formatNumber type="number" maxFractionDigits="0" value="${rdto.rental_item_price*0.01 }"/> point
				</div>
				
				<p>
					<c:forEach begin="1" end="4" step="1">
						<img width="20" height="20" src="${pageContext.request.contextPath}/upload/star.png" />
					</c:forEach>
					<img width="20" height="20" src="${pageContext.request.contextPath}/upload/harfStar.png" /> <a
						href="#review"> 4/5 리뷰</a>
				</p>

				<!-- 예약 달력!!!  -->



				<form action="./RentalReserve.re?rental_item_name=${rdto.rental_item_id }">
				<input type="hidden" name="itemId" value="${rdto.rental_item_id }">
					<input type="text" id="datepicker" name="selectedDate"> <br>
			
					<input type="submit" value="예약하기" class="btn">
				</form>
				<%-- </form>
				<form
					action="./RentalReserve.re?rental_item_name=${rdto.rental_item_id }">
					<div id="calendar-container">
						<input type="hidden" name="itemId" value="${rdto.rental_item_id }">
						<input class="date" type="text" name="selectedDate"
							id="rental-date-input" readonly>
						<div id="calendar-popup"></div>
					</div>
					<br>
					<button type="submit" class="btn">${rdto.rental_item_name }
						예약하기</button>
				</form> --%>


			</div>
		</div>
		<div id="midBox">
			<div class="detailinfo showstep1">
				<div class="content">
					<img src="${pageContext.request.contextPath}/upload/${rdto.rental_img_sub }"
						alt="상품 상세 이미지">
				</div>
			</div>
			<div class="buttons">
				<button class="btn_open">더보기</button>
				<button class="btn_close hide">상세 이미지 접기</button>
			</div>
		</div>

		<div class="reviews" id="review">
			<h1>REVIEW</h1>
			<div class="container">
				<table class="table table-sm">
					<colgroup>
						<col style="width: 10%;" />
						<col style="width: 60%;" />
						<col style="width: 10%;" />
						<col style="width: 10%;" />
					</colgroup>
					<thead>
						<tr>
							<th>평점</th>
							<th>내용</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rbdto" items="${reviewList }">
							<tr>
								<td><c:if test="${rbdto.rating == 5 }">
		        ★★★★★
		       </c:if> <c:if test="${rbdto.rating == 4 }">
		        ★★★★
		       </c:if> <c:if test="${rbdto.rating == 3 }">
		        ★★★
		       </c:if> <c:if test="${rbdto.rating == 2 }">
		        ★★
		       </c:if> <c:if test="${rbdto.rating == 1 }">
		        ★
		       </c:if></td>
								<td>
									<div class="panel-faq-container">
										<p class="panel-faq-title">${rbdto.board_content }</p>
									</div>
								</td>
								<td>${rbdto.member_name }</td>
								<td>${rbdto.board_writeTime }</td>
							</tr>
							<tr>
								<td colspan="5" width="100%"
									style="border-bottom: inherit; padding: 0;">
									<div class="panel-faq-answer">
										<p class="p-content">${rbdto.board_content }</p>
										<c:if test="${!empty rbdto.board_file}">
											<p class="p-content">
												<img
													src="<%=request.getContextPath() %>/upload/${rbdto.board_file }"
													style="width: 100px; height: 100px;">
											</p>
										</c:if>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<nav aria-label="Page navigation example">
				<ul class="pagination pagination-sm justify-content-center">
					<c:if test="${reviewStartPage > reviewPageBlock }">
						<li class="page-item"><a class="page-link" href=""
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<c:forEach var="i" begin="${reviewStartPage }"
						end="${reviewEndPage }" step="1">
						<li class="page-item"><a class="page-link" href="">${i }</a>
						</li>
					</c:forEach>
					<c:if test="${reviewEndPage < reviewPageCount }">
						<li class="page-item"><a class="page-link" href=""
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>
			</nav>
		</div>

		<div class="questions">
			<h1>Q&A</h1>
			<div class="container">
				<table class="table table-sm">
					<colgroup>
						<col style="width: 10%;" />
						<col style="width: 15%;" />
						<col style="width: 50%;" />
						<col style="width: 15%;" />
						<col style="width: 10%;" />
					</colgroup>
					<thead>
						<tr>
							<th>답변상태</th>
							<th>구분</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody class="heading">
						<c:forEach var="bdto" items="${inquiryList }">
							<tr>
								<td><c:if test="${bdto.answer_state == '답변예정'}">
										<strong>답변예정</strong>
									</c:if> <c:if test="${bdto.answer_state == '답변완료'}">
										<font style="color: red;"><strong>${bdto.answer_state }</strong></font>
									</c:if></td>
								<td><c:out value="${bdto.inquiry_type }" /></td>
								<td>
									<div class="panel-faq-container">
										<p class="panel-faq-title">${bdto.board_subject }</p>
									</div>
								</td>
								<td>${bdto.member_name }</td>
								<td>${bdto.board_writeTime }</td>
							</tr>
							<tr>
								<td colspan="5" width="100%"
									style="border-bottom: inherit; padding: 0;">
									<div class="panel-faq-answer">
										<p class="p-content">${bdto.board_content }</p>
										<c:if test="${bdto.board_id == rpdto.board_id }">
											<p class="p-content">
												<strong>[답변]</strong>
											</p>
											<p class="p-content">${rpdto.reply_content }</p>
										</c:if>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<nav aria-label="Page navigation example">
				<ul class="pagination pagination-sm justify-content-center">
					<c:if test="${startPage > pageBlock }">
						<li class="page-item"><a class="page-link" href=""
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
						<li class="page-item"><a class="page-link" href="">${i }</a>
						</li>
					</c:forEach>
					<c:if test="${endPage < pageCount }">
						<li class="page-item"><a class="page-link" href=""
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>
			</nav>
			<!-- 로그인한 회원만 문의 가능 -->
			<div style="text-align: right; margin-right: 180px;">
				<c:if test="${!empty userId }">
					<a class="btn btn-sm btn-dark rbtn" role="button"
						onclick="openInquiryPop()">작성하기</a>
				</c:if>
			</div>
		</div>
	</section>


	<nav class="top">
		<a href="#">↑TOP</a>
	</nav>


	<!-- footer 시작 -->
	<footer class="footer">
		<p>&copy; 2023 SSB Style</p>
	</footer>
	<!-- footer 끝 -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>




</body>
</html>