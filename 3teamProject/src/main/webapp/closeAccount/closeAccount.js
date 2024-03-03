    
document.addEventListener('DOMContentLoaded', function() {
    const accordionBtns = document.querySelectorAll('.accordion-btn');

    accordionBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {
            this.classList.toggle('active');
            const content = this.nextElementSibling;
            if (content.style.display === 'block') {
                content.style.display = 'none';
            } else {
                content.style.display = 'block';
            }
        });
    });
});

function toggleCategory() {
    var categoryContent = document.querySelector('.category-content');
    var brandContent = document.querySelector('.brand-content');

    categoryContent.style.display = 'block';
    brandContent.style.display = 'none';
}

function toggleBrand() {
    var categoryContent = document.querySelector('.category-content');
    var brandContent = document.querySelector('.brand-content');

    categoryContent.style.display = 'none';
    brandContent.style.display = 'block';
}

/////////////////////////////////////////////////////////////////////////////////////////////////

// submit 버튼 업데이트 함수
let pwCheck = false; // submit 시에 필요
let isOptionSelected = false; // 탈퇴 사유 선택 여부
let isCheckBoxChecked = false; // 유의사항 checkbox 선택 여부

// enterKey 처리
$(".inputPw").keypress(function(e) {
  if (e.keyCode === 13) {
    e.preventDefault();
  }
});

// 해당 사항이 성공했을 때만 submit 버튼 활성화
function updateSubmitButton(){
	if(pwCheck && isCheckBoxChecked){
		$('.checkButton').prop('disabled', false);
	}else{
		$('.checkButton').prop('disabled', true);
	}
}

// 비밀번호 확인 ajax
$('.inputPw').keyup(function(){
	checkingPassword();
});

function checkingPassword(){
	
	let userPw = $('.inputPw').val();  
	
	$.ajax({
		url: "./MemberCloseAccountPwCheck.me",
		type:"post",
		data: {userPw:userPw},
		dataType: 'text',
		success: function(result){
			if(result == "1"){
				$('.pwChecking').html("일치합니다.");
				$('.pwChecking').css('color', 'skyblue');
				pwCheck = true;
			}else{
				$('.pwChecking').html("일치하지 않습니다.");
				$('.pwChecking').css('color', 'red');
				pwCheck = false;
			}
			updateSubmitButton()
		},
		error:function(){
			alert("서버요청실패");
		}
	});
}

//탈퇴 사유 선택
//$('#withdrawalReason').change(function() {
//    isOptionSelected = $(this).val() !== 'r0'; // 'r0'이 아닌 다른 값을 선택했는지 확인
//    updateSubmitButton();
//});

// 유의사항 checkbox 선택
$('#checkBox').change(function() {
    isCheckBoxChecked = $(this).prop('checked');
    updateSubmitButton();
});


// submit 버튼 함수
function confirmCloseAccount() {
    if (confirm('정말 탈퇴하시겠습니까?')) {
        document.getElementById('reasonForm').submit();
    }
}








