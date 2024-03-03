<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    #calendar-container {
      position: relative;
      display: inline-block;
    }

    #calendar-popup {
      position: absolute;
      display: none;
      border: 1px solid #ccc;
      background-color: #fff;
      padding: 10px;
      z-index: 1;
    }

    #calendar-popup table {
      width: 100%;
      border-collapse: collapse;
    }

    #calendar-popup th, #calendar-popup td {
      padding: 5px;
      text-align: center;
    }

    #calendar-popup .current-month {
      background-color: #e0e0e0;
    }
  </style>
</head>
<body>

<div id="calendar-container">
  <input type="text" id="date-input" readonly>
  <div id="calendar-popup"></div>
</div>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const dateInput = document.getElementById("date-input");
    const calendarPopup = document.getElementById("calendar-popup");
    const reservedDates = []; // 예약된 날짜를 저장하는 배열

    dateInput.addEventListener("click", function () {
      calendarPopup.style.display = "block";
      generateCalendar();
    });

    document.addEventListener("click", function (event) {
      if (event.target !== dateInput) {
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

      let calendarHTML = "<table>";

      // Header 생성
      calendarHTML += "<tr>";
      const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
      for (const day of daysOfWeek) {
        calendarHTML += "<th>" + day + "</th>";
      }
      calendarHTML += "</tr>";

      // 달력 내용 생성
      let dayCount = 1;
      for (let i = 0; i < 6; i++) {
        calendarHTML += "<tr>";
        for (let j = 0; j < 7; j++) {
          if ((i === 0 && j < firstDayOfMonth.getDay()) || dayCount > daysInMonth) {
            calendarHTML += "<td></td>";
          } else {
            const currentDate = new Date(year, month, dayCount);
            const isCurrentMonth = currentDate.getMonth() === month;
            const isReserved = reservedDates.includes(currentDate.toISOString().split("T")[0]);

            // 예약된 날짜는 다른 스타일로 강조
            calendarHTML += "<td class='" + (isCurrentMonth ? 'current-month' : '') + (isReserved ? ' reserved' : '') + "' onclick='selectDate(\"" + currentDate.toISOString().split("T")[0] + "\")'>" + currentDate.getDate() + "</td>";
            dayCount++;
          }
        }
        calendarHTML += "</tr>";
        if (dayCount > daysInMonth) break;
      }

      calendarHTML += "</table>";
      calendarPopup.innerHTML = calendarHTML;
    }

    function selectDate(selectedDate) {
      // 예약된 날짜인지 확인
      if (reservedDates.includes(selectedDate)) {
        alert("이 날짜는 이미 예약되었습니다. 다른 날짜를 선택해주세요.");
      } else {
        // 예약 기능 추가: 여기에서 예약을 처리하는 로직을 추가하세요.
        reservedDates.push(selectedDate); // 배열에 예약된 날짜 추가
        alert("대여일 " + selectedDate + "이(가) 선택되었습니다!");

        dateInput.value = selectedDate;
        calendarPopup.style.display = "none";
      }
    }
  });
</script>

</body>
</html>

