(function() {
    'use strict';
    const dataElem = document.querySelector('#data');
    const searchParams = new URL(window.location.href).searchParams;
    const iboard = searchParams.get('iboard');

    const boardDetailElem = document.querySelector('#board_detail');
    const commentFormContainerElem = document.querySelector('#comment_form_container');
    const commentListElem = document.querySelector('#comment_list');
    const tbodyElem = commentListElem.querySelector('table > tbody');


    //글 삭제 버튼
    const delBtnElem = document.querySelector('#delBtn');
    if(delBtnElem) {
        delBtnElem.addEventListener('click', ()=> {
            console.log(iboard);

            if(confirm(msg.fnIsDel(`${iboard}번 글`))) {
                location.href=`/customer/del?iboard=${iboard}`;
            }
        });
    }

    //글 디테일 데이터 가져오기
    const getData = () => {
        fetch(`/customer/detail_item?iboard=${iboard}`)
            .then(res => res.text())
            .then(data => {
                console.log(data);
                boardDetailElem.innerHTML = data;
            });
    }
    getData();

    //댓글 리스트
    const getCommentList = () => {
        myFetch.get('/ajax/customerCmt', list => {
            console.log(list);
            makeCommentRecordList(list);
        }, { iboard });
    }
    getCommentList();

    function delCmtList () {
        commentListElem.innerHTML = '';
    }

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

    //댓글 입력 폼
    if(commentFormContainerElem) {
        const commentSubmitBtnElem = commentFormContainerElem.querySelector('button[name="comment_submit"]');
        const commentCtntInputElem = commentFormContainerElem.querySelector('input[name="ctnt"]');


        commentSubmitBtnElem.addEventListener('click', e => {
            console.log(commentCtntInputElem.value);

            const param = {
                iboard,
                'ctnt': commentCtntInputElem.value
            }

            myFetch.post('/ajax/customerCmt', data => {
                console.log(data.result);
                switch(data.result) {
                    case 0:
                        alert('댓글 전송에 실패하였습니다.');
                        break;
                    case 1:
                        commentCtntInputElem.value = null;
                        delCmtList();
                        getCommentList();
                        location.href=`/customer/detail?iboard=${iboard}`
                        break;
                }
            }, param);
        });
    }
})();