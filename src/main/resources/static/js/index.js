const msg = {
    isDel: '삭제하시겠습니까?',
    fnIsDel : function(target) {
        return `${target}을(를) ` + this.isDel;
    }
};

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

$('#follow-btn').on('click', function() {
    follow(true);
});

$('#unfollow-btn').on('click', function() {
    follow(false);
});

function follow(check) {
    if(check) {
        $.ajax({
            type: "POST",
            url: `/follow/${user.uid}`,
            headers: {
                "Content-Type": "application/json",
                "X-HTTP-Method-Override": "POST"
            },
            success: function(result) {
                console.log("result : " + result);
                if(result === "FollowOK"){
                    $(".follow").html('<button class="followBtn" id="unfollow-btn">언팔로우</button>');
                    location.href="/myapp/post/${user.id}";
                }
            }
        });
    } else {
        $.ajax({
            type: "POST",
            url: `/unfollow/${user.id}`,
            headers: {
                "Content-Type": "application/json",
                "X-HTTP-Method-Override": "POST"
            },
            success: function(result) {
                console.log("result : " + result);
                if(result === "UnFollowOK"){
                    $(".follow").html('<button class="followBtn" id="follow-btn">팔로우</button>');
                    location.href="/myapp/post/${user.id}";
                }
            }
        });
    }
}