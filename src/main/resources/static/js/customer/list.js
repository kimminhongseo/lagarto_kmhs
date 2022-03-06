const recordList = document.querySelectorAll('.record');
const moveToDetail = (item) => {
    item.addEventListener('click', () => {
        const iboard = item.dataset.iboard;
            console.log(iboard);
            location.href = `/customer/detail?iboard=${iboard}`;
    });
};
recordList.forEach(moveToDetail);

function movePage(uri, queryString) {
    location.href = uri + queryString;
}
