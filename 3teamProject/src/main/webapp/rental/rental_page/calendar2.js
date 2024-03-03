/**
 * 예약하기 기능
 */ 

 document.addEventListener("DOMContentLoaded", function () {
  const showCalendarBtn = document.getElementById("show-calendar-btn"); 
  const rentalDateInput = document.getElementById("rental-date-input");
  const calendarPopup = document.getElementById("calendar-popup");
  const reservedDates = []; // 예약된 날짜를 저장하는 배열
  



  showCalendarBtn.addEventListener("click", function () {

    calendarPopup.style.display = "block";
    generateCalendar();
    
  
    
  });

  document.addEventListener("click", function (event) {
	
	// selectDate(currentDate.toISOString().split("T")[0]);
    if (event.target !== showCalendarBtn && !calendarPopup.contains(event.target)) {
      calendarPopup.style.display = "none";
    }   
    
  });
  


	
  function generateCalendar() {
	
	  
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth();
    const firstDayOfMonth = new Date(year, month, 1);
    const lastDayOfMonth = new Date(year, month + 1, 0);
    const daysInMonth = lastDayOfMonth.getDate();
    
      
    
   
    /* 변경사항 --------------------------------------------------------------------------*/
    let calendarHTML = "<table id='generated-calendar'>";
    /* 변경사항 --------------------------------------------------------------------------*/
    
    // Header 생성
    calendarHTML += "<tr>";
    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
    
    
    
    for (const day of daysOfWeek) {
      calendarHTML += "<th>" + day + "</th>";
    }
    calendarHTML += "</tr>";

    // 달력 내용 생성
    let dayCount = 1;
    /* 변경사항 --------------------------------------------------------------------------*/
    let currentDate = null;
    /* 변경사항 --------------------------------------------------------------------------*/
    
    for (let i = 0; i < 6; i++) {
      calendarHTML += "<tr>";
      for (let j = 0; j < 7; j++) {
        if ((i === 0 && j < firstDayOfMonth.getDay()) || dayCount > daysInMonth) {
          calendarHTML += "<td></td>";
        } else {
          /* 변경사항 --------------------------------------------------------------------------*/
          currentDate = new Date(year, month, dayCount);
          /* 변경사항 --------------------------------------------------------------------------*/
          const isCurrentMonth = currentDate.getMonth() === month;
          const isReserved = reservedDates.includes(currentDate.toISOString().split("T")[0]);
		 
		  
//          console.log(year+'/'+month+'/'+dayCount);
//          console.log( currentDate +"1@@@@@@@@@@");

          /* 변경사항 --------------------------------------------------------------------------*/
          // 예약된 날짜는 다른 스타일로 강조
          calendarHTML += "<td id='tdV' class=\'";
          calendarHTML += (isCurrentMonth ? 'current-month' : '') + (isReserved ? ' reserved' : '')+"\'";
          calendarHTML += " data-date="+currentDate.getFullYear()+"-"+(currentDate.getMonth()+1)+"-"+currentDate.getDate();
          // => 날짜 표시처리 로직은 손볼 필요있음!!!
          calendarHTML += ">";
          calendarHTML += currentDate.getDate() + "</td>";
          dayCount++;
          
      
          /* 변경사항 --------------------------------------------------------------------------*/
          
        
        }
      }
      calendarHTML += "</tr>";
      if (dayCount > daysInMonth) break;
    }

    calendarHTML += "</table>";
    calendarPopup.innerHTML = calendarHTML;
    // 달력 내용 생성 - 끝
    
    /* 변경사항 --------------------------------------------------------------------------*/
    // 생성된 달력의 날짜 클릭 이벤트 처리
    const calendar = document.getElementById("generated-calendar");
    // => 테이블 생성태그에 ID 추가설정 
    //console.log(calendar);

    // 해당  calendar에 클릭 이벤트가 발생하는 시점
    calendar.addEventListener("click", function (event) {
      // 클릭 이벤트 대상을 확인
      const clickedElement = event.target;
      console.log(clickedElement);

      // 날짜를 클릭한 경우에만 처리 'current-month' 속성이 있는경우(사용가능한경우)
      if (clickedElement.classList.contains("current-month")) {
        const selectedDate = clickedElement.getAttribute("data-date");
        selectDate(selectedDate);
        
        // 선택된 날짜 서버로 전송하는 ajax
        const dateValue = new Date(selectedDate);
      const formattedDate = `${dateValue.getFullYear()}-${dateValue.getMonth() + 1}-${dateValue.getDate()}`;

      //------------ 선택된 날짜를 서버로 전송하는 코드인데, 사용안함. 지워도 안됨---------------------//
      sendSelectedDateToServer(formattedDate);

      rentalDateInput.value = formattedDate;
      calendarPopup.style.display = "none";
        
      }
      
      function sendSelectedDateToServer(selectedDate) {
      // Ajax를 사용하여 서버로 데이터 전송
      const xhr = new XMLHttpRequest();
      xhr.open("POST", "/your-server-endpoint", true);
      xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

      xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
          // 서버 응답을 받았을 때의 처리
          if (xhr.status === 200) {
            console.log("서버 응답:", xhr.responseText);
          } else {
            console.error("서버 응답 오류:", xhr.status);
          }
        }
      };
       // 서버로 전송할 데이터
      const data = JSON.stringify({ selectedDate: selectedDate });

      xhr.send(data);
    }
    // ----------------------- 선택된 날짜를 서버로 전송하는 코드인데, 사용안함. 지워도 안됨- ajax 설정 -----------------------
      
    });
    
    
    
    /* 변경사항 --------------------------------------------------------------------------*/
   
  }//generateCalendar
  


  
	  
  
  function selectDate(selectedDate) {
//	  alert("selectDate 호출");
	    // 예약된 날짜인지 확인
	    if (reservedDates.includes(selectedDate)) {
	      alert("이 날짜는 이미 예약되었습니다. 다른 날짜를 선택해주세요.");
	    } else {
	      // 예약 기능 추가: 여기에서 예약을 처리하는 로직을 추가하세요.
	      reservedDates.push(selectedDate); // 배열에 예약된 날짜 추가
	      alert("대여일 " + selectedDate + "이(가) 선택되었습니다!");
	
	      rentalDateInput.value = selectedDate;
	      calendarPopup.style.display = "none";
	      
	    }
  }//selectDate


});
