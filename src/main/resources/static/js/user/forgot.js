{
    const forgotForm = document.querySelector('#forgot_form');

    if (forgotForm) {

        const cancelBtn = document.querySelector('#cancel_btn');
        cancelBtn.addEventListener('click', () => {
            window.history.back();
        })

        forgotForm.submit_btn.addEventListener('click', (e) => {
            let inputs = document.querySelectorAll('input');

            for (let i = 0; i < inputs.length; i++) {
                let input = inputs[i];

                if (input.dataset.regex !== undefined) {
                    let regex = new RegExp(input.dataset.regex);
                    let name = input.previousElementSibling.textContent;

                    if (!regex.test(input.value)) {
                        alert(`${name}를 다시 확인해 주세요.`);
                        input.focus();

                        e.preventDefault();
                        return false;
                    }
                }
            }

            forgotForm.submit();
        });
    }
}