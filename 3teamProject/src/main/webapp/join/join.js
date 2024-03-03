///////////////////////////////////////////////////////////////
// 콤보 박스 - 도메인 직접 입력 or domain option 선택 
const domainListEl = document.querySelector('#domain-list')
const domainInputEl = document.querySelector('#domain-txt')
// select 옵션 변경 시
domainListEl.addEventListener('change', (event) => {
  // option에 있는 도메인 선택 시
  if(event.target.value !== "type") {
    // 선택한 도메인을 input에 입력하고 disabled
    domainInputEl.value = event.target.value
    domainInputEl.disabled = true
  } else { // 직접 입력 시
    // input 내용 초기화 & 입력 가능하도록 변경
    domainInputEl.value = ""
    domainInputEl.disabled = false
  }
});
/////////////////////////////////////////////////////////////

//////////////////// 공통으로 필요한 것 /////////////////////////
// 필요한 값 초기화
let idCheck = false; // onsubmit 시에 필요
let pwCheck = false; // onsubmit 시에 필요 
let pwCheck2 = false; // onsubmit 시에 필요
let isCheckBoxChecked = false; // onsubmit 시에 필요

// 공백 제어 jquery
$('.inputId, .inputPw, #domain-txt, #floatingEmail, #floatingName, #floatingBirth, #floatingTel').on('input', function () {
    // 현재 입력된 비밀번호 값 가져오기
    let value = $(this).val();

    // 공백을 포함하고 있다면 입력을 무시
    if (value.includes(' ')) {
        // 공백을 제거한 값으로 다시 입력란에 설정
        value = value.replace(/\s/g, '');
        $(this).val(value);
    }
});

// 전화번호, 생년월일 제어(숫자만 입력 되게)
$('#floatingBirth, #floatingTel').on('input', function () {
    // 현재 입력된 값 가져오기
    let value = $(this).val();
    // 숫자 이외의 문자를 제거
    value = value.replace(/[^0-9]/g, '');
    $(this).val(value);
});

// submit 버튼 업데이트 함수
function updateSubmitButton() {
    // 필수 사항이 성공했을 때만 submit 버튼 활성화
    if (idCheck && pwCheck && pwCheck2 && isCheckBoxChecked) {
        $('#submitButton').prop('disabled', false);
    } else {
        $('#submitButton').prop('disabled', true);
    }
}
//////////////////// 공통으로 필요한 것 /////////////////////////

// 아이디 중복 확인 ajax
$('.inputId').keyup(function(){
    checkDuplicationId();
    //console.log(pwCheck);
});
function checkDuplicationId(){
	let userId = $('.inputId').val(); // inputId에 입력되는 값

	$.ajax({
		url: "./MemberJoinIdCheck.me",
		type: "post",
		data: { userId: userId },
		dataType: 'text',
		success: function(result) {
			if (result == '-1') {
				$("#checkId").html('<b>사용할 수 없는 아이디입니다</b>');
				$("#checkId").attr('color', 'red');
				idCheck = false;
				//				$("#floatingId").focus();
			} else if (result == '0') {
				$("#checkId").html('<b>아이디를 입력하세요</b>');
				$("#checkId").attr('color', 'red');
				idCheck = false;
				//				$("#floatingId").focus();
			} else if (result == '1') {
				$("#checkId").html('<b>영문,숫자만 가능(숫자 단일조합X)</b>');
				$("#checkId").attr('color', 'red');
				idCheck = false;
				//				$("#floatingId").focus();
			}  else if (result == '2') {
				$("#checkId").html('사용가능한 아이디입니다');
				$("#checkId").attr('color', 'skyblue');
				idCheck = true;				
			}
			
			updateSubmitButton(); // 아이디 체크 후 submit 버튼 업데이트

		},
		error: function() {
			alert("서버요청실패");
		}
	});
//	console.log(idCheck);
}
// 아이디 중복 확인 ajax

//비밀번호 유효성 검사 ajax
$('.inputPw').keyup(function(){
    checkPasswordHints();
//    console.log(pwCheck);
});
function checkPasswordHints() {
    let userPw = $('.inputPw').val();

    $.ajax({
        url: './MemberJoinPwCheck.me',
        type: 'post',
        data: { userPw: userPw },
        datatype: 'text',
        success: function (result) {
            // 스타일 초기화
            $('.password-hints font').css('color', '');

            // 힌트 표시
            switch (parseInt(result)) {
                case 1:
                    $('.hint1').css('color', 'skyblue'); // 대소문자
                    pwCheck = false;
                    break;
                case 2:
                    $('.hint2').css('color', 'skyblue'); // 숫자
                    pwCheck = false;
                    break;
                case 3:
                    $('.hint3').css('color', 'skyblue'); // 특수문자
                    pwCheck = false;
                    break;
                case 4:
                    $('.hint4').css('color', 'skyblue'); // 8~20자 이내
                    pwCheck = false;
                    break;
                case 5:
                    $('.hint1, .hint2').css('color', 'skyblue'); // 대소문자, 숫자
                    pwCheck = false;
                    break;
                case 6:
                    $('.hint1, .hint3').css('color', 'skyblue'); // 대소문자, 특수문자
                    pwCheck = false;
                    break;
                case 7:
                    $('.hint1, .hint4').css('color', 'skyblue'); // 대소문자, 8~20자
                    pwCheck = false;
                    break;
                case 8:
                    $('.hint2, .hint3').css('color', 'skyblue'); // 숫자, 특수문자
                    pwCheck = false;
                    break;
                case 9:
                    $('.hint2, .hint4').css('color', 'skyblue'); // 숫자, 8~20자
                    pwCheck = false;
                    break;
                case 10:
                    $('.hint3, .hint4').css('color', 'skyblue'); // 특수문자, 8~20자
                    pwCheck = false;
                    break;
                case 11:
                    $('.hint1, .hint2, .hint3').css('color', 'skyblue'); // 대소문자, 숫자, 특수문자
                    pwCheck = false;
                    break;
                case 12:
                    $('.hint1, .hint2, .hint4').css('color', 'skyblue'); // 대소문자, 숫자, 8~20자
                    pwCheck = false;
                    break;
                case 13:
                    $('.hint1, .hint3, .hint4').css('color', 'skyblue'); // 대소문자, 특수문자, 8~20자
                    pwCheck = false;
                    break;
                case 14:
                    $('.hint2, .hint3, .hint4').css('color', 'skyblue'); // 숫자, 특수문자, 8~20자
                    pwCheck = false;
                    break;
                case 15:
                    $('.hint1, .hint2, .hint3, .hint4').css('color', 'skyblue'); // 대소문자, 숫자, 특수문자, 8~20자
                    pwCheck = true;
                    break;
//                default:
//                    break;
            }
            updateSubmitButton(); // 아이디 체크 후 submit 버튼 업데이트
        },
        error: function () {
            alert("서버요청실패");
        }
    }); 
}
// 비밀번호 유효성 검사 ajax 끝

// 비밀번호 재확인 jquery
$('.inputPw2').keyup(function(){
    pwDoubleCheck();
    console.log(pwCheck2);
});
function pwDoubleCheck(){
	
	let userPw1 = $('.inputPw').val(); // inputPw에 입력되는 값
	let userPw2 = $('.inputPw2').val(); // inputPw2에 입력되는 
	
    // 두 비밀번호 필드가 모두 공백인 경우
    if (userPw1.trim() === '' && userPw2.trim() === '') {
        $('#pwDoubleCheck').html(""); // 메시지를 지우고
        pwCheck2 = false;
        updateSubmitButton(); // 제출 버튼을 업데이트
        return;
    }
	
	$.ajax({
		url: "./MemberJoinPwDoubleCheck.me",
		type: "post",
		data: { userPw2: userPw2 },
		dataType: 'text',
		success: function(result) {
			if(userPw1 !== result){
				$('#pwDoubleCheck').html("비밀번호가 일치하지 않습니다");
				$('#pwDoubleCheck').attr('color','red');
				pwCheck2 = false;
			}else{
				$('#pwDoubleCheck').html("일치합니다");
				$('#pwDoubleCheck').attr('color','skyblue');
				pwCheck2 = true;
			}
			updateSubmitButton(); // 아이디 체크 후 submit 버튼 업데이트
		},
		error: function() {
			alert("서버요청실패");
		}
	});
}
// 비밀번호 재확인 ajax 끝

// onsubmit 유효성 검사
function check() {
	
	// 아이디 유효성 체크(ajax)
	if (idCheck == false) {
        document.fr.member_user_id.focus();
        return false;
    }

	// 비밀번호 입력여부 체크
	var pw = document.fr.member_pw.value;
	if (pw == "") {
		alert('비밀번호를 입력하세요!');
		document.fr.member_pw.focus();
		return false;
	}

	// 비밀번호 유효성 체크(ajax)
	if(pwCheck == false){
		document.fr.member_pw.focus();
		return false;
	}
	
	// 비밀번호 확인 체크(ajax)
	if(pwCheck2 == false){
		document.fr.member_pw2.focus();
		return false;		
	}
	
	// 이메일 입력여부 체크 (이메일 주소 또는 직접 입력)
	var email = document.fr.member_email.value;
	var email2 = document.fr.member_email2.value;
	var domain = document.fr.domain.value;
	if (email == "" || (domain == "type" && email2 == "")) {
//		alert('이메일을 입력하세요!');
		document.fr.member_email.focus();
		return false;
	}

	// 생년월일 입력여부 체크
	var birth = document.fr.member_birth.value;
	if (birth == "") {
		document.fr.member_birth.focus();
		return false;
	}

	// 생년월일 유효성 체크 (8자)
	if (birth.length != 8) {
		alert('생년월일은 8자리입니다');
		document.fr.member_birth.focus();
		return false;
	}

	// 전화번호 입력여부 체크
	var phone = document.fr.member_phone.value;
	if (phone == "") {
		document.fr.member_phone.focus();
		return false;
	}

// 마케팅 수신 동의 체크여부 체크
//     var agree = document.querySelector('input[name="member_agree"]:checked');
//     if(!agree){
//       alert('마케팅 수신 동의를 체크하세요!');
//       return false;
//     }
	return true;
}

// 유의사항 checkbox 선택
$('#floatingAgree2').change(function() {
    isCheckBoxChecked = $(this).prop('checked');
    updateSubmitButton();
});


////////////////////////////////////////////////////////////////////////////
//order_pay.jsp 페이지
isPayCheck = false;

$('#Agree2').change(function(){
	isPayCheck = $(this).prop('checked');
	updateOrderPayButton();
});

function updateOrderPayButton() {
    // 필수 사항이 성공했을 때만 submit 버튼 활성화
    if (isPayCheck) {
        $('#payButton').prop('disabled', false);
    } else {
        $('#payButton').prop('disabled', true);
    }
}
/////////////////////////////////////////////////