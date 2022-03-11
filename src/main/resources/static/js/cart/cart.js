
<!--

-->


/*
// 총 몇개의 상품 있는지?
const rows = document.getElementById("cartdata").getElementsByTagName('tr');
console.log(" 총 몇개인가 ? "+rows.length);


const dataElem = document.querySelector('#cartdata');
console.log("dataElem")
console.log(dataElem);



    for(i=0; i<rows.length; i++){
        dataElem.children[i].id= i;
       dataElem.children[i].children[3].children[0].id='num'+i;
        console.log(dataElem.children[i].children[2].textContent);
    }
let selectElem = document.querySelector('#num1');
console.log(selectElem);



//select 박스 값 가져오기

$("select[name=num]").change(function(){
    console.log($(this).val()); //value값 가져오기
});


//요금
const feeElem = document.querySelector('#fee');




//불러오는거

 */


//restcontroller로 다시해보자



const iuser = document.querySelector('#cartiuser').dataset.iuser;
const tableListElem = document.querySelector('#tableList');
const tbodyElem = tableListElem.querySelector('table > tbody');

const mypointElem = document.querySelector('#mypoint');


 const getCartList = () =>{
     myFetch.get('/cart1/list', list=>{
         selCartList(list);
     },iuser);
 }



const selCartList = list =>{
     list.forEach(item =>{
         const trElem = document.createElement('tr');
         trElem.innerHTML = `
                 <td>${item.iboard}</td>
                <td>${item.title}</td>
                <td>${item.price}</td>
                 <td>${item.num}</td>
            `;

       tbodyElem.appendChild(trElem);


         const plusBtn = document.createElement('input');
         plusBtn.type = 'button';
         plusBtn.value = '+';

         plusBtn.addEventListener('click',()=>{
             const tdArr =trElem.querySelectorAll('td');
             let tdcell = tdArr[3]  // 숫자
             tdcell.value = tdArr[3].textContent;
             console.log(tdcell);
             console.log(tdcell.value);
             console.log(list.length);

             const param = {
                 iboard:item.iboard
             }
             myFetch.put('/cart1/plus',data=>{
                switch (data.result){
                    case 0:
                        alert('실패');
                        break;
                    case 1:

                        break;
                }
             },param);
             location.reload();
         });


         const minusBtn = document.createElement('input');
         minusBtn.type='button';
         minusBtn.value='-';
         minusBtn.addEventListener('click',()=>{
             const param = {
                 iboard:item.iboard
             }
             myFetch.put('/cart1/minus',data=>{
                 switch (data.result){
                     case 0:
                         alert('실패');
                         break;
                     case 1:
                         break;
                 }
             },param);

             location.reload();
         });

         trElem.appendChild(minusBtn);
         trElem.appendChild(plusBtn);

         //장바구니 오면 개수 1개니까 가격이랑 곱해서 전체 가격 계산.
         let sumfee = 0
         for(i=0; i<list.length;i++){
             sumfee = sumfee + (list[i].price *list[i].num);
         }
         //plus 함수 // minus 함수.
         //sumfee에 각각 리턴값 해보기
         const feeElem = document.querySelector('#fee');
         feeElem.innerHTML=`<div>보유 금액: ${mypointElem.dataset.money} 원</div>
                            <div>결제 금액: ${sumfee} 원 </div>`


         let balance = mypointElem.dataset.money - sumfee;
         const balanceElem = document.querySelector('#balance');
         balanceElem.innerHTML=`<div> 결제후 남은 캐쉬: ${balance}원</div>`

     })



 }



//
// $("select[name=num]").change(function(){
//     console.log($(this).val());//value값 가져오기
//     var numValue = $(this).val();});

getCartList();




