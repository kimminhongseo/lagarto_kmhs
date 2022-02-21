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




    const searchParams = new URL(window.location.href).searchParams;
    const iboard = searchParams.get('iboard');


    const img = document.getElementsByClassName("click_img");
    for (let x = 0; x < img.length; x++) {
        img.item(x).onclick = function () {
            window.open(this.src, '_blank', 'toolbar=no,location=no,status=no,menubar=no, scrollbars=auto,resizable=no,' +
                'width=500,height=500 top=200 left=300')
        };
    }

//ㅁㄴㅇㅁㄴㅇsadas


//MOD 기능  >> 잘됨.
    const modBtnElem = document.querySelector('#modBtn');
    if (modBtnElem) {
        modBtnElem.addEventListener('click', () => {
            location.href = `/auction/mod?iboard=${iboard}`;
        });
    }


//DEL 기능  >> 잘됨

    const delBtnElem = document.querySelector('#delBtn');
    if (delBtnElem) {
        delBtnElem.addEventListener('click', () => {
            if (confirm('현재 게시된 글을 삭제하시겠습니까?')) {
                location.href = `/auction/del?iboard=${iboard}`;
            }
        });
    }


    const bidmodal = document.querySelector('#bid-modal');

    const formImbuy = document.querySelector('#formimbuy');
    const formBuy = document.querySelector('#formbuy');
    const formBid = document.querySelector('#formbid');

    const formbuybtn = document.querySelector('#formbuybtn');
    const formImbuyBtn = document.querySelector('#formimbuybtn');


    const startbuyElem = document.querySelector('#startbuy');

    //글 디테일 가져오기

    //여기서 경매가 등록하면 바뀌도롞?

    if (formbuybtn) {

        formbuybtn.addEventListener('click', () => {
            console.log(formbid.value);
            const dataiboard = document.querySelector('#data');
            console.log(parseInt(iboard));

            fetch(`/ajax/auctionBid/buy?formbid=${formbid.value}&iboard=${parseInt(iboard)}`, {
                method: 'post',
                headers: {'Content-type': 'application/json'}
            }).then(res => {
                return res.json();
            }).then(data => {
                switch (data){
                    case "true":
                        location.reload();
                        break;
                }


            })
        })
    }





















