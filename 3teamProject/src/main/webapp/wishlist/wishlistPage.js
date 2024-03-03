$(function() {
	//전체선택
	$("#checkAll").click(function() {//체크박스 전체선택/해제
		if ($("#checkAll").is(":checked")) $("input[name=wishlist_id]").prop("checked", true);
		else $("input[name=wishlist_id]").prop("checked", false);
	});
	$("input[name=wishlist_id]").click(function() {//체크박스 전체선택 감지
		var total = $("input[name=wishlist_id]").length;
		var checked = $("input[name=wishlist_id]:checked").length;
		if (total != checked) $("#checkAll").prop("checked", false);
		else $("#checkAll").prop("checked", true);
	});
});
function deleteWishlist() {//장바구니 삭제
	arrayData();//선택된 체크박스값 추출
	var checkArray = $("#checkArray").val();
	//옵션 null값 체크
	if (checkArray == "") {
		alert("위시리스트를 선택해 주십시오");
		return;
	}
	//AJAX
	$.ajax({
		type: "POST",
		url: "./deleteWishlist.wl",
		dataType: "text",
		data: {
			"item_idArr": checkArray
		},
		error: function() {
			alert('통신실패!!');
		},
		success: function(data) {
			var result = JSON.parse(data);
			//옵션 삭제 결과
			if (result = 1) {
				alert("삭제완료");
			} else if (result = 0) {
				alert("삭제실패");
			} else {
				alert("알수없는 오류");
			}
			//페이지 새로고침
			location.reload();
		}
	});
}
function arrayData() {//배열 넘기기
	var checkArray = new Array();
	$("input:checkbox[name=wishlist_id]:checked").each(function() {
		checkArray.push(this.value);
	});
	$("#checkArray").val(checkArray);
}
