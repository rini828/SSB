
// 제품 상세 - 옵션 선택
$('#options_idSelecter').change(function() {
    var html = "<div class='cartDiv'>";
    var selectedOption = $("#options_idSelecter option:selected");
    var optionsName = selectedOption.data("options-name");
    var optionsValue = selectedOption.data("options-value");
    var optionsId = $("#options_idSelecter").val();
    
	$("#cartPool .cartDiv").each(function() {
		if($(this).find('.options_id').val() == optionsId){
			alert('이미 추가된 상품입니다');
			return false();
		}
	});
    
    html += "<input type='hidden' class='item_id' value='" + $("#item_idSelecter").val() + "'>";
    html += "<span class='options_name'>" + optionsName + "</span>";
    html += "<span class='options_value'>" + optionsValue + "</span>";
    html += "<input type='button' class='cart_minus' onclick='decreaseQuantity()'value='-'>"; 
    html += "<input type='number' class='cart_quantity' value='1' onkeyup='checkReg(event)' min='1' max='100' readonly>"; 
    html += "<input type='button' class='cart_plus' onclick='increaseQuantity()' value='+'>"; 
    html += "<input type='hidden' class='options_id' value='" + optionsId + "'>";
    html += "<input type='button' class='closeButton' value='×'>";
    html += "</div>";
    $("#cartPool").append(html);
});


$(document).on("click",".closeButton",function(){
	$(this).parent().detach();
});
function getCartDiv(){
	var arr = new Array;
	$("#cartPool .cartDiv").each(function() {
		let cartItem = {
  		"item_id" : $(this).find('.item_id').val(),
  		"cart_quantity" : $(this).find('.cart_quantity').val(),
  		"options_id" : $(this).find('.options_id').val()
		};
		arr.push(cartItem);
	});
	return arr;
}

function addCart(type) {
	var arr = getCartDiv();
	if(arr.length == 0){
		alert('옵션을 선택해 주세요');
		return false;
	}
	$.ajax({
		type: "POST",
		url: "./insertCart.ca",
		dataType: "text",
		data: {
			"arr": JSON.stringify(arr),
			"type": type
		},
		error: function() {
			alert('통신실패!!');
		},
		success: function(data) {
			if(data == -1){
				if (confirm("이미 장바구니에 담긴 상품이 있습니다.\n장바구니로 이동하시겠습니까?") == true) {
					location.href = "./cartList.ca";
				} else {
					return false;
				}
			}else if(data == -2){
				if (confirm("장바구니로 이동하시겠습니까?") == true) {
					location.href = "./cartList.ca";
				} else {
					return false;
				}
			}else if(data == -3){
				alert("장바구니 담기 오류");
			}else{
				var input = data.replaceAll('"', "");
				location.href = "./Order.od?checkArray="+input;
			}
		}
	});
};




// 제품 상세이미지 펼치고 닫기
document.addEventListener('DOMContentLoaded', function() {
    const content = document.querySelector('.detailinfo > .content');
    const btnOpen = document.querySelector('.btn_open');
    const btnClose = document.querySelector('.btn_close');

    btnOpen.addEventListener('click', function() {
        content.style.maxHeight = 'none'; 
        btnOpen.classList.add('hide');
        btnClose.classList.remove('hide');
    });

    btnClose.addEventListener('click', function() {
        content.style.maxHeight = '300px'; 
        btnOpen.classList.remove('hide');
        btnClose.classList.add('hide');
    });
});




// 옵션 선택 - 수량 변경 버튼
$(document).on("click", ".cart_minus", function() {  
    var cartQuantityInput = $(this).parent().find('.cart_quantity');
    var currentValue = parseInt(cartQuantityInput.val());
    if (currentValue > 1) {
        cartQuantityInput.val(currentValue - 1);
    }
});

$(document).on("click", ".cart_plus", function() {   
    var cartQuantityInput = $(this).parent().find('.cart_quantity');
    var currentValue = parseInt(cartQuantityInput.val());
    cartQuantityInput.val(currentValue + 1);
});




// Q&A 목록 펼치고 닫기 + 게시판
window.onload = () => {
	  // panel-faq-container
	  const panelFaqContainer = document.querySelectorAll(".panel-faq-container"); // NodeList 객체
	  
	  // panel-faq-answer
	  let panelFaqAnswer = document.querySelectorAll(".panel-faq-answer");

	  // btn-all-close
	  const btnAllClose = document.querySelector("#btn-all-close");
	  
	  // 반복문 순회하면서 해당 FAQ제목 클릭시 콜백 처리
	  for(let i=0;i < panelFaqContainer.length; i++) {
	    panelFaqContainer[i].addEventListener('click', function() {
	      // Q&A 제목 클릭 시 -> 본문 보이게 -> active 클래스 추가
	      panelFaqAnswer[i].classList.toggle('active');
	    });
	  };
	  
	  btnAllClose.addEventListener('click', function() {
	    // 버튼 클릭시 처리할 일  
	    for(let i=0; i < panelFaqAnswer.length; i++) {
	        panelFaqAnswer[i].classList.remove('active');
	    };
	  });
	}
