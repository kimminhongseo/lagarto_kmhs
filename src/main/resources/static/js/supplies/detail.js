
const searchParams = new URL(window.location.href).searchParams;
const iboard = searchParams.get('iboard');

//삭제기능
const delBtnElem = document.querySelector('#delBtn');
if(delBtnElem){
    delBtnElem.addEventListener('click',() =>{
        if(confirm('현재 게시된 글을 삭제하시겠습니까?')){
            location.href=`/supplies/del?iboard=${iboard}`;
            alert('삭제 되었습니다.');
        }
        return false;
    });
}

//이미지 스라이드?
$(document).ready(function(){
    $('.bxslider').bxSlider({
        auto: false,
        slideWidth: 600,
        slideMargin: 1000,
        speed: 500,
        pause: 4000,
        mode:'fade',
        autoControls: true,
        pager:true,
    });
});





