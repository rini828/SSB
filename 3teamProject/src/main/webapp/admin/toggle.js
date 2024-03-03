/**
 * 관리자페이지 사이드바 토글 공통부
 */
 
 /* (5) 사이드바 자동으로 접기 */
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