$(function() {//배송요청사항 직접입력 체크
	$('#sample6_detailAddress').change(function() {
		var result = $('#sample6_detailAddress option:selected').attr('label') == '직접 입력';
		if (result) {//직접입력 체크
			$('textarea').removeAttr('hidden');
		} else {
			$('textarea').attr('hidden', 'hidden');
		}
	});
});

function directInputCheck(){//직접 입력 체크
	if ($('#sample6_detailAddress option:selected').attr('label') == '직접 입력') {
		$('#sample6_detailAddress option:selected').attr('value',$('#directInput').val());
	}
}

function phoneCheck(){
	var phoneArr = new Array();//전화번호 유효성 체크
	var phone0 = $('input[name="location_phone[]"]')[0].value;
	var phone1 = $('input[name="location_phone[]"]')[1].value;
	var phone2 = $('input[name="location_phone[]"]')[2].value;

	if (phone0.length < 3 || phone1.length < 4 || phone2.length < 4) {
		alert('유효하지 않은 전화번호');
		return false;
	} else {
		for (var i = 0; i < 3; i++) {
			phoneArr.push($('input[name="location_phone[]"]')[i].value);
		}
		$('#location_phone').attr('value',phoneArr.join(''));
		return true;
	}
}

function addressCheck(){//우편번호,주소 체크
	if ($('#sample6_postcode').val() == "" || $('#sample6_address').val() == "") {
		alert('우편번호를 확인해주세요');
		return false;
	}else{
		return true;
	}
}

function validityCheck() {//유효성 체크
	if(phoneCheck()&&addressCheck()){
		directInputCheck();
		var location_name = $('#location_name').val();
		var location_phone = $('#location_phone').val();
		var location_postcode = $('#sample6_postcode').val();
		var location_add = $('#sample6_address').val();
		var locationD_add = $('#sample6_extraAddress').val();
		var location_title = $('#location_title').val();
		var location_requested = $('#sample6_detailAddress').val();
		var location_id = $('#location_id').val();
		$.ajax({
			type: "POST",
			url: "./locationInsertAction.lo",
			dataType: "text",
			data: {
				"location_name": location_name,
				"location_phone": location_phone,
				"location_postcode": location_postcode,
				"location_add": location_add,
				"locationD_add": locationD_add,
				"location_title": location_title,
				"location_requested": location_requested,
				"location_id": location_id
			},
			error: function() {
				alert('통신실패!!');
			},
			success: function() {
				opener.location.reload();
				window.close();
			}
		});
	}
}
function sample6_execDaumPostcode() {//카카오 주소API
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수
	
			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}
	
			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== ''
					&& /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== ''
					&& data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', '
						+ data.buildingName
						: data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = '(' + extraAddr + ')';
				}
				// 조합된 참고항목을 해당 필드에 넣는다.
				document.getElementById("sample6_extraAddress").value = extraAddr;
	
			} else {
				document.getElementById("sample6_extraAddress").value = '';
			}
	
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById("sample6_postcode").value = data.zonecode;
			document.getElementById("sample6_address").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("sample6_extraAddress")
				.focus();
		}
	}).open();
}