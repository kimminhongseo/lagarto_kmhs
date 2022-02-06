// {
//     const recordList = document.querySelectorAll('.record');
// //All 했기때문에 배열로 넘어온다.
//
//     const recordEvent = (item) => {
//         item.addEventListener('click', () => {
//             const iboard = `${item.iboard}`;
//             console.log(iboard);
//             location.href = `/auction/detail?iboard=${iboard}`;
//             //  ` 템플릿 리터럴 (백틱)
//         });
//     };
//     recordList.forEach(recordEvent);
// }

//
// (function (){
//     'use strict';
//
//     //리스트 정보
// const auctionConElem = document.querySelector('.auction_container');
//
//
//     const getList  = () =>{
//         myFetch.get('/auction/list', list => {
//             console.log(list);
//             makeList(list);
//         });
//     }
//
//     const makeList = list =>{
//         const listElem = auctionConElem.querySelector('ul li');
//
//         list.forEach(item => {
//             const divElem = document.createElement('div');
//             listElem.appendChild(divElem);
//
//             //추가
//             divElem.innerHTML=`
//                 <div>게시판 번호 : ${item.iboard}</div>
//                 <div>이미지 : ${item.images}</div>
//                 <div>조회수 : ${item.hits}</div>
//                 <div>
//                     <div>제목 : ${item.title}</div>
//                     <div>내용 : ${item.ctnt}</div>
//                 </div>
//                 <div>
//                     <div>즉시구매가 : ${item.imbuy}</div>
//                     <div>경매가 : ${item.buy}</div>
//                 </div>
//
//             `;
//             divElem.addEventListener('click', e=>{
//               location.href=`/auction/detail?iboard=${item.iboard}`;
//             });
//         });
//     }
//     getList();
//
//
// })();


// {
//
//     const recordList = document.querySelectorAll('.record');
// //All 했기때문에 배열로 넘어온다.
//
//     const recordEvent = (item) => {
//         item.addEventListener('click', () => {
//             location.href = `/auction/detail?iboard=${data.iboard}`;
//             //  ` 템플릿 리터럴 (백틱)
//         });
//     };
//
//
// }

