

const iuser = document.querySelector('#cartiuser').dataset.iuser;
const tableListElem = document.querySelector('#tableList');
const tbodyElem = tableListElem.querySelector('table > tbody');

const mypointElem = document.querySelector('#mypoint');

//보유금액 < 결제 금액 이라면 결제하기 버튼 안보이게 하려고
const payBtnElem = document.querySelector('#pay');


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

             if(item.num ==1){ //1일때 -누르면 0이 되니까 삭제처리
                 if(confirm(`${item.title} 을 삭제 하시겠습니까?`)) {
                     delcart(item.iboard,trElem);
                 }
                 return false;
             }
             location.reload();
         });

         trElem.appendChild(minusBtn);
         trElem.appendChild(plusBtn);



         //잔액, 합계값
         let sumfee = 0
         for(i=0; i<list.length;i++){
             sumfee = sumfee + (list[i].price *list[i].num);
         }

         const feeElem = document.querySelector('#fee');
         feeElem.innerHTML=`<div>보유 금액: ${mypointElem.dataset.money} 원</div>
                            <div>결제 금액: ${sumfee} 원 </div>`


         let balance = mypointElem.dataset.money - sumfee;
         const balanceElem = document.querySelector('#balance');
         if(balance < 0){
             balanceElem.innerHTML='<div>잔액이 부족합니다. 충전 후 이용해 주세요.</div>'
            payBtnElem.style.visibility ="hidden";

         }else{
             balanceElem.innerHTML=`<div> 결제후 남은 캐쉬: ${balance}원</div>`
             payBtnElem.style.visibility ="visible";
         }

        //즉시구매 기능 구현

         if(payBtnElem){
             payBtnElem.addEventListener('click',()=>{
                 if(confirm('정말로 결제 하시겠습니까???')){
                     const param={
                         balance
                     }
                     console.log('잔액 업데이트~');
                     myFetch.put(`/cart1/balance`,data =>{
                         switch (data.result){
                             case 0:
                                 alert('실패');
                                 return false;
                             case 1:
                                 alert('성공');
                                 location.reload();
                         }
                     },param);
                     //결제하면 ${balance} 를 money에 update해주기.
                 }
             })
         }


     })

}




const delcart = (iboard,tr) =>{
     myFetch.delete(`/cart1/${iboard}`,data=>{
         if(data.result){
             tr.remove();
         }
         else{
             alert('상품을 삭제할 수 없습니다.');
         }
     })
}













getCartList();




