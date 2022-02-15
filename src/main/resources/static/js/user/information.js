function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;

            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

const informationFrm = document.querySelector('#information-frm');
const informaionNickName = informationFrm.querySelector('#information-nickname');
const informaionName = informationFrm.querySelector('#information-nm');
const informationPost = informationFrm.querySelector('#sample6_postcode');
const informaionPrimary = informationFrm.querySelector('#sample6_address');
const informaionSecond = informationFrm.querySelector('#sample6_detailAddress');
const informationNicknameBtn = informationFrm.querySelector('#information-nickname-btn');
const informationUpdBtn = informationFrm.querySelector('#information-upd');
let flag3 = false;
informaionNickName.addEventListener('keyup', () => {
    if (informaionNickName.value.length === 0){
        informationNicknameBtn.disabled = false;
        flag3 = false;
    }
})
informationNicknameBtn.addEventListener('click', () => {
    fetch(`/user/nicknameCheck?nickname=${informaionNickName.value}`)
        .then(res => {
            return res.json();
        }).then(data => {
        console.log(informaionNickName.value)
        if (informaionNickName.value.length === 0){
            alert('닉네임 입력하는게 어때?');
            flag3 = false;
            return;
        }
        switch (data){
            case 1:
                alert('중복된 닉네임 입니다.')
                break;
            case 0:
                alert('사용 할수 있는 닉네임 입니다.')
                informationNicknameBtn.disabled = true
                flag3 = true;
                break;
        }
    })
})

informationUpdBtn.addEventListener('click', (e) =>{
    confirm('정보 변경을 하시겠습니까?')
    if (informaionName.value.length === 0 || informationPost.value.length === 0 || informaionPrimary.value.length === 0 || informaionSecond.value.length === 0){
        alert('올바른 정보를 입력하십시오.')
        e.preventDefault();
        return;
    }
    if (flag3 === false){
        alert('닉네임 중복 체크를 해주세요')
        e.preventDefault();
        return;
    }
})