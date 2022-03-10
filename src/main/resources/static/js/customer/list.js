const recordList = document.querySelectorAll('.record');
const moveToDetail = (item) => {
    item.addEventListener('click', (e) => {
        let iboard = item.dataset.iboard;
            console.log(iboard);
            location.href = `/customer/detail?iboard=${iboard}`;
    });
};
recordList.forEach(moveToDetail);

const removeMoveToDetail = (item) => {
    item.remove();
}

function movePage(uri, queryString) {
    location.href = uri + queryString;
}


