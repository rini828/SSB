$(function() {
	opener.location.reload();
});
function listPopup() {
	window.open("./locationPopup.lo", "배송지", "width = 600, height = 1200, top = 100, left = 200, location = no");
}
function insertPopup(location_id) {
	window.open("./locationInsert.lo?&location_id=" + location_id, "신규배송지", "width = 500, height = 600, top = 100, left = 200, location = no");
}
function updatePopup(location_id) {
	window.open("./locationInsert.lo?location_id=" + location_id, "수정배송지", "width = 500, height = 600, top = 100, left = 200, location = no");
}
function deletePopup(location_id) {
	if (confirm("삭제하시겠습니까?")) {//삭제 시 재확인
		//페이지 이동
		$.ajax({
			type: "POST",
			url: "./deleteLocation.lo",
			dataType: "text",
			data: {
				"location_id": location_id
			},
			error: function() {
				alert('통신실패!!');
			},
			success: function() {
				opener.location.reload();
				location.reload();
			}
		});
	}else{
		return false;
	}
}
function selectPopup(location_id){//배송지 선택시 부모창으로 값 넘기기
	var check = 0;
		
	$(opener.document).find('#location_id option').each(function(){
		
		if(this.value == location_id) {
			$(opener.document).find('#location_id').val(location_id).prop("selected", true);
			check = 1;
		}
	});
	
	if(!check){
		alert("배송지 선택 오류");
	}
    window.close();
}
/*
selectPopup 사용양식
jsp의 select에게 id = location_id 추가
option의 value = $ {dto.location_id} 추가
 */

