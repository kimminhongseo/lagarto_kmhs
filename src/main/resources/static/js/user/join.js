{
    const joinForm = document.querySelector('#join_form');


    if (joinForm) {
        const setUidChkMsg = (data) => {
            const emailWarning = joinForm.querySelector('[rel="email-warning"]');
            idChkState = data.result;

            switch (data.result) {
                case 0:
                    emailWarning.innerText = '사용할 수 있는 아이디 입니다.';
                    break;
                case 1:
                    emailWarning.innerText = '이미 사용중인 아이디 입니다.';
                    break;
            }
        }

        // email 중복 체크
        joinForm['uid'].addEventListener('focusout', (e) => {
            e.preventDefault();

            const uidRegex = joinForm['uid'].dataset.regex;
            const uidVal = joinForm['uid'].value
            const emailWarning = joinForm.querySelector('[rel="email-warning"]');
            if (uidRegex !== undefined) {
                let regex = new RegExp(uidRegex);
                if (!regex.test(uidVal)) {
                    emailWarning.innerText = '이메일을 다시 확인해 주세요.';
                    return false;
                }
            }

            const param = {
                'uid' : uidVal
            }
            fetch('/user/uidChk', {
                method: 'POST',
                headers: { "Content-Type" : "application/json" },
                body: JSON.stringify(param)
            })
                .then (res => res.json())
                .then (data => {
                    console.log(data.result);
                    setUidChkMsg(data);
                })

        })


        // 약관 동의 전체 체크
        const discAgreeAll = document.querySelector('#disc_agree_all');
        const discAgreeA = document.querySelector('#disc_agree_a');
        const discAgreeB = document.querySelector('#disc_agree_b');
        const discAgreeC = document.querySelector('#disc_agree_c');

        joinForm['disc_agree_all'].addEventListener('change', (e) => {
            joinForm['disc_agree_a'].checked = e.currentTarget.checked;
            joinForm['disc_agree_b'].checked = e.currentTarget.checked;
            joinForm['disc_agree_c'].checked = e.currentTarget.checked;
        });


        // form submit
        joinForm.onsubmit = () => {
            // input 값 정규식 체크
            let inputs = joinForm.querySelectorAll('input');
            for (let i = 0; i < inputs.length; i++) {
                let input = inputs[i];

                if (input.dataset.regex !== undefined) {
                    let regex = new RegExp(input.dataset.regex);
                    let name = input.previousElementSibling.textContent;
                    if (!regex.test(input.value)) {
                        console.log(name);
                        alert(`${name}을(를) 다시 확인해 주세요.`);
                        input.focus();
                        return false;
                    }
                }
            }


            // 잘못된 값 확인
            if (idChkState !== 0) {
                alert('사용할 수 없는 아이디 입니다. 다른 아이디를 사용해 주세요.');
                return false;
            }

            if (joinForm['upw'].value !== joinForm['upw_check'].value) {
                alert('비밀번호가 서로 일치하지 않습니다.');
                joinForm['upw_check'].focus();
                return false;
            }

            if (!joinForm['disc_agree_a'].checked) {
                alert('이용약관을 읽고 동의해 주세요.');
                return false;
            }

            if (!joinForm['disc_agree_b'].checked) {
                alert('개인정보 수집 및 이용을 읽고 동의해 주세요.');
                return false;
            }


            // 전화번호 중복 체크
            const param = {
                'contact_first' : joinForm['contact_first'].value,
                'contact_second' : joinForm['contact_second'].value,
                'contact_third' : joinForm['contact_third'].value
            }

            fetch("/user/contChk",
                {
                    method : "POST",
                    headers : { "Content-Type" : "application/json" },
                    body : JSON.stringify(param)
                })
                .then(res => res.json())
                .then((data) => {
                    console.log(data);
                    if (data.result !== 0) {
                        switch (data.result) {
                            case 1 :
                                console.log('result - 1');
                                alert('이미 가입 된 번호입니다. 로그인 해 주세요.');
                                window.location.href('/user/login');
                                return false;
                        }
                    }

                })
                .catch(e => { console.log(e) })

            return true;
        }
        
    }





}