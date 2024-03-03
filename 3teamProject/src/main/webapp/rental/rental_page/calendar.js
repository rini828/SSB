document.addEventListener("DOMContentLoaded", function () {
  const today = new Date();
  let currentMonth = today.getMonth();
  let currentYear = today.getFullYear();
  const showCalendarBtn = document.getElementById("show-calendar-btn");
  const rentalDateInput = document.getElementById("rental-date-input");
  const calendarPopup = document.getElementById("calendar-popup");
  const calendar = document.getElementById("generated-calendar");
  const reservedDates = [];

  showCalendarBtn.addEventListener("click", function () {
    calendarPopup.style.display = "block";
    generateCalendar();
  });

  document.addEventListener("click", function (event) {
    if (event.target !== showCalendarBtn && !calendarPopup.contains(event.target)) {
      calendarPopup.style.display = "none";
    }
  });

  document.getElementById("rental-date-input").addEventListener("click", function () {
    calendarPopup.style.display = "block";
    generateCalendar();
  });

  function generateCalendar() {
	
	
    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
    
    
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
    const lastDayOfMonth = new Date(currentYear, currentMonth + 1, 0);
    const daysInMonth = lastDayOfMonth.getDate();

    let calendarHTML = "<div class='nav'>";
    calendarHTML += "<button class='nav-btn go-prev' onclick='prevMonth()'>&lt;</button>";
    calendarHTML += (currentMonth+1)+"월 ";
    calendarHTML += "<button class='nav-btn go-next' onclick='nextMonth()'>&gt;</button>";
    calendarHTML += "</div>";

    calendarHTML += "<table id='generated-calendar'>";
    calendarHTML += "<tr>";

    for (const day of daysOfWeek) {
      calendarHTML += "<th>" + day + "</th>";
    }

    calendarHTML += "</tr>";

    let dayCount = 1;
    let currentDate = null;

    for (let i = 0; i < 6; i++) {
      calendarHTML += "<tr>";

      for (let j = 0; j < 7; j++) {
        if ((i === 0 && j < firstDayOfMonth.getDay()) || dayCount > daysInMonth) {
          calendarHTML += "<td></td>";
        } else {
          currentDate = new Date(currentYear, currentMonth, dayCount);
          const isCurrentMonth = currentDate.getMonth() === currentMonth;
          const isReserved = reservedDates.includes(currentDate.toISOString().split("T")[0]);

          calendarHTML += "<td id='tdV' class='";
          calendarHTML += (isCurrentMonth ? 'current-month' : '') + (isReserved ? ' reserved' : '') + "'";
          calendarHTML += " data-date=" + currentDate.getFullYear() + "-" + (currentDate.getMonth() + 1) + "-" + currentDate.getDate();
          calendarHTML += ">";
          calendarHTML += currentDate.getDate() + "</td>";
          dayCount++;
        }
      }

      calendarHTML += "</tr>";

      if (dayCount > daysInMonth) break;
    }

    calendarHTML += "</table>";
    calendarPopup.innerHTML = calendarHTML;

    const calendar = document.getElementById("generated-calendar");

    calendar.addEventListener("click", function (event) {
      const clickedElement = event.target;

      if (clickedElement.classList.contains("current-month")) {
        const selectedDate = clickedElement.getAttribute("data-date");
        selectDate(selectedDate);
        rentalDateInput.value = selectedDate;
        calendarPopup.style.display = "none";
      }
    });
  }

  function selectDate(selectedDate) {
    if (reservedDates.includes(selectedDate)) {
      alert("이 날짜는 이미 예약되었습니다. 다른 날짜를 선택해주세요.");
    } else {
      reservedDates.push(selectedDate);
      alert("대여일 " + selectedDate + "이(가) 선택되었습니다!");
      rentalDateInput.value = selectedDate;
      calendarPopup.style.display = "none";
    }
  }

  window.prevMonth = () => {
    currentMonth--;
    if (currentMonth < 0) {
      currentMonth = 11;
      currentYear--;
    }
    generateCalendar();
  };

  window.nextMonth = () => {
    currentMonth++;
    if (currentMonth > 11) {
      currentMonth = 0;
      currentYear++;
    }
    generateCalendar();
  };

  window.goToday = () => {
    currentMonth = today.getMonth();
    currentYear = today.getFullYear();
    generateCalendar();
  };

  generateCalendar();
});
