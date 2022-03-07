const recordList = document.querySelectorAll('.record');
const moveToDetail = (item) => {
    item.addEventListener('click', (e) => {
        const board_cd = item.dataset.board_cd;
        const iboard = item.dataset.iboard;
        const loginUser = item.dataset.loginUser;
        if(loginUser !== null && board_cd == 3 && loginUser !== item.iuser) {
            alert('비밀글입니다.');
            e.preventDefault();
        }
            console.log(iboard);
            location.href = `/customer/detail?iboard=${iboard}`;
    });
};
recordList.forEach(moveToDetail);

function movePage(uri, queryString) {
    location.href = uri + queryString;
}


