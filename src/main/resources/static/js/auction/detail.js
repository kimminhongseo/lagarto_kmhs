//todo:첨부파일이 없다면 '첨부파일이 없습니다' 라고 하기
//data.image 가 null  이면 img_item에 innerHTML 하면 될거같은데,,,,
//todo:이미지 클릭시 크게 보이도록

// 이건 null일때 지우는거 역할 할때 사용하면될듯
// var con = document.getElementById("image"+x).src;
//
// if(con.indexOf('null') > 41){
//     con.remove();
// }

//magnificPopup 이기능은 jQuery를 사용하여 클릭하면
// 모달창 뜨면서 좌우로 넘길수 있음.


// 이미지 클릭하면 상세기되는거(팝업으로 근데 새창임)  window.open(_self) 해주면 현재 페이지에서 열림.
const img = document.getElementsByClassName("click_img");
for (let x = 0; x < img.length; x++) {
    img.item(x).onclick=function() {window.open(this.src,'_blank','toolbar=no,location=no,status=no,menubar=no, scrollbars=auto,resizable=no,' +
        'width=500,height=500 top=200 left=300')};


}














