
const searchParams = new URL(window.location.href).searchParams;
const iboard = searchParams.get('iboard');

//삭제기능
const delBtnElem = document.querySelector('#delBtn');
if(delBtnElem){
    delBtnElem.addEventListener('click',() =>{
        if(confirm('현재 게시된 글을 삭제하시겠습니까?')){
            location.href=`/supplies/del?iboard=${iboard}`;
        }
        alert('삭제 되었습니다.');
    });
}