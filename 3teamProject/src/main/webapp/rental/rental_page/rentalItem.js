/**
 * 렌탈아이템 상세 페이지 js 모음
 */
 
 /* header js 코드 영역 접어둠. */
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


/* 상단 메뉴바 오픈 및 영역 확장 js코드 접어둠 */
document.addEventListener("DOMContentLoaded", function() {

	const submenus = document.querySelectorAll(".menu .submenu");
	const submenuContents = document.querySelectorAll(".submenu-content");

	submenus.forEach(function(submenu, index) {
		const submenuContent = submenuContents[index];

		// 메뉴 항목을 호버할 때
		submenu.addEventListener("mouseover", function() {
			// 해당 메뉴 항목의 하위 메뉴가 표시되면 메뉴의 높이를 조절
			submenuContent.style.display = "block";
			submenu.style.height = submenuContent.clientHeight + "px";
		});

		// 메뉴 항목에서 마우스가 나갈 때
		submenu.addEventListener("mouseout", function() {
			// 해당 메뉴 항목의 하위 메뉴가 숨겨지면 메뉴의 높이를 원래대로 복원
			submenuContent.style.display = "none";
			submenu.style.height = "auto";
		});
	});
});





/* 달력 */
document.addEventListener('DOMContentLoaded', function() {
    const content = document.querySelector('.detailinfo > .content');
    const btnOpen = document.querySelector('.btn_open');
    const btnClose = document.querySelector('.btn_close');

    btnOpen.addEventListener('click', function(e) {
        content.style.maxHeight = 'none'; // 변경된 부분
        btnOpen.classList.add('hide');
        btnClose.classList.remove('hide');
    });

    btnClose.addEventListener('click', function(e) {
        content.style.maxHeight = '300px'; // 변경된 부분
        btnOpen.classList.remove('hide');
        btnClose.classList.add('hide');
    });
});



/*Q&A 목록 펼치고 닫기*/	
window.onload = () => {
	  // panel-faq-container
	  const panelFaqContainer = document.querySelectorAll(".panel-faq-container"); // NodeList 객체
	  
	  // panel-faq-answer
	  let panelFaqAnswer = document.querySelectorAll(".panel-faq-answer");

	  // btn-all-close
	  const btnAllClose = document.querySelector("#btn-all-close");
	  
	  // 반복문 순회하면서 해당 FAQ제목 클릭시 콜백 처리
	  for(let i=0;i < panelFaqContainer.length; i++) {
	    panelFaqContainer[i].addEventListener('click', function() { // 클릭 시 처리할 일
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
	
	
