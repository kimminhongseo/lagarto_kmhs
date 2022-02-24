function asd(pageNum) {
    console.log(pageNum);
    fetch(`/user/charge?pageNum=${pageNum}`,{
        method : 'get'
    }).then(res => {
        return res.json();
    }).then(data => {

    })
}



$('#charge_kakao').click(function () {
    // getter
    var IMP = window.IMP;
    IMP.init('imp03043248');
    var money = $('#direct').val();
    console.log(money);
    if (money > 1000000){
        alert('충전금액은 최대 100만원입니다.')
        return false;
    }
    if (money < 5000){
        alert('충전금액은 최소 5천원입니다.')
        return false;
    }
    IMP.request_pay({
        pg: 'kakao',
        merchant_uid: 'merchant_' + new Date().getTime(),

        name: 'Lagarto 머니충전',
        amount: money
    }, function (rsp) {
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            fetch(`/user/charge?money=${money}`,{
                method : 'post',
                headers: {'Content-type': 'application/json'}
            }).then(res => {
                return res.json();
            })
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
        document.location.href="/user/charge?pageNum=1"; //alert창 확인 후 이동할 url 설정
    });
});

