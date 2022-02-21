$('#charge_kakao').click(function () {
    // getter
    var IMP = window.IMP;
    IMP.init('imp03043248');
    var money = $('input[name="cp_item"]:checked').val();
    console.log(money);

    IMP.request_pay({
        pg: 'kakao',
        merchant_uid: 'merchant_' + new Date().getTime(),

        name: 'Lagarto 머니충전',
        amount: money,
        buyer_email: 'iamport@siot.do',
        buyer_name: '구매자이름',
        buyer_tel: '010-1234-5678',
        buyer_addr: '인천광역시 부평구',
        buyer_postcode: '123-456'
    }, function (rsp) {
        console.log(money);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '고유ID : ' + rsp.imp_uid;
            msg += '상점 거래ID : ' + rsp.merchant_uid;
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
        document.location.href="/user/charge"; //alert창 확인 후 이동할 url 설정
    });
});