
// todo: bxslider (용품에서 쓴것) 참고해서 활용해보기
// 이미지 클릭하면 상세기되는거(팝업으로 근데 새창임)  window.open(_self) 해주면 현재 페이지에서 열림.
const searchParams = new URL(window.location.href).searchParams;
const iboard = searchParams.get('iboard');
const commentListElem = document.querySelector('#comment_list');
const tbodyElem = commentListElem.querySelector('table > tbody');
const commentFormContainerElem = document.querySelector('#comment_form_container');
const formimbuybtnElem = document.querySelector('#formimbuybtn');


//이미지 작게  고정.
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
//DEL >> 잘됨.
const delBtnElem = document.querySelector('#delBtn');
if(delBtnElem){
    delBtnElem.addEventListener('click',() =>{
        if(confirm('현재 게시된 글을 삭제하시겠습니까?')){
            location.href=`/auction/del?iboard=${iboard}`;
        }
    });
}
const formbuybtn = document.querySelector('#formbuybtn');


//경매가 등록시 바뀌도록.
if (formbuybtn) {
    formbuybtn.addEventListener('click', () => {
        if(confirm("경매가를 수정 하시겠습니까?")){

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


//즉시구매시 처리 >> 거래내역에 올라가도록. (시작)
const payElem = document.querySelector('#pay');
if(payElem){
    payElem.addEventListener('click',()=>{
        if(confirm('정말로 구매하시겠습니까????')){
            const param ={
                iboard
            }
            myFetch.put('/auction/imbuy',data=>{
                location.href= "http://localhost:8090/user/mypage";
            },param);
        }

    })
}
//즉시구매시 처리 >> 거래내역에 올라가도록. (끝)




//댓글 입력폼. 내용이랑 입력했을때 반응
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
            // todo: alert 확인
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
//-----------------댓글 수정삭제 (시작)

const sessionloginElem = document.querySelector('#dataLogin');
const makeCommentRecordList = list => {

        //로그인 안되면 댓글만 보이게
        if(sessionloginElem == null){
            list.forEach(item => {
                const tdElem = document.createElement('td');
                const trElem = document.createElement('tr');
                trElem.innerHTML = `
                <td>${item.icmt}</td>
                <td>${item.ctnt}</td>
                <td>${item.nickname}</td>
                <td>${(item.rdt)}</td>
            `;
                tbodyElem.appendChild(trElem);
            });
        }

        //로그인 됐다면? >> 수정,삭제 여부
        if(sessionloginElem !=null){

        list.forEach(item => {
        const tdElem = document.createElement('td');
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
    // return trElem; 없어도 작동
     }
}

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
//----------------------------------댓글 수정삭제 (끝)----------------------------------


// ----------------------------------좋아요 구현 (시작)-------------------
const favIconElem = document.querySelector('#fav_icon');

const isFav = () => {
    myFetch.get(`/auction/like/${iboard}`, (data) => {
        console.log(data.result);
        switch(data.result) {
            case 0:
                disableFav();
                break;
            case 1:
                enableFav();
                break;
        }
    });
}

const disableFav = () => {
    if(favIconElem) {
        favIconElem.classList.remove('fa-solid');
        favIconElem.classList.add('fa-regular');
    }
}

const enableFav = () => {
    if(favIconElem) {
        favIconElem.classList.remove('fa-regular');
        favIconElem.classList.add('fa-solid');
    }
}

if(favIconElem) {
    isFav();
    favIconElem.addEventListener('click', () => {
        if(favIconElem.classList.contains('fa-regular')) { //no 좋아요
            const param = { iboard };
            myFetch.post(`/auction/like`, data => {
                switch (data.result) {
                    case 0:
                        alert('좋아요 처리에 실패하였습니다.');
                        break;
                    case 1:
                        enableFav();
                        break;
                }
            }, param);
        } else { //yes 좋아요
            myFetch.delete(`/auction/like/${iboard}`, data => {
                switch (data.result) {
                    case 0:
                        alert('좋아요 처리에 실패하였습니다.');
                        break;
                    case 1:
                        disableFav();
                        break;
                }
            });
        }
    });
}



//-----------------경매 남은시간 및 끝났을때 이벤트
const IuserElem = document.querySelector('#dataIuser'); //파는 사람
const BuyerElem = document.querySelector('#dataBuyer'); //사는 사람

const countDownTimer = function (id,date){
    var _vDate = new Date(date); // 전달 받은 일자
    var _second = 1000;
    var _minute = _second * 60;
    var _hour = _minute * 60;
    var _day = _hour * 24;
    var timer;

    function showRemaining(){
        var now = new Date();
        var distDt = _vDate - now;

        if(distDt<0){ //경매 마감되었을때.
            clearInterval(timer);
            document.getElementById(id).textContent='경매가 마감되었습니다.';
            document.getElementById("soldout").style.visibility = "visible"; //마감되면 sold out 이미지

            if(document.getElementById("bidwrite")){
                document.getElementById("bidwrite").style.visibility = "hidden"; //경매가 등록 사라지게
            }
            if(document.getElementById("formimbuybtn")){
                document.getElementById("formimbuybtn").style.visibility = "hidden"; //즉시구매 사라지게
            }
            const param={
                iboard,
                'iuser':IuserElem.dataset.iuser,
                'buyer':BuyerElem.dataset.buyer
            }
            myFetch.put(`/ajax/auctionBid`,data =>{
                switch (data.result){
                    case 0:
                        alert('실패');
                        break;
                    case 1:
                        alert("경매가 끝난 상품입니다.");
                        console.log("경매끝");
                        break;
                    case 2:
                        alert("유찰된 상품입니다.");
                        console.log("유찰");
                        break;
                }
            },param);

            return;
        }

        var days = Math.floor(distDt / _day);
        var hours = Math.floor((distDt % _day) / _hour);
        var minutes = Math.floor((distDt % _hour) / _minute);
        var seconds = Math.floor((distDt % _minute) / _second);

        //document.getElementById(id).textContent = date.toLocaleString() + "까지 : ";
        document.getElementById(id).textContent = days + '일 ';
        document.getElementById(id).textContent += hours + '시간 ';
        document.getElementById(id).textContent += minutes + '분 ';
        document.getElementById(id).textContent += seconds + '초 남았습니다';

    }
    timer= setInterval(showRemaining,1000);
}


const mdtElem = document.getElementById('mdt');
if(mdtElem){
        fetch(`/ajax/auctionBid/timer?iboard=${iboard}&mdt=${mdt.value}`,{
            method: 'GET',
            headers : {'Content-type': 'application/json'}
        }).then(res=>{
            return res.json();
        }).then(data=>{

            countDownTimer('finish',mdtElem.value); //string으로 받아와도 괜찮은가

        })
    }

//-----------------경매 남은시간 및 끝났을때 이벤트 (끝)







getCommentList();
isfollow();





















