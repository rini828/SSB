<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Archivo&display=swap');

@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

* {
	font-family: 'Archivo', 'Noto Sans KR', sans-serif;
}

.sidebar {
	display: none;
}
</style>

<script>
  function toggleSidebar() {
    var sidebar = document.querySelector("#sidebar");

    if (sidebar) {
      sidebar.style.display = (sidebar.style.display === "none" || sidebar.style.display === "") ? "block" : "none";
    }
  }
</script>   

<nav id="sidebar" class="sidebar js-sidebar">
  <div class="sidebar-content js-simplebar">
	<a class="sidebar-brand" href="./NoticeList.no">
     <span class="align-middle">SSB</span>
    </a>
	<ul class="sidebar-nav">
		<li class="sidebar-header">
		</li>

		<li class="sidebar-item">
			<a class="sidebar-link" href="./AdMemberList.me">
              <i class="align-middle" data-feather="user"></i> 
              <span class="align-middle">회원관리</span>
           	</a>
		</li>
		<li class="sidebar-item">
			<a class="sidebar-link" href="./AdOrderList.od">
              <i class="align-middle" data-feather="shopping-cart"></i> 
              <span class="align-middle">주문관리</span>
            </a>
		</li>
		<li class="sidebar-item">
			<a class="sidebar-link" href="./ItemMgt.it">
              <i class="align-middle" data-feather="box"></i> 
              <span class="align-middle">제품 관리</span>
            </a>
		</li>
		
		
	  <li class="sidebar-header">
         게시판
      </li>

      <li class="sidebar-item">
         <a class="sidebar-link" href="./NoticeList.no">
              <i class="align-middle" data-feather="edit-2"></i> 
           <span class="align-middle">Notice</span>
            </a>
      </li>
      <li class="sidebar-item">
         <a class="sidebar-link" href="./InquiryList.iq">
              <i class="align-middle" data-feather="edit-2"></i> 
               <span class="align-middle">Q&A</span>
            </a>
      </li>
	  
	</ul>
  </div>
</nav>