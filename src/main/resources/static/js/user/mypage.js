{
    const passswordFrmElem = document.querySelector('#password-frm');
    const dataElem = passswordFrmElem.querySelector('#data');
    const passwordUpwElem = passswordFrmElem.querySelector('#password-current-input');
    const passwordNewUpwElem = passswordFrmElem.querySelector('#password-upd-input');
    const passwordChkUpwElem = passswordFrmElem.querySelector('#password-chk-input');
    const passwordSmtElem = passswordFrmElem.querySelector('#password-smt');

    if (passswordFrmElem){
        passwordSmtElem.addEventListener('click', (e) => {
            fetch('/user/passwordCurrent', {
                method: 'post',
                headers: {'Content-type': 'application/json'},
                body: JSON.stringify({
                    'iuser' : dataElem.dataset.iuser,
                    'upw' : passwordUpwElem.value,
                    'newUpw' : passwordNewUpwElem.value
                })
            }).then(res => {
                return res.json();
            }).then(data => {
                console.log(data);
                switch (data.result){
                    case 0:
                        alert('변경실패')
                        e.preventDefault();
                        break;
                    case 1:
                        if (confirm('비밀번호를 변경 하시겠습니까?')){
                            alert('변경완료');
                            location.href;
                        }

                }
            })
        })
    }
    console.log(passwordUpwElem);
}