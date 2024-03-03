<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SSB 상품 상세</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/saleItem/itemD.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico" > 
	
</head>
<body>

	<!---- 공통 헤더 ---->
	<%@ include file="../Mcommon/top.jsp"%>
	
	<main style="width:1500px;  margin: 0 auto;" >
    <section>

			<div id="topBox">
			
				<div class="leftBigBox">
					<div class="brandLogo"> 
						<c:if test="${!empty itemDTO.item_img_logo }">
						<img src="${pageContext.request.contextPath}/upload/${itemDTO.item_img_logo}" alt="브랜드로고">
						</c:if>
					</div>
						<img src="${pageContext.request.contextPath}/upload/${itemDTO.item_img_main}" alt="상품이미지">
					<p></p>
				</div>
				
				
				<div class="rightBigBox">
				<br>
					<div class="category">홈 > <b>${itemDTO.category_sport}</b> > <b>${itemDTO.category_sub} (${itemDTO.category_major})</b> </div>
					<span class="Info_M">Product Info</span>
					<span class="Info_S"> No. ${itemDTO.item_id} </span>
					
					<p class="itemName">  ${itemDTO.item_name} </p>
					<p class="Info_S"> ${itemDTO.category_brand} </p>
					

					<div class="detail">
						<ul>
							<li><span>판매가</span> <span class="r"> <b> <fmt:formatNumber value="${itemDTO.item_price }" />원 </b> </span></li>
							<li><span>적립금</span> 
								<span class="r"> <fmt:formatNumber type="number" maxFractionDigits="0" value="${itemDTO.item_price*0.01 }"/> </span></li>
							<li><span>구매후기</span> 
								<span class="star"> 
									<c:forEach begin="1" end="4" step="1">
										<img width="20" height="20" src="${pageContext.request.contextPath}/upload/star.png"/>
									</c:forEach>
										<img width="20" height="20" src="${pageContext.request.contextPath}/upload/harfStar.png"/> 
										<a href="#review">&nbsp;4.5/5 <b>리뷰</b> </a> 
								</span></li>
							<li><span>배송비</span> <span class="r">무료</span></li>
						</ul>
					</div>


					<div id=mini_cart>

						<div id="selecters">
							<input type="hidden" value="${itemDTO.item_id}" id="item_idSelecter"> 
							
							<div class="pick"> 
							<select id="options_idSelecter" class="form-control" name="options_value" required="required">
        							<option value="" disabled selected>-&nbsp;[필수] 옵션을 선택해 주세요</option>
            					<c:forEach var="optList" items="${getOptList}" >
  									<option value="${optList.options_id}" label="${optList.options_name}&nbsp;&nbsp;${optList.options_value}"
  									data-options-name="${optList.options_name}" data-options-value="${optList.options_value}"> ${optList.options_value}</option>
							 	</c:forEach>
   							</select>
							</div> 
					
						<hr class="line">
						</div>
					
					
						<div id="cartPool">  
						</div>
					
					</div>


					<span class="btnSale">		
						<button class="btn-2" type="button" onclick="addCart('cart')"> 
						<img src="${pageContext.request.contextPath}/main/img/market.png" alt=""></button>
					
						<button class="custom-btn btn-1 addCart" type="button" onclick="addCart('buy')">BUY NOW</button>
					</span>

	
				</div> 
			</div>
			
			
			
			
			<div id="midBox">
    			<h2>PRODUCT IMAGE</h2>
    			<p>본 상품은 본사 인증 정품입니다. 브랜드 공식 택과 A/S가 제공됩니다.</p>

    			<div class="detailinfo showstep1">
        			<div class="content">
            		<img src="${pageContext.request.contextPath}/upload/${itemDTO.item_img_sub}" alt="상세이미지">
        			</div>
    			</div>

    			<div class="buttons">
       				<button class="btn_open">더보기</button>
        			<button class="btn_close hide">상세 이미지 접기</button>
    			</div>
			</div>


	

			<!-- REVIEW -->
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

			</div>




			<!-- Q&A -->
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
										nstyle="border-bottom: inherit; padding: 0;">
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


				<!-- 로그인한 회원만 문의 가능 -->
				<div style="text-align: right; margin-right: 100px;">
					<c:if test="${!empty userId }">
						<a class="btn btn-sm btn-dark rbtn" role="button"
							onclick="openInquiryPop()">작성하기</a>
					</c:if>
				</div>
			</div>
	
	</section>
	</main>




	<nav class="top">
		<a href="#">↑TOP</a>
	</nav>
    
   <footer style="background: black; color:white; text-align: center; padding:10px; width:100vw;">
      <p>&copy; 2023 SSB Style</p>
   </footer>

	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/saleItem/itemD.js"></script>
	<script>
	/*Q&A 작성하기 버튼 클릭 시 팝업창 띄우기*/
	function openInquiryPop(){
			var popup = window.open('./InquiryItemQWrite.iq?itemId=${param.item_id }', '문의팝업', 'width=550px,height=450px,scrollbars=yes');
		}
	</script>

</body>
</html>