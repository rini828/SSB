$(document).on("click",".closeButton",function(){
	$(this).parent().detach();
});
function addCart() {
	var arr = new Array;
	$("#cartPool .cartDiv").each(function() {
		let cartItem = {
  		"item_id" : $(this).find('.item_id').val(),
  		"cart_quantity" : $(this).find('.cart_quantity').val(),
  		"options_id" : $(this).find('.options_id').val()
		};
		arr.push(cartItem);
	});
	$.ajax({
		type: "POST",
		url: "./insertCart.ca",
		dataType: "text",
		data: {
			"arr": JSON.stringify(arr)
		},
		error: function() {
			alert('통신실패!!');
		},
		success: function(data) {
			
		}
	});
};
function setCart(){
	var html = "<div class='cartDiv'>";
	html += "<input type='input' class='item_id' value='" + $("#item_idSelecter").val() + "'>";
	html += "<input type='input' class='cart_quantity' value='" + $("#cart_quantitySelecter").val() + "'>";
	html += "<input type='input' class='options_id' value='" + $("#options_idSelecter").val() + "'>";
	html += "<input type='button' class='closeButton' value='close'>";
	html += "</div>";
	$("#cartPool").append(html);
}
