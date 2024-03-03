function view(){
    window.open("/home/login.html","login","width=1000px, height=800px")
}

function goToCenter(){
    window.open("/home/center.html","center","width=1000px, height=800px")
}

function windowClose(){
    window.close('/home/login.html','view'); 
}


const menu = document.getElementById('onmenu');
const testBtn = document.getElementById('test-btn');
const menuClose = document.getElementById('menuClose');
const dragMenu = document.getElementById('menuClose');
if(testBtn){
    testBtn.addEventListener('mouseover', () =>{
        menu.classList.add('drag')
        testBtn.classList.add('move-test-btn')
    })
}

if(menu){
    menu.addEventListener('mouseleave', () =>{
        menu.classList.remove('drag')
        testBtn.classList.remove('move-test-btn')
    })
}