
    const sup_submitElem = document.querySelector('#sup_submit') ;

    if(sup_submitElem){
        sup_submitElem.addEventListener('click',e=>{
            if(imbuy.value < buy.value){
                alert('경매가는 즉시구매가보다 낮아야합니다.');
                e.preventDefault();
            }
        })
    }



