<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ssb.adItem.db.ItemDAO"%>
<%@ page import="com.ssb.adItem.db.ItemDTO"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SSB 재고 입력</title>

<!----- Bootstrap CSS ---->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-------  jQuery  ------->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<!------ common CSS  ----->
<link rel="stylesheet" href="${pageContext.request.contextPath}/adItem/css/itemMgt.css">

<style>
  *{
  font-family: 'Archivo', 'Noto Sans KR', sans-serif;
  }
  body {
  background-color: #f0f0f0;
  justify-content: center;
  align-items: center;
}
  @import url('https://fonts.googleapis.com/css2?family=Archivo&display=swap');
  @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
#saleSubmitButton {
  float: right;
  background: rgb(0, 0, 0);
  background: linear-gradient(0deg, rgba(0, 0, 0, 1) 0%, rgba(25, 25, 25, 1) 100%);
  border: none;
  color: white;
  border-radius: 3px;
  width: 100px;
  height: 30px;
  padding: 2px 10px; 
  font-size: 16px;
  cursor: pointer;
  margin-bottom:100px;
}
#saleSubmitButton:hover {
  background: rgb(17, 17, 17);
  background: linear-gradient(0deg, rgba(17, 17, 17, 1) 0%, rgba(50, 50, 50, 1) 100%);
}
h6 {
margin-bottom: 40px;
}
</style>
<!-- 파비콘 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/upload/favicon.ico">
</head>
<body>

	<div class="validation-form" novalidate style="width:100%; margin: 0 auto; ">

		<div class="container">
				<div class="add-form col-md-12 mx-auto">


						<h6 class="addTitle">옵션 수정</h6>
							
						<!----------------------------------- 상품 (판매용) 수정 폼 ------------------------------------>
						
						<form id="saleForm" action="./ItemEditProAction.it" method="post" class="validation-form" novalidate >
						<input type="hidden" name="category_code" value="1">
						<input type="hidden" name="optionsId" value="${dto.options_id}">

						
						<div class="row">
							<div class="Add-G"> 
							<label for="name">옵션명</label> 
								<input type="text"class="form-control" name="name" placeholder="size, color, Etc" value="${dto.options_name}" required>
									<div class="invalid-feedback">옵션명을 입력해주세요.</div>
							</div>

							<div class="Add-H">
								<label for="name">옵션값</label> 
								<input type="text" class="form-control" name="value" placeholder="" value="${dto.options_value}" required>
									<div class="invalid-feedback">옵션값을 입력해주세요.</div>
							</div>
						</div>

						<div class="row">
							<div class="Add-E">
							<label for="item">잔여 재고</label> 
								<input type="text" class="form-control" name="quantity" placeholder="" value="${dto.options_quantity}" required>
									<div class="invalid-feedback">재고 수량을 입력해주세요.</div>
							</div>

						 	<div class="Add-F">
							<label for="item">옵션추가금</label> 
								<input type="text" class="form-control" name="price" placeholder="선택사항" value="${dto.options_price}">
							</div>
						
						
						</div>
						<hr class="mc">
						<input type="submit" id="saleSubmitButton" value="수정">
						</form>
							

						
						</div>


						<!---------------------------------------------------------------------------------------------->

						</div>
		

	<footer class="my-3 text-center text-small">
		<p class="mb-1">&copy; 2023 SSB</p>
	</footer>

	</div>

	<script src="${pageContext.request.contextPath}/adItem/js/item.js"></script>

</body>
</html>
