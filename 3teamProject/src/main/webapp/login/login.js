console.log("login.js 파일이 실행되었습니다.");

function displayAlert() {
    alert("로그인 실패");
}

function showLoginFailedMessage() {
    alert("로그인에 실패했습니다. 다시 시도해주세요.");
    redirectToLoginPage();
}

function redirectToLoginPage() {
    window.location.href = "login.jsp";
}

var referrer = document.referrer

if(referrer.indexOf(page)!=-1){
	window.location.href="./Main.in";
}else{
	window.location.href=referrer;
}
