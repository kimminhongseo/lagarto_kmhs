{
    const certForm = document.querySelector('#cert_form');

    let profile;

    if (certForm) {
        const cancelBtn = document.querySelector('#cancel_btn');

        cancelBtn.addEventListener('click', () => {
            window.history.back();
        })

        function onSignIn() {
            var auth2 = gapi.auth2.getAuthInstance();

            profile = auth2.currentUser.get().getBasicProfile();
            console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
            console.log('Name: ' + profile.getName());
            console.log('Image URL: ' + profile.getImageUrl());
            console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.

        }

        certForm.onsubmit = () => {
            let inputs = certForm.querySelectorAll('input');
            for (let i = 0; i < inputs.length; i++) {
                let input = inputs[i];

                if (input.dataset.regex !== undefined) {
                    let regex = new RegExp(input.dataset.regex);
                    let name = input.previousElementSibling.textContent;
                    if (!regex.test(input.value)) {
                        console.log(name);
                        alert(`${name}를 다시 확인해 주세요.`);
                        input.focus();
                        return false;
                    }
                }
            }

            const param = {
                'uid': profile.getEmail(),
                'nm': profile.getName(),
                'contact_first': certForm.contact_first.value,
                'contact_second': certForm.contact_second.value,
                'contact_third': certForm.contact_third.value,
                'platform_cd' : 4
            }

            fetch('/user/apiCertification',
                {
                    method : 'POST',
                    headers : { 'Content-Type' : 'application/json' },
                    body : JSON.stringify(param)
                })
                .then(res => res.json())
                .then((data) => {
                    console.log(data);
                    switch (data.result) {
                        case 0 :
                            alert('이미 가입 된 번호입니다. 로그인 해 주세요.');
                            window.location.href = '/user/login';
                            break;
                        case 1 :
                            alert('회원가입에 성공했습니다. 로그인 해 주세요.');
                            window.location.href = '/user/login';
                            break;
                    }
                })
                .catch(err => { console.log(err) })
            return false;

        }

    }

}