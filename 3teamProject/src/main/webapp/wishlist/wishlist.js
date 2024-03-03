$(function() {
	//위시리스트 감지
	var wishlistDiv = document.querySelectorAll("div.wishlist");
	$.ajax({
		type: "POST",
		url: "./getWishlist.wl",
		dataType: "text",
		data: {
			
		},
		error: function() {
			//alert('통신실패!!');
		},
		success: function(data) {
			var item_idArr = JSON.parse(data);
			var value;
			for (let i = 0; i < wishlistDiv.length; i++) {
				value = wishlistDiv[i].getAttribute('value');
				item_idArr.find(function(element) {
					if (element == value) {
						$('div[value=' + value + ']').html("<img width='22' height='22' src='./main/img/redHeart.png' alt='inserted'>");//img src 변경
					}
				});
			}
		}
	});
	//위시리스트 선택/해제
	$(".wishlist").click(function() {
		var item_id = $(this).attr("value");
		$.ajax({
			type: "POST",
			url: "./switchWishlist.wl",
			dataType: "text",
			context: this,
			data: {
				"item_id": item_id
			},
			error: function() {
				alert('통신실패!!');
			},
			success: function(data) {
				var input = data.replaceAll('"', "");
				if (input == "inserted") {
					html = "<img width='25' height='25' src='./main/img/redHeart.png' alt='inserted'>";
				} else if (input == "deleted") {
					html = "<img width='22' height='22' src='./main/img/heart.png' alt='deleted'>";
				} else {
					alert("로그인 후 시도해주세요.");
				}
				$(this).html(html);
				if (input == "inserted") {
//					if (confirm("위시리스트 페이지로 이동하시겠습니까?") == true) {
//						location.href = "./wishlist.wl";
//					} else {
//						return false;
//					}
				}
			}
		});
	});
});

//위시리스트 양식
//<div class="wishlist" value="item_id">
//	<img src="위시리스트이미지" alt="위시리스트">
//</div>
