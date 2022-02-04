{
    const certForm = document.querySelector('#cert_form');

    if (certForm) {
        certForm.addEventListener('submit', (e) => {
            const param = {
                'contact_first' : certForm['contact_first'].value(),
                'contact_second' : certForm['contact_second'].value(),
                'contact_third' : certForm['contact_third'].value()
            }

            fetch("/user/certification",
                {
                    method : "POST",
                    headers : { "Content-Type" : "application/json" },
                    body : JSON.stringify(param)
                })
                .then(res => res.json())
                .then((data) => {
                    console.log(data);
                    switch (data.result) {
                        case 0 :
                            console.log('result - 0');
                            alert('이미 가입 된 번호입니다. 로그인 해 주세요.');
                            closeWindow();
                            break;
                        case 1 :
                            console.log('result - 1');
                            window.close();
                            window.location('/user/join');
                            break;
                    }
                })
                .catch(err => { console.log(err) })
            e.preventDefault();
        })
    }

    const cancelBtn = certForm.querySelector('#cancelBtn');

    if (cancelBtn) {
        cancelBtn.addEventListener('click', () => {
            window.close();
        })
    }

    function closeWindow() {
        setTimeout(function() {
            window.close();
        }, 2000);
    }
}