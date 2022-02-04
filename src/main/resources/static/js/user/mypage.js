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
    const div2 = document.createElement('div');

    if (passswordFrmElem){
        passwordNewUpwElem.addEventListener('keyup', e => {
            const errpasswordCHKElem = paswordCHk.querySelector('#errpasswordCHK');
            const newerrpasswordCHKElem = paswordCHk.querySelector('#newerrpasswordCHK');
            div.id = 'errpasswordCHK';
            div2.id = 'newerrpasswordCHK';
            if (passwordChkUpwElem.value !== passwordNewUpwElem.value){
                if (errpasswordCHKElem == null){
                    div.innerHTML=`비밀번호가 일치하지 않습니다`;
                    paswordCHk.appendChild(div);
                    if (newerrpasswordCHKElem !=null){
                        newerrpasswordCHKElem.remove();
                    }
                }
            }else if (passwordChkUpwElem.value.length === 0 && passwordNewUpwElem.value.length === 0 ){
                if (errpasswordCHKElem != null || newerrpasswordCHKElem != null){
                    errpasswordCHKElem.remove();
                }
            }else{
                if (newerrpasswordCHKElem == null){
                    div2.innerHTML=`비밀번호가 일치합니다`;
                    paswordCHk.appendChild(div2)
                    if (errpasswordCHKElem != null){
                        errpasswordCHKElem.remove();
                    }
                }
            }
        });

        passwordChkUpwElem.addEventListener('keyup', (e) => {
            div.id = 'errpasswordCHK';
            const errpasswordCHKElem = paswordCHk.querySelector('#errpasswordCHK');
            const newerrpasswordCHKElem = paswordCHk.querySelector('#newerrpasswordCHK');
            div2.id = 'newerrpasswordCHK';

            console.log(passwordNewUpwElem.value.length);
            console.log(passwordChkUpwElem.value.length);
            if (passwordChkUpwElem.value !== passwordNewUpwElem.value){
                if (errpasswordCHKElem == null){
                    div.innerHTML=`비밀번호가 일치하지 않습니다`;
                    paswordCHk.appendChild(div);
                    if (newerrpasswordCHKElem !=null){
                        newerrpasswordCHKElem.remove();
                    }
                }
            }else if (passwordChkUpwElem.value.length === 0 && passwordNewUpwElem.value.length === 0 ){
                if (errpasswordCHKElem != null || newerrpasswordCHKElem != null){
                    errpasswordCHKElem.remove();
                }
            }else{
                if (newerrpasswordCHKElem == null){
                    div2.innerHTML=`비밀번호가 일치합니다`;
                    paswordCHk.appendChild(div2)
                    if (errpasswordCHKElem != null){
                        errpasswordCHKElem.remove();
                    }
                }
            }
        });
    }



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
                            location.href="/user/logout";
                        }

                }
            })
        })
    }
    console.log(passwordUpwElem);
}