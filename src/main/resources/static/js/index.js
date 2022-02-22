const msg = {
    isDel: '삭제하시겠습니까?',
    fnIsDel : function(target) {
        return `${target}을(를) ` + this.isDel;
    }
};
// ---------------------------------------------------------------------------------------------
//정규식 테스트 사이트
//https://www.regextester.com/

const regex = {
    id: /^([a-zA-Z0-9]{4,15})$/,        //대소문자_숫자조합으로 4~15글자
    //숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 8자리 이상 입력
    pw : /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/,
    nm: /^([가-힣]{2,5})$/,             //한글조합으로 2~5글자
    ctnt: /^[^><]*$/,
    msg: {
        id: '대소문자_숫자조합으로 4~15글자',
        pw: '대소문자+숫자+!@_ 조합으로 4~20글자',
        nm: '한글조합으로 2~5글자',
        ctnt: '<, >는 사용할 수 없습니다.',
    },
    isWrongWith: function(target, val) {
        return (target && val) ? !this[target].test(val) : true;
    }
};
// ---------------------------------------------------------------------------------------------

const dataIuserElem = document.querySelector('#dataIuser');
const isfollowElem = document.querySelector('#isfollow');

// ---------------------------------------------------------------------------------------------

const myFetch = {
    send: function(fetchObj, cb) {
        return fetchObj
            .then(res => res.json())
            .then(cb)
            .catch(e => { console.log(e) });
    },
    get: function(url, cb, param) {
        if(param) {
            const queryString = '?' + Object.keys(param).map(key => `${key}=${param[key]}`).join('&');
            url += queryString;
        }
        return this.send(fetch(url), cb);
    },
    post: function(url, cb, param) {
        return this.send(fetch(url, {
            'method': 'post',
            'headers': { 'Content-Type': 'application/json' },
            'body': JSON.stringify(param)
        }), cb);
    },
    put: function(url, cb, param) {
        return this.send(fetch(url, {
            'method': 'put',
            'headers': { 'Content-Type': 'application/json' },
            'body': JSON.stringify(param)
        }), cb)
    },
    delete: function(url, cb) {
        return this.send(fetch(url, {
            'method': 'delete',
            'headers': { 'Content-Type': 'application/json' },
        }), cb);
    }
}
// ---------------------------------------------------------------------------------------------

let isfollow = () => fetch(`/isfollow/${dataIuserElem.dataset.iuser}`).then(res => {
    return res.json();
}).then(data => {
    switch (data) {
        case 0:
            isfollowElem.innerHTML = `<button id="follow-Btn">팔로우</button>`;
            break;
        case 1:
            isfollowElem.innerHTML = `<button id="unfollow-Btn">언팔로우</button>`;
            break;
        case 2:
            break;
    }
    const followBtnElem = isfollowElem.querySelector('#follow-Btn');
    if (followBtnElem){
        followBtnElem.addEventListener('click', () => {
            console.log(dataIuserElem.dataset.iuser);
            fetch(`/follow/${dataIuserElem.dataset.iuser}`, {
                method : 'post',
                headers: {'Content-type': 'application/json'}
            }).then(res => {
                return res.json();
            }).then(data => {
                switch (data){
                    case 0:
                        location.href='http://localhost:8090/user/login';
                        break;
                    case 2:
                        alert('자신에게 팔로우는 불가능 합니다');
                        break;
                    case 1:
                        followBtnElem.remove();
                        isfollow();
                        break;
                }
            })
        })
    }
    const unfollowBtnElem = isfollowElem.querySelector('#unfollow-Btn');
    if (unfollowBtnElem){
        unfollowBtnElem.addEventListener('click', () => {
            fetch(`/unfollow/${dataIuserElem.dataset.iuser}`,{
                method : 'DELETE'
            }).then(res => {
                return res.text();
            }).then(data => {
                console.log(data);
                unfollowBtnElem.remove();
                isfollow();

            })
        })
    }
})
isfollow();
// ---------------------------------------------------------------------------------------------
