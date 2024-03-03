$(document).ready(function() {
	$("#cbx_chkAll").click(function() {
		if ($("#cbx_chkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
		else $("input[name=chk]").prop("checked", false);
	});

	$("input[name=chk]").click(function() {
		var total = $("input[name=chk]").length;
		var checked = $("input[name=chk]:checked").length;

		if (total != checked) $("#cbx_chkAll").prop("checked", false);
		else $("#cbx_chkAll").prop("checked", true);
	});
});



    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    }, false);
    
    /* select */
  const objTest = {
    의류: ['상의', '하의', '상하의세트', '이너웨어', '수영복/비치웨어'],
    잡화: ['가방', '장갑', '모자', '보호대', '양말/레그웨어', '선글라스', '액세서리', '기타'],
    신발: ['러닝화', '워킹화', '트레킹화', '스포츠화', '기타'],
    용품: ['코칭/경기용품', '캠핑용품', '기타', '뭐추가하지?']
  };

  $(function () {
    $('#major').change(function () {
      const classVal = $(this).val();
      const sub = $('#sub');
      sub.empty().append($('<option>', {
        value: "",
        text: "소분류를 선택해 주세요"
      }));
      $.each(objTest[classVal], function (index, value) {
        sub.append($('<option>', {
          value: value,
          text: value
        }));
      });
    });
  });
  
  $("#img_main").on('change',function(){
	  var fileName = $("#img_main").val();
	  $(".upload-main").val(fileName);
	});
  $("#img_sub").on('change',function(){
	  var fileName = $("#img_sub").val();
	  $(".upload-sub").val(fileName);
	});
  $("#img_logo").on('change',function(){
	  var fileName = $("#img_logo").val();
	  $(".upload-logo").val(fileName);
	});
  
// submit - confirmDelete 기능
function confirmDelete() {
    if (confirm('정말 삭제하시겠습니까?')) {
        document.getElementById('deleteForm').submit();
    }
}  
