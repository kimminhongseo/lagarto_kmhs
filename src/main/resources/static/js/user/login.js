


{
    let btnUserElem = document.querySelector('#btnUser');
    let btnLoginElem = btnUserElem.querySelector('#btnLogin');
    let loginElem = document.querySelector('#login');
    let formId = loginElem.querySelector('#formId');
    let formPw = loginElem.querySelector('#formPw');


    if (btnLoginElem){
        btnLoginElem.disabled = 'disabled';
        formId.addEventListener('keyup', () =>{
                formPw.addEventListener('keyup', () =>{
                    if (formId.value !== '' || formPw.value !== ''){
                        btnLoginElem.disabled = false;
                    }
                });
        });
        formPw.addEventListener('keyup', () =>{
        formId.addEventListener('keyup', () =>{
                if (formId.value !== '' || formPw.value !== ''){
                    btnLoginElem.disabled = false;
                }
            });
        });

    }

    if (btnUserElem){
        btnLoginElem.addEventListener('click', (e) =>{
            btnLoginElem.type = 'submit';
        });
    }

    // 휴대전화 인증 페이지 (/user/certification) 이동
    let btnJoinElem = document.querySelector('#btnJoin');

    if (btnJoinElem) {
        btnJoinElem.addEventListener('click', () => {
            location.href='/user/join';
        })
    }

    function openJoinWin() {
        window.open(
            "/user/certification",
            "_blank",
            "width=800, height=700, location=no, toolbar=no, menubar=no, scrollbars=no, resizable=yes");
    }


    //기존 로그인 상태를 가져오기 위해 Facebook에 대한 호출
    function statusChangeCallback(res) {
        statusChangeCallback(response);
    }


    function fnFbCustomLogin() {
        FB.login(function (response) {
            if (response.status === 'connected') {
                FB.api('/me', 'get', {fields: 'name,email'}, function (r) {
                    let url = '/user/apiLogin';
                    console.log(r);

                    fetch(url, {
                        method: 'post',
                        headers: {'Content-type': 'application/json'},
                        body: JSON.stringify(r)
                    }).then(function (res) {
                        return res.json();
                    }).then(hh => {
                        switch (hh){
                            case 1:
                                location.href = "http://localhost:8090/user/certification"
                                break;
                            case 0:
                                location.href = "http://localhost:8090/page/main"
                                break;
                        }
                    });
                })
            } else if (response.status === 'not_authorized') {
                // 사람은 Facebook에 로그인했지만 앱에는 로그인하지 않았습니다.
                alert('앱에 로그인해야 이용가능한 기능입니다.');
            } else {
                // 그 사람은 Facebook에 로그인하지 않았으므로이 앱에 로그인했는지 여부는 확실하지 않습니다.
                alert('페이스북에 로그인해야 이용가능한 기능입니다.');
            }
        }, {scope: 'public_profile,email'});
    }

    window.fbAsyncInit = function () {
        FB.init({
            appId      : '612308656721361', // 내 앱 ID를 입력한다.
            cookie     : true,
            xfbml      : true,
            version    : 'v12.0'
        });
        FB.AppEvents.logPageView();
    };

}
//카카오
{
    //카카오 초기화
    Kakao.init('26fdb40a974d81a0ceaf74ba448073cd');
    console.log( Kakao.isInitialized() ); // 초기화 판단여부

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
                    method : 'post',
                    headers : {'Content-type' : 'application/json'},
                    body : JSON.stringify(kakao_user_info)
                }).then(function (res){
                    return res.json();
                }).then(hh => {
                    switch (hh) {
                        case 1:
                            location.href = "http://localhost:8090/user/certification"
                            break;
                        case 0:
                            location.href = "http://localhost:8090/page/main"
                            break;
                    }
                })
            },
            fail: function (error) {
                alert('카카오 로그인에 실패했습니다. 관리자에게 문의하세요.' + JSON.stringify(error));
            }
        });
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


    //네이버
    var naver_id_login = new naver_id_login("Olu165BkwTiB1vNvozvn", "http://localhost:8090/user/callback");
    var state = naver_id_login.getUniqState();
    naver_id_login.setButton("white", 2,40);
    naver_id_login.setDomain("http://localhost:8090/user/login");
    naver_id_login.setState(state);
    naver_id_login.setPopup();
    naver_id_login.init_naver_id_login();

}