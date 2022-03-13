{
    let btnUserElem = document.querySelector('#btnUser');
    let btnLoginElem = btnUserElem.querySelector('#btnLogin');
    let loginElem = document.querySelector('#login');
    let formId = loginElem.querySelector('#formId');
    let formPw = loginElem.querySelector('#formPw');
    let cookieValue = loginElem.querySelector('#cookie_value');
    let div = document.createElement('div');
    div.innerHTML = '올바른 이메일 형식을 적어주세요.';
    div.style.display = 'flex';
    div.style.justifyContent = 'center';
    div.style.justifyItems = 'center';
    div.style.paddingTop = '15px';
    div.style.color = 'red';

    let div2 = document.createElement('div');
    div2.innerHTML = '로그인 실패';
    div2.style.display = 'flex';
    div2.style.justifyContent = 'center';
    div2.style.justifyItems = 'center';
    div2.style.textAlign = 'center';
    div2.style.color = 'red';
    div2.style.backgroundColor = 'rgba(255,0,0,0.1)';
    div2.style.paddingTop = '30px';
    div2.style.paddingBottom = '30px';

    if (cookieValue.dataset.cookie != null) {
        loginElem.saveId.checked = true;
    }

    if (btnUserElem) {
        btnLoginElem.addEventListener('click', (e) => {
            btnLoginElem.type = 'submit';
        });
    }

    // 휴대전화 인증 페이지 (/user/certification) 이동
    let btnJoinElem = document.querySelector('#btnJoin');

    if (btnJoinElem) {
        btnJoinElem.addEventListener('click', () => {
            location.href = '/user/join';
        })
    }

    function openJoinWin() {
        window.open(
            "/user/certification",
            "_blank",
            "width=800, height=700, location=no, toolbar=no, menubar=no, scrollbars=no, resizable=yes");
    }


    let isRecaptchachecked = false;

    $('#formId').on('keyup', (k) => {
        if (k.keyCode == 13){
            dologin();
        }
    })

    $('#formPw').on('keyup', (k) => {
        if (k.keyCode == 13){
            dologin();
        }
    })

    function dologin() {
        let id = $('#formId');
        let pw = $('#formPw');
        const pid = $('#pId');
        // <input type="asd" />
        if (id.val().indexOf('@') < 1 || id.val().indexOf('.') < 1) {
            pid.append(div);
            id.focus();
            if (id.val() == 0) {
                div.remove();
            }
            return false;
        } else {
            div.remove();
        }
        if (id.val().trim() == '') {
            alert('아이디 입력하세요.');
            id.focus();
            return false;
        }
        if (pw.val().trim() == '') {
            alert('비밀번호를 입력하세요.')
            pw.focus();
            return false;
        }
        if (!isRecaptchachecked) {
            alert('인증 체크를 해주세요.');
            $("#recaptcha").focus();
            return false;
        }

        if (!loginElem.saveId.checked) {
            loginElem.saveId.value = false;
            loginElem.saveId.checked = true;
        }

        console.log("auto_id : ", loginElem.saveId.value);
        console.log("getvalue : ", loginElem.formId.value);
        return doVaildRecaptcha();
    }

    function recaptchaCallback(){// 리캡챠 체크 박스 클릭시 isRecaptchachecked 값이 true로 변경
        isRecaptchachecked = true;
    }

    function doVaildRecaptcha() {
        let captcha = 1;
        $.ajax({
            url: '/valid-recaptcha',
            type: 'post',
            data: {
                recaptcha: $("#g-recaptcha-response").val()
            },
            success: function (data) {
                switch (data) {
                    case 0:
                        console.log(data);
                        captcha = 0;
                        $.ajax({
                            url: '/user/login',
                            type: 'post',
                            data: {
                                uid: $('#formId').val(),
                                upw: $('#formPw').val(),
                                auto_id_check: $('#saveId').val()
                            },
                            success: (num) => {
                                switch (num) {
                                    case 1:
                                        location.href = '/main';
                                        break;
                                    case 2:
                                        let msg = $('#msg-login');
                                        msg.append(div2);
                                        break;
                                }
                            }
                        });
                        break;
                    case 1:
                        alert("자동 가입 방지 봇을 확인 한뒤 진행 해 주세요.");
                        break;
                    default:
                        alert("자동 가입 방지 봇을 실행 하던 중 오류가 발생 했습니다. [Error bot Code : " + Number(data) + "]");
                        break;
                }
            }
        });
        if (captcha != 0) {
            return false;
        }

        function openJoinWin() {
            window.open(
                "/user/certification",
                "_blank",
                "width=800, height=700, location=no, toolbar=no, menubar=no, scrollbars=no, resizable=yes");
        }
    }


//카카오
    {
        //카카오 초기화
        Kakao.init('26fdb40a974d81a0ceaf74ba448073cd');
        console.log(Kakao.isInitialized()); // 초기화 판단여부

        //데모버전으로 들어가서 카카오로그인 코드를 확인.
        function loginWithKakao() {
            Kakao.Auth.login({
                success: function (authObj) {
                    console.log(authObj); // access토큰 값
                    Kakao.Auth.setAccessToken(authObj.access_token); // access토큰값 저장

                    getInfo();
                },
                fail: function (err) {
                    console.log(err);
                }
            });
        }

        //액세스 토큰을 발급받고, 아래 함수를 호출시켜서 사용자 정보를 받아옴.
        function getInfo() {
            Kakao.API.request({
                url: '/v2/user/me',
                success: function (res) {
                    console.log(res);
                    // 이메일, 닉네임
                    const kakao_user_info = {
                        uid: res.kakao_account.email,
                        nm: res.kakao_account.profile.nickname
                    }
                    let url = '/user/apiLogin';
                    console.log(kakao_user_info);

                    fetch(url, {
                        method: 'POST',
                        headers: {'Content-type': 'application/json'},
                        body: JSON.stringify(kakao_user_info)
                    }).then(res => res.json())
                        .then(hh => {
                        switch (hh.result) {
                            case 0:
                                location.href = "http://localhost:8090/user/certification"
                                break;
                            case 1:
                                location.href = "http://localhost:8090/main"
                                break;
                        }
                    })
                },
                fail: function (error) {
                    alert('카카오 로그인에 실패했습니다. 관리자에게 문의하세요.' + JSON.stringify(error));
                }
            });
        }
    }


    //로그아웃 기능 - 카카오 서버에 접속하는 엑세스 토큰을 만료, 사용자 어플리케이션의 로그아웃은 따로 진행.
    function kakaoLogout() {
        if (!Kakao.Auth.getAccessToken()) {
            alert('Not logged in.');
            return;
        }
        Kakao.Auth.logout(function() {
            alert('logout ok\naccess token -> ' + Kakao.Auth.getAccessToken());
        });
    }

//
//     //네이버
//     var naver_id_login = new naver_id_login("Olu165BkwTiB1vNvozvn", "http://localhost:8090/user/callback");
//     var state = naver_id_login.getUniqState();
//     naver_id_login.setButton("white", 2,40);
//     naver_id_login.setDomain("http://localhost:8090/user/login");
//     naver_id_login.setState(state);
//     naver_id_login.setPopup();
//     naver_id_login.init_naver_id_login();
//
// }


// 구글
    {
        var googleUser = {};
        var startApp = function () {
            gapi.load('auth2', function () {
                // Retrieve the singleton for the GoogleAuth library and set up the client.
                auth2 = gapi.auth2.init({
                    client_id: '559437244447-ucbou9ltcbr3vkcvl3og41mbi9kglan4.apps.googleusercontent.com',
                    cookiepolicy: 'single_host_origin',
                    // Request scopes in addition to 'profile' and 'email'
                    //scope: 'additional_scope'
                });
                attachSignin(document.getElementById('google'));
            });
        };

        function attachSignin(element) {
            auth2.attachClickHandler(element, {},
                function (googleUser) {
                    if (googleUser) {
                        var profile = googleUser.getBasicProfile();
                        console.log('ID: ' + profile.getId());
                        console.log('Name: ' + profile.getName());
                        console.log('Image URL: ' + profile.getImageUrl());
                        console.log('Email: ' + profile.getEmail());

                        const param = {
                            'uid': profile.getEmail(),
                            'nm': profile.getName()
                        }


                        fetch('/user/apiLogin', {
                            method: 'POST',
                            headers: {"Content-Type": "application/json"},
                            body: JSON.stringify(param)
                        })
                            .then(res => res.json())
                            .then(data => {

                                switch (data.result) {
                                    case 0 :
                                        window.location.href = '/user/certification';
                                        break;
                                    case 1 :
                                        window.location.href = '/main';
                                        break;
                                }
                            })
                    }
                }, function (error) {

                });
        }

    }
}


