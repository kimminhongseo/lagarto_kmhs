//todo:첨부파일이 없다면 '첨부파일이 없습니다' 라고 하기
//data.image 가 null  이면 img_item에 innerHTML 하면 될거같은데,,,,
//todo:이미지 클릭시 크게 보이도록

//magnificPopup 이기능은 jQuery를 사용하여 클릭하면
// 모달창 뜨면서 좌우로 넘길수 있음.


// 이미지 클릭하면 상세기되는거(팝업으로 근데 새창임)  window.open(_self) 해주면 현재 페이지에서 열림.
const searchParams = new URL(window.location.href).searchParams;
const iboard = searchParams.get('iboard');


const commentListElem = document.querySelector('#comment_list');
const tbodyElem = commentListElem.querySelector('table > tbody');
const commentFormContainerElem = document.querySelector('#comment_form_container');


const img = document.getElementsByClassName("click_img");
for (let x = 0; x < img.length; x++) {
    img.item(x).onclick=function() {window.open(this.src,'_blank','toolbar=no,location=no,status=no,menubar=no, scrollbars=auto,resizable=no,' +
        'width=500,height=500 top=200 left=300')};
}

//MOD 기능  >> 잘됨.
const modBtnElem = document.querySelector('#modBtn');
if(modBtnElem) {
    modBtnElem.addEventListener('click', ()=> {
        location.href=`/auction/mod?iboard=${iboard}`;
    });
}

const delBtnElem = document.querySelector('#delBtn');
if(delBtnElem){
    delBtnElem.addEventListener('click',() =>{
        if(confirm('현재 게시된 글을 삭제하시겠습니까?')){
            location.href=`/auction/del?iboard=${iboard}`;
        }
    });
}
    const formbuybtn = document.querySelector('#formbuybtn');

    //여기서 경매가 등록하면 바뀌도롞?

    if (formbuybtn) {
        formbuybtn.addEventListener('click', () => {
            if(confirm("경매가를 수정 하시겠습니까?")){
                console.log(formbid.value);
                console.log(formimbuy.value);
                console.log(parseInt(iboard));
                console.log(prebuyer.value);
                console.log(formbuy.value);

                fetch(`/ajax/auctionBid/buy?formbid=${formbid.value}&iboard=${parseInt(iboard)}&formimbuy=${formimbuy.value}
            &formbuy=${formbuy.value}&prebuyer=${prebuyer.value}`, {
                    method: 'post',
                    headers: {'Content-type': 'application/json'}
                }).then(res => {
                    return res.json();
                }).then(data => {

                    switch (data){
                        case 1: //fetch 로 전에있던값을 넣어줘라. 그 전 사람에게. case: 1일떄 일어나겠네.
                            window.close();
                            location.reload();
                            break;
                        case 0:
                            alert("즉시구매가보다 낮은 금액을 입력하세요");
                            return false;
                            break;
                        case 2:
                            alert("현재가보다 높은 금액을 입력하세요");
                            return false;
                            break;
                        case 3:
                            alert("잔액이 부족합니다. 충전후 경매를 이용해 주세요.")
                            return false;
                            break;
                    }


                })
            }

        })
    }

    //댓글

//댓글 리스트
const getCommentList = () => {
    myFetch.get('/ajax/auctionCmt', list => {
        makeCommentRecordList(list);
    }, { iboard });
}
getCommentList();


//리스트 만들기
const makeCommentRecordList = list => {

    list.forEach(item => {

        const trElem = document.createElement('tr');
        trElem.innerHTML = `
                <td>${item.icmt}</td>
                <td>${item.ctnt}</td>
                <td>${item.nickname}</td>
                <td>${item.rdt}</td>
            `;
        tbodyElem.appendChild(trElem);
    });
}
//-----------------댓글 수정삭제 (시작)

ㅇ
//-----------------댓글 수정삭제 (끝)

//댓글 입력 폼

        if (commentFormContainerElem) {
            const commentSubmitBtnElem = commentFormContainerElem.querySelector('button[name="comment_submit"]');
            const commentCtntInputElem = commentFormContainerElem.querySelector('input[name="ctnt"]');


            commentSubmitBtnElem.addEventListener('click', e => {
                console.log(commentCtntInputElem.value);

                const param = {
                    iboard,
                    'ctnt': commentCtntInputElem.value
                }

                myFetch.post('/ajax/auctionCmt', data => {
                    console.log(data.result);
                    switch (data.result) {
                        case 0:
                            alert('댓글 전송에 실패하였습니다.');
                            break;
                        case 1:
                            commentCtntInputElem.value = null;
                            //댓글 쓴 후 새로고침
                            location.reload();
                            break;
                    }
                }, param);
            });


}


    isfollow();




















