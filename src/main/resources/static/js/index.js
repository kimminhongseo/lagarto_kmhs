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
    pw: /^([a-zA-Z0-9!@_]{4,20})$/,     //대소문자+숫자+!@_ 조합으로 4~20글자
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

