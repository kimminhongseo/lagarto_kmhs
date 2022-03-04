(function() {
    'use strict';
    const dataElem = document.querySelector('#data');
    const searchParams = new URL(window.location.href).searchParams;
    const iboard = searchParams.get('iboard');

    const boardDetailElem = document.querySelector('#board_detail');
    const commentFormContainerElem = document.querySelector('#comment_form_container');
    const commentListElem = document.querySelector('#comment_list');
    const tbodyElem = commentListElem.querySelector('table > tbody');
    const sessionloginElem = document.querySelector('#dataLogin');


    //글 삭제 버튼
    const delBtnElem = document.querySelector('#delBtn');
    if (delBtnElem) {
        delBtnElem.addEventListener('click', e => {
            if (confirm(msg.fnIsDel(`${iboard}번 글`)) == true) {
                location.href = `/customer/del?iboard=${iboard}`;
            } else {
                e.preventDefault();
                return;
            }
        });
    }

    //글 수정 버튼
    const modBtnElem = document.querySelector('#modBtn');
    if (modBtnElem) {
        modBtnElem.addEventListener('click', () => {
            location.href = `/customer/upd?iboard=${iboard}`;
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

    // 댓글 부분
    // 댓글 리스트
    const getCommentList = () => {
        myFetch.get('/ajax/customerCmt', list => {
            makeCommentRecordList(list);
        }, { iboard });
    }
    getCommentList();

    //리스트 만들기
    // const makeCommentRecordList = list => {
    //
    //     list.forEach(item => {
    //         const trElem = document.createElement('tr');
    //         trElem.innerHTML = `
    //             <td>${item.icmt}</td>
    //             <td>${item.ctnt}</td>
    //             <td>${item.nickname}</td>
    //             <td>${item.rdt}</td>
    //         `;
    //         tbodyElem.appendChild(trElem);
    //     });
    // }

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
                        //댓글 쓴 후 새로고침
                        location.reload();
                        break;
                }
            }, param);
        });
    }
    //-----------------------------------------------------------------------------
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
                        myFetch.put('/ajax/customerCmt', data => {
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
        return trElem;
        })


    }


//iuser 값 과 로그인한 iuser값

//삭제
    const delCmt = (icmt, tr) => {
        myFetch.delete(`/ajax/customerCmt/${icmt}`, data => {
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

    //좋아요 ------------------------------------------------------------ [start] --
    const favIconElem = document.querySelector('#fav_icon');

    const isFav = () => {
        myFetch.get(`/customer/like/${iboard}`, (data) => {
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
                myFetch.post(`/customer/like`, data => {
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
                myFetch.delete(`/customer/like/${iboard}`, data => {
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
    //좋아요 ------------------------------------------------------------ [end] --
// getCommentList();
})();