function getSelectedOptions() {
    var selectedOptions = []; // 선택된 옵션 ID를 담을 배열
    var checkboxes = document.querySelectorAll('input[name="options_id"]:checked'); // 체크된 모든 체크박스 가져오기

    checkboxes.forEach(function(checkbox) {
        selectedOptions.push(checkbox.value); // 선택된 옵션 ID를 배열에 추가
    });

    return selectedOptions; // 선택된 옵션 ID 배열 반환
}

function edit() {
    var selectedOptions = getSelectedOptions(); // 선택된 옵션 ID 배열 가져오기

    if (selectedOptions.length > 0) { // 선택된 옵션이 있는지 확인
        // 선택된 옵션 ID를 URL 파라미터로 추가하여 수정 페이지로 이동
        // var editUrl = './itemEditForm.it?optionsId=' + selectedOptions.join(','); // 수정 페이지 URL // js가 문자열로 보내서 탈락!
        var editUrl = './itemEditForm.it?optionsId=' + selectedOptions.map(Number).join(',');

        window.location.href = editUrl; // 수정 페이지로 이동
    } else {
        alert('수정할 상품을 선택해주세요.'); // 선택된 옵션이 없는 경우 알림
    }
}


function getSelectedRental() {
    var selectedRental = []; 
    var checkboxes = document.querySelectorAll('input[name="rental_item_id"]:checked'); 

    checkboxes.forEach(function(checkbox) {
        selectedRental.push(checkbox.value); 
    });

    return selectedRental;
}

function editRental() {
    var selectedRental = getSelectedRental(); 

    if (selectedRental.length > 0) { 
        var editUrl = './rentalEditForm.re?rentalItemId=' + selectedRental.map(Number).join(',');

        window.location.href = editUrl; 
    } else {
        alert('수정할 상품을 선택해주세요.'); 
    }
}



 /* (1) 체크박스 제어 */
 /*
$(document).ready(function() {
	$("#cbx_chkAll").click(function() {
		if ($("#cbx_chkAll").is(":checked")) $("input[name=options_id]").prop("checked", true);
		else $("input[name=options_id]").prop("checked", false);
	});

	$("input[name=options_id]").click(function() {
		var total = $("input[name=options_id]").length;
		var checked = $("input[name=options_id]:checked").length;

		if (total != checked) $("#cbx_chkAll").prop("checked", false);
		else $("#cbx_chkAll").prop("checked", true);
	});
}); */

$(document).ready(function() {
    $("#cbx_chkAll_options").click(function() {
        if ($("#cbx_chkAll_options").is(":checked")) $("input[name=options_id]").prop("checked", true);
        else $("input[name=options_id]").prop("checked", false);
    });

    $("input[name=options_id]").click(function() {
        var total = $("input[name=options_id]").length;
        var checked = $("input[name=options_id]:checked").length;

        if (total != checked) $("#cbx_chkAll_options").prop("checked", false);
        else $("#cbx_chkAll_options").prop("checked", true);
    });

    $("#cbx_chkAll_rental").click(function() {
        if ($("#cbx_chkAll_rental").is(":checked")) $("input[name=rental_item_id]").prop("checked", true);
        else $("input[name=rental_item_id]").prop("checked", false);
    });

    $("input[name=rental_item_id]").click(function() {
        var total = $("input[name=rental_item_id]").length;
        var checked = $("input[name=rental_item_id]:checked").length;

        if (total != checked) $("#cbx_chkAll_rental").prop("checked", false);
        else $("#cbx_chkAll_rental").prop("checked", true);
    });
});




/* (2) form 유효성 검사 */
window.addEventListener('load', () => {
	const forms = document.getElementsByClassName('validation-form');

	Array.prototype.filter.call(forms, (form) => {
		form.addEventListener('submit', function(event) {

			const categorySport = form.querySelector('select[name="category_sport"]');
			if (categorySport.value === "") {
				categorySport.setCustomValidity("미선택 상태에서는 제출할 수 없습니다.");
			} else {
				categorySport.setCustomValidity("");
			}

			const categoryMajor = form.querySelector('select[name="category_major"]');
			if (categoryMajor.value === "") {
				categoryMajor.setCustomValidity("미선택 상태에서는 제출할 수 없습니다.");
			} else {
				categoryMajor.setCustomValidity("");
			}

			const categorySub = form.querySelector('select[name="category_sub"]');
			if (categorySub.value === "") {
				categorySub.setCustomValidity("미선택 상태에서는 제출할 수 없습니다.");
			} else {
				categorySub.setCustomValidity("");
			}

			const categoryBrand = form.querySelector('select[name="category_brand"]');
			if (categoryBrand.value === "") {
				categoryBrand.setCustomValidity("미선택 상태에서는 제출할 수 없습니다.");
			} else {
				categoryBrand.setCustomValidity("");
			}

			if (form.checkValidity() === false) {
				event.preventDefault();
				event.stopPropagation();
			}

			form.classList.add('was-validated');
		}, false);
	});
}, false);
  
  

  
  
/* (4) 상품/옵션 삭제 처리 */
function selectOptions(url) {
    var selectedItems = $("input[name=options_id]:checked").map(function() {
        return this.value; 
    }).get(); // options_id를 가져와 selectedItems 배열에 저장함

    if (selectedItems.length === 1) { // 선택된 옵션ID 배열의 길이가 1인 경우
        if (url == 'ItemDeleteAction.it' && !confirm("선택한 상품을 삭제하시겠습니까?")) {
            return false;
        }

        // 선택된 옵션ID 배열을 서버로 전송
        $.ajax({
            type: 'POST',
            url: './ItemDeleteAction.it',
            data: { options_id: selectedItems },
            traditional: true, // 배열 전송을 위해 필요한 옵션
            success: function(response) {
                // 서버 응답에 따라 처리
                console.log(response);
                alert("상품 삭제 성공!");

                // 화면 갱신
                location.reload();
            },
            error: function(error) {
                console.error(error);
                alert("삭제 중 오류가 발생했습니다.");
            }
        });
    } else if (selectedItems.length > 1) { 
        alert("하나의 상품만 선택해 주세요!!");
    } else {
        alert("삭제할 상품을 선택해주세요!!");
    }
}


$(document).ready(function() {
   // 로컬 스토리지에서 상태를 읽어옴
   var isSidebarCollapsed = localStorage.getItem('isSidebarCollapsed');

   // 로컬 스토리지에 저장된 값이 없거나 true일 경우에는 접힌 상태로 시작
   if (isSidebarCollapsed === null || isSidebarCollapsed === 'true') {
      $('#sidebar').addClass('collapsed');
   }

   // 토글 버튼 클릭 시 사이드바 펼침/접힘 기능 추가
   $('.sidebar-toggler').click(function() {
      $('#sidebar').toggleClass('collapsed');

      // 상태를 로컬 스토리지에 저장
      var isCollapsed = $('#sidebar').hasClass('collapsed');
      localStorage.setItem('isSidebarCollapsed', isCollapsed);
   });
});


/* (6) 상품등록창 - 불러온 카테고리 정렬 (중복제거, 가나다순) */
function removeDuplicatesAndSort(selectElement) {
    var values = [];
    var options = selectElement.options;

    // 옵션을 배열로 복사하고 가나다 순으로 정렬
    var sortedOptions = Array.from(options).sort(function(a, b) {
        return a.text.localeCompare(b.text, 'ko-KR');
    });

    // 기존 옵션을 모두 제거
    while (selectElement.options.length > 0) {
        selectElement.remove(0);
    }

    // 정렬된 옵션을 중복 제거하면서 추가
    for (var i = 0; i < sortedOptions.length; i++) {
        var optionValue = sortedOptions[i].value;
        if (values.indexOf(optionValue) === -1) {
            values.push(optionValue);
            selectElement.add(sortedOptions[i]);
        }
    }
}
// 각 셀렉트 박스에 중복 옵션 제거 함수 호출
var selects = document.getElementsByClassName("Add-C")[0].getElementsByTagName("select");
for (var i = 0; i < selects.length; i++) {
    removeDuplicatesAndSort(selects[i]);
}
var selects = document.getElementsByClassName("Add-Z")[0].getElementsByTagName("select");
for (var i = 0; i < selects.length; i++) {
    removeDuplicatesAndSort(selects[i]);
}




/* (7) 상품등록창 - 렌탈 / 판매 구분 */
function handleCategoryChange() {
	var selectElement = document.querySelector('select[name="category_code"]');

	// 렌탈 선택 시
	if (selectElement.value === "2") {
		document.getElementById('rentalForm').style.display = 'block';
		document.getElementById('saleForm').style.display = 'none';
	}
	// 판매 선택 시
	else if (selectElement.value === "1") {
		document.getElementById('rentalForm').style.display = 'none';
		document.getElementById('saleForm').style.display = 'block';
	}
}




/* 검색 임시 */
document.getElementById('searchForm').addEventListener('submit', function(event) {
    event.preventDefault(); 

    var searchTerm = document.getElementById('searchInput').value;
    console.log('검색어:', searchTerm);

});





