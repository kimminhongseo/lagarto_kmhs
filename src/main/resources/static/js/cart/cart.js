
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


//갯수
const rows = document.getElementById("cartdata").getElementsByTagName('tr');
console.log("장바구니에 담긴 항목 갯수 : "+rows.length);


 const getCartList = () =>{
     myFetch.get('/cart1/list', list=>{
         selCartList(list);
     },{iuser});
 }



//cart 갯수 올리기 함수
function fnCalCount(type, ths){
    var $input = $(ths).parents("td").find("input");
    var tCount = Number($input.val());
    var tEqCount = 10;

    if(type=='p'){
        if(tCount < tEqCount) $input.val(Number(tCount)+1);
        //fetch로 p 면 putmapping update num  = num+1


        myFetch.put('/cart1/plus',data=>{
            switch (data.result){
                case 0:
                    alert('작동 안됨');
                    break;
                case 1:
                    alert('1증가?');
                    break;
            }
        });

    }else{
        if(tCount >0) $input.val(Number(tCount)-1);
    }
}



const selCartList = list =>{
     list.forEach(item =>{
         const trElem = document.createElement('tr');
         trElem.innerHTML = `
                 <td>${item.iboard}</td>
                <td>${item.title}</td>
                <td>${item.price}</td>
                 <td>
                      <button class="minus"  onclick="fnCalCount('m',this);">-</button>
                      <input class="resultnum" value="${item.num}" readonly>
                      <button class="plus" onclick="fnCalCount('p', this);">+</button>
                   </td>
            `;

      console.log(document.getElementsByClassName("input[class=ressultnum]"))


       tbodyElem.appendChild(trElem);
       console.log(tbodyElem);



     })


     console.log(document.getElementsByTagName("input"));





     //장바구니 오면 개수 1개니까 가격이랑 곱해서 전체 가격 계산.
     let sumfee = 0
     for(i=0; i<rows.length;i++){
        sumfee = sumfee + (list[i].price);
        console.log(list[i].iboard); // 상품 번호들.

     }


     //plus 함수 // minus 함수.
     //sumfee에 각각 리턴값 해보기

     const feeElem = document.querySelector('#fee');
     feeElem.innerHTML=`<div>결제금액은 ${sumfee} 원 입니다.</div>`
 }

//
// $("select[name=num]").change(function(){
//     console.log($(this).val());//value값 가져오기
//     var numValue = $(this).val();});




getCartList();


