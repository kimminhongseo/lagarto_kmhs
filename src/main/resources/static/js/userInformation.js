isfollow();

let reportButton = document.querySelector('#reportButton');
let fraudReport = document.querySelector('#fraudReport');
let dataIuser = document.querySelector('#dataIuser');
let floatingTextarea2 = document.querySelector('#floatingTextarea2');
if (reportButton){
    reportButton.addEventListener('click', () => {
        if(confirm('신고 하시겠습니까?')){
            fetch('/user/report', {
                method : 'post',
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify({
                    opponent : dataIuser.dataset.iuser,
                    reportNum : fraudReport.value,
                    reportContent : floatingTextarea2.value
                })
            }).then(res => {
                return res.json();
            }).then(data => {
                switch (data){
                    case 0:
                        alert('오류입니다 고객센터로 문의 주세요.');
                        break;
                    case 1:
                        alert('신고가 접수되었습니다.');
                        location.reload();
                        break;
                    case 2:
                        alert('자신을 신고 할수 없습니다.')
                        break;
                    case 3:
                        alert('신고 사유를 선택하세요')
                        break;
                }
            })
        }
    })
}