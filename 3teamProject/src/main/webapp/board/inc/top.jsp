<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Archivo&display=swap');
  @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
  
* {
	font-family: 'Archivo', 'Noto Sans KR', sans-serif;
}
</style>

<header class="navbar navbar-expand navbar-light navbar-bg">
	<a class="sidebar-toggle js-sidebar-toggle" onclick="toggleSidebar()">
	  <i class="hamburger align-self-center"></i>
    </a>
    <a href="./Main.in">
	  <img class="home" alt="home" src="./adImg/icons/home.png" width="22" height="22">
 	</a>
    
    <!--  -->
	<c:if test="${sessionScope.userId == null }">
      <a href="./AdminLogin.ad">
		<img class="log-button" alt="login" src="./adImg/icons/login.png" width="25" height="25">
 	  </a>   
	</c:if>
	<c:if test="${sessionScope.userId != null }">
      <a href="./AdminLogout.ad">
		<img class="log-button" alt="logout" src="./adImg/icons/logout.png" width="25" height="25">
 	  </a>   
	</c:if>
</header>