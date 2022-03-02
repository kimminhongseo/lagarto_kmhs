let moneyTbodyElem = document.querySelector('#money-tbody');
let num = 1;
function asd(pageNum) {
    fetch('/user/moneyChargeList',{
        method : 'post',
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify({
            currentPage : pageNum
        })
    }).then(res => {
        return res.json();
    }).then(data => {
        console.log(data.length)
        moneyTbodyElem.innerHTML = "";
        for (let i = 0; i < data.length; i++){
            let tr = document.createElement('tr');
            let td1 = document.createElement('td');
            td1.innerText = data[i].rdt.substring(0,11);
            let td2 = document.createElement('td');
            td2.innerText = data[i].money+'원';
            let td3 = document.createElement('td');
            td3.innerText = '충전완료';
            moneyTbodyElem.appendChild(tr);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
        }
    })
}

asd(1);

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
        document.location.href="/user/charge"; //alert창 확인 후 이동할 url 설정
    });
});

