{
    const passswordFrmElem = document.querySelector('#password-frm');
    const dataElem = passswordFrmElem.querySelector('#data');
    const paswordUPD = passswordFrmElem.querySelector('#paswordUPD');
    const paswordCHk = passswordFrmElem.querySelector('#paswordCHk');
    const passwordUpwElem = passswordFrmElem.querySelector('#password-current-input');
    const passwordNewUpwElem = passswordFrmElem.querySelector('#password-upd-input');
    const passwordChkUpwElem = passswordFrmElem.querySelector('#password-chk-input');
    const passwordSmtElem = passswordFrmElem.querySelector('#password-smt');
    const div = document.createElement('div');
    div.innerHTML = `비밀번호가 일치하지 않습니다`;
    div.id = 'errpasswordCHK';
    div.style.color = 'red';
    div.style.display = 'relative';
    div.style.paddingLeft = '125px'

    const div2 = document.createElement('div');
    div2.innerHTML = `비밀번호가 일치합니다`;
    div2.id = 'newerrpasswordCHK';
    div2.style.color = 'green';
    div2.style.display = 'relative';
    div2.style.paddingLeft = '125px'

    const div3 = document.createElement('div');
    div3.innerHTML = `숫자, 영어, 특수문자를 합성하여 8자리 이상 입력해주세요`;
    div3.id = 'regexPasswordF';
    div3.style.color = 'red';
    div3.style.display = 'relative';
    div3.style.paddingLeft = '125px'

    const div4 = document.createElement('div');
    div4.innerHTML = `변경비밀번호 사용가능`;
    div4.id = 'regexPasswordS';
    div4.style.color = 'green';
    div4.style.display = 'relative';
    div4.style.paddingLeft = '125px'

    let flag = false;
    if (passswordFrmElem) {

        passwordNewUpwElem.addEventListener('keyup', e => {
            const errpasswordCHKElem = paswordCHk.querySelector('#errpasswordCHK');
            const newerrpasswordCHKElem = paswordCHk.querySelector('#newerrpasswordCHK');
            const regexPasswordSElem = paswordUPD.querySelector('#regexPasswordS');
            const regexPasswordFElem = paswordUPD.querySelector('#regexPasswordF');
            if (regex.pw.test(passwordNewUpwElem.value)) {
                if (regexPasswordFElem != null) {
                    regexPasswordFElem.remove();
                }
                paswordUPD.appendChild(div4);
            } else if (passwordNewUpwElem.value.length === 0) {
                if (regexPasswordSElem != null) {
                    regexPasswordSElem.remove();
                }
                if (regexPasswordFElem != null) {
                    regexPasswordFElem.remove();
                }
            } else {
                if (regexPasswordSElem != null) {
                    regexPasswordSElem.remove();
                }
                paswordUPD.appendChild(div3);
            }
            if (passwordChkUpwElem.value !== passwordNewUpwElem.value) {
                if (errpasswordCHKElem == null) {
                    paswordCHk.appendChild(div);
                    if (newerrpasswordCHKElem != null) {
                        newerrpasswordCHKElem.remove();
                    }
                }
                flag = false;
            } else if (passwordChkUpwElem.value.length === 0 && passwordNewUpwElem.value.length === 0) {
                if (errpasswordCHKElem != null || newerrpasswordCHKElem != null) {
                    errpasswordCHKElem.remove();
                }
            } else {
                if (newerrpasswordCHKElem == null) {
                    paswordCHk.appendChild(div2)
                    if (errpasswordCHKElem != null) {
                        errpasswordCHKElem.remove();
                    }
                }
                flag = true;
            }
        });

        passwordChkUpwElem.addEventListener('keyup', (e) => {
            const errpasswordCHKElem = paswordCHk.querySelector('#errpasswordCHK');
            const newerrpasswordCHKElem = paswordCHk.querySelector('#newerrpasswordCHK');
            const regexPasswordFElem = paswordUPD.querySelector('#regexPasswordF');
            const regexPasswordSElem = paswordUPD.querySelector('#regexPasswordS');
            if (passwordChkUpwElem.value !== passwordNewUpwElem.value) {
                if (errpasswordCHKElem == null) {
                    paswordCHk.appendChild(div);
                    if (newerrpasswordCHKElem != null) {
                        newerrpasswordCHKElem.remove();
                    }
                    flag = false;
                }
            } else if (passwordChkUpwElem.value.length === 0 && passwordNewUpwElem.value.length === 0) {
                if (errpasswordCHKElem != null || newerrpasswordCHKElem != null) {
                    errpasswordCHKElem.remove();
                }
            } else {
                if (newerrpasswordCHKElem == null) {
                    paswordCHk.appendChild(div2)
                    if (errpasswordCHKElem != null) {
                        errpasswordCHKElem.remove();
                    }
                }
                flag = true;
                if (regexPasswordFElem != null) {
                    flag = false;
                }
            }
            console.log(flag);
        });
    }


    if (passswordFrmElem) {
        passwordSmtElem.addEventListener('click', (e) => {
            if (passwordUpwElem.value.length === 0) {
                alert('현재비밀번호를 입력해주세요.');
                return;
            } else if (passwordNewUpwElem.value.length === 0) {
                alert('변경비밀번호를 입력해주세요.');
                return;
            } else if (passwordChkUpwElem.value.length === 0) {
                alert('확인비밀번호를 입력해주세요.');
                return;
            }
            if (!flag) {
                alert("변경비밀번호와 체크비밀번호를 확인해 주세요.")
                return;
            }

            if (confirm('비밀번호를 변경 하시겠습니까?')) {
                fetch('/user/passwordCurrent', {
                    method: 'post',
                    headers: {'Content-type': 'application/json'},
                    body: JSON.stringify({
                        'iuser': dataElem.dataset.iuser,
                        'upw': passwordUpwElem.value,
                        'newUpw': passwordNewUpwElem.value
                    })
                }).then(res => {
                    return res.json();
                }).then(data => {
                    console.log(data.result);
                    switch (data.result) {
                        case 0:
                            alert('현재비밀번호가 일치하지 않습니다.')
                            e.preventDefault();
                            break;
                        case 1:
                            alert('변경완료');
                            location.href = "/user/logout";
                    }
                })
            }
        });
    }
    // console.log(passwordUpwElem);

    const emailModal = document.querySelector('#email-modal');
    const formEmail = emailModal.querySelector('#formEmail');
    const formEmailsend = emailModal.querySelector('#formEmail-send');
    const formEmailNumber = emailModal.querySelector('#formEmail_Number');
    const formEmailSubmit = emailModal.querySelector('#formEmail_submit');
    const timer = emailModal.querySelector('#timer');
    let authKey = 0;
    timer.style.color = 'red';
    let flag2 = true;
    if (emailModal) {
        formEmailsend.addEventListener('click', (e) => {
            if (confirm("인증 메일을 보내시겠습니까?")) {
                let SetTime = 10;      // 최초 설정 시간(기본 : 초)
                let min = "";
                let sec = "";
                flag2 = true;
                alert('인증번호를 발송 했습니다.')
                let x = setInterval(function () {
                    min = parseInt(SetTime / 60);
                    sec = SetTime % 60;
                    document.getElementById("timer").innerHTML = min + "분" + sec + "초";
                    SetTime--;
                    // formEmailsend.disabled = 'true';
                    //타임아웃 시
                    if (SetTime < 0) {
                        flag2 = false;
                        clearInterval(x); // 타이머 종료하는 함수
                        document.getElemenltById("timer").innerHTML = "인증만료";
                    }
                }, 1000); // 1초마다

                fetch(`/ajax/mail?uid=${formEmail.value}`)
                    .then(res => {
                        // return res.json();
                    }).then(data => { // data = 메일로 발송된 인증키
                    console.log(data);
                    authKey = data;
                    switch (data) {
                        case 'null':
                            alert('알 수 없는 오류가 발생했습니다')
                            break;
                    }
                })
            }
        });

        formEmailSubmit.addEventListener('click', (e) => {
            console.log(flag2)
            if (!flag2) {
                alert('제한시간 만료');
                return;
            }
            if (authKey != formEmailNumber.value) {
                alert('인증번호 불일치')
                return;
            }
            fetch(`/ajax/authkey?authKey=${authKey}`)
                .then(res => {
                    return res.json();
                }).then(data => {
                switch (data) {
                    case 0:
                        alert('인증번호 전송을 해주세요');
                        break;
                    case 1:
                        alert('인증번호 일치');
                        location.reload();
                        break;
                    case 2:
                        alert("알 수 없는 오류입니다.")
                        location.href = "/user/logout";
                        break;
                }
            })
        })
    }

    let information = document.querySelector('#information-button');

    if (information) {
        information.addEventListener('click', () => {
            location.href = 'http://localhost:8090/user/information';
        })
    }
}


const unfollowBtnElem = document.querySelectorAll('.unfollow-Btn');
if (unfollowBtnElem){
    unfollowBtnElem.forEach(item => {
        console.log(item.value);
        item.addEventListener('click', () => {
            const myFElem = document.querySelector('.myF');
            fetch(`/unfollow/${item.value}`,{
                method : 'DELETE'
            }).then(res => {
                return res.text();
            }).then(data => {
                console.log(data);
                myFElem.remove();
            })
        });
    })
}

