{
    const forgotIdForm = document.querySelector('#forgot_id_form');

    if (forgotIdForm) {
        const cancelBtn = document.querySelector('#cancel_btn');
        cancelBtn.addEventListener('click', () => {
            window.history.back();
        })

        forgotIdForm.onsubmit = () => {
            let inputs = document.querySelectorAll('input');

            for (let i = 0; i < inputs.length; i++) {
                let input = inputs[i];

                if (input.dataset.regex !== undefined) {
                    let regex = new RegExp(input.dataset.regex);
                    let name = input.previousElementSibling.textContent;

                    if (!regex.test(input.value)) {
                        alert(`${name}를 다시 확인해 주세요.`);
                        input.focus();
                        return false;
                    }
                }
            }

            // const param = {
            //     'contact_first' : forgotIdForm.contact_first.value,
            //     'contact_second' : forgotIdForm.contact_second.value,
            //     'contact_third' : forgotIdForm.contact_third.value
            // }
            //
            // fetch('/user/forgotId', {
            //     method : 'POST',
            //     headers : { 'Content-Type' : 'application/json' },
            //     body : JSON.stringify(param)
            // })
            //     .then(res => res.json())
            //     .then((data) => {
            //         console.log(data);
            //     })


            return true;


        }
    }
}