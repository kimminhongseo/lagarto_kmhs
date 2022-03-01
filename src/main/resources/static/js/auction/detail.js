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
            console.log(iuser.value);

            fetch(`/ajax/auctionBid/buy?formbid=${formbid.value}&iboard=${parseInt(iboard)}&formimbuy=${formimbuy.value}
            &formbuy=${formbuy.value}&prebuyer=${prebuyer.value}&iuser=${iuser.value}`, {
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


//댓글 리스트
const getCommentList = () => {
    myFetch.get('/ajax/auctionCmt', list => {
        makeCommentRecordList(list);
    }, { iboard });
}


//리스트 만들기
// var sessionData = sessionStorage.;
// sessionStorage.setItem("sessionLogin",sessionData);


//-----------------댓글 수정삭제 (시작)
const sessionloginElem = document.querySelector('#dataLogin');
const makeCommentRecordList = list => {

    list.forEach(item => {
        const tdElem = document.createElement('td')
        const trElem = document.createElement('tr');
        trElem.innerHTML = `
                <td>${item.icmt}</td>
                <td>${item.ctnt}</td>
                <td>${item.nickname}</td>
                <td>${item.rdt}</td>
            `;
        tbodyElem.appendChild(trElem);
        //수정 삭제 구현  댓글쓴 사람이랑 현재 로그인한 사람이랑 같으면 수정/삭제 버튼 활성화
        if(item.iuser == sessionloginElem.dataset.iuser){
        const modBtn = document.createElement('input')
        modBtn.type = 'button';
        modBtn.value = '수정';
        modBtn.addEventListener('click', () => {
            const tdArr = trElem.querySelectorAll('td');
            const tdCell = tdArr[1];
            const modInput = document.createElement('input');
            modInput.value = item.ctnt;

            const saveBtn = document.createElement('input')
            saveBtn.type = 'button';
            saveBtn.value = '저장';

            saveBtn.addEventListener('click', () => {
                const param = {
                    icmt: item.icmt,
                    ctnt: modInput.value
                }
                myFetch.put('/ajax/auctionCmt', data => {
                    switch (data.result) {
                        case 0:
                            alert('댓글 수정에 실패하였습니다.')
                            break;
                        case 1:
                            tdCell.innerText = modInput.value;
                            item.ctnt = modInput.value;
                            removeCancelBtn();
                            break;
                    }
                }, param);
            });

            tdCell.innerHTML = null;
            tdCell.appendChild(modInput);
            tdCell.appendChild(saveBtn);
            const cancelBtn = document.createElement('input');
            cancelBtn.type = 'button';
            cancelBtn.value = '취소';
            cancelBtn.addEventListener('click', () => {
                tdCell.innerText = item.ctnt;
                removeCancelBtn();
            });

            const removeCancelBtn = () => {
                modBtn.classList.remove('hidden');
                delBtn.classList.remove('hidden');
                cancelBtn.remove();
            }

            tdElem.insertBefore(cancelBtn, modBtn);
            modBtn.classList.add('hidden');
            delBtn.classList.add('hidden');
        });
        const delBtn = document.createElement('input');
        delBtn.type = 'button';
        delBtn.value = '삭제';

        delBtn.addEventListener('click', () => {
            if (confirm('삭제하시겠습니까?')) {
                delCmt(item.icmt, trElem);
            }
        });
        trElem.appendChild(modBtn);
        trElem.appendChild(delBtn);

        }
    })
    return trElem;


}


//iuser 값 과 로그인한 iuser값

//삭제
const delCmt = (icmt, tr) => {
    myFetch.delete(`/ajax/auctionCmt/${icmt}`, data => {
        if(data.result) {
            tr.remove();
            // //만약 댓글이 하나도 없다면
            // if(getTrLen() === 1) {
            //     const cmtListElem = document.querySelector('#cmt_list');
            //     cmtListElem.innerText = '댓글 없음!';
            // }
        } else {
            alert('댓글을 삭제할 수 없습니다.');
        }
    });
}

// const getTrLen = () => {
//     const cmtListElem = document.querySelector('#cmt_list');
//     const trArr = cmtListElem.querySelectorAll('table tr');
//     return trArr.length;
// }



//-----------------댓글 수정삭제 (끝)

//댓글 입력 폼




getCommentList();
isfollow();



















