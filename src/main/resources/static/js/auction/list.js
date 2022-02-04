{
const recordList = document.querySelectorAll('.record');
//All 했기때문에 배열로 넘어온다.

const recordEvent = (item) => {
    item.addEventListener('click', () => {
        const iboard = item.dataset.iboard;
        console.log(iboard);
        location.href = `/auction/detail?iboard=${iboard}`;
        //  ` 템플릿 리터럴 (백틱)
    });
};
recordList.forEach(recordEvent);
}