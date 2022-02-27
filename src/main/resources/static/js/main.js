let imgKakao1 = document.querySelector('.imgKakao1');
let mouseOver1 = document.querySelector('.mouseOver1');
if (imgKakao1){
    imgKakao1.addEventListener('mouseover', () => {
        mouseOver1.style.display = 'block';
    })
    imgKakao1.addEventListener('mouseout', () => {
        mouseOver1.style.display = 'none';
    })
    mouseOver1.addEventListener('click', () => {
        location.href = "/auction/list/";
    })
}

let imgKakao2 = document.querySelector('.imgKakao2');
let mouseOver2 = document.querySelector('.mouseOver2');
if (imgKakao2){
    imgKakao2.addEventListener('mouseover', () => {
        mouseOver2.style.display = 'block';
    })
    imgKakao2.addEventListener('mouseout', () => {
        mouseOver2.style.display = 'none';
    })
    mouseOver2.addEventListener('click', () => {
        location.href = "/용품/list/";
    })
}

let imgKakao3 = document.querySelector('.imgKakao3');
let mouseOver3 = document.querySelector('.mouseOver3');
if (imgKakao3){
    imgKakao3.addEventListener('mouseover', () => {
        mouseOver3.style.display = 'block';
    })
    imgKakao3.addEventListener('mouseout', () => {
        mouseOver3.style.display = 'none';
    })
    mouseOver3.addEventListener('click', () => {
        location.href = "customer/list/1";
    })
}

let imgKakao4 = document.querySelector('.imgKakao4');
let mouseOver4 = document.querySelector('.mouseOver4');
if (imgKakao4){
    imgKakao4.addEventListener('mouseover', () => {
        mouseOver4.style.display = 'block';
    })
    imgKakao4.addEventListener('mouseout', () => {
        mouseOver4.style.display = 'none';
    })
    mouseOver4.addEventListener('click', () => {
        location.href = "customer/list/2";
    })
}

let imgKakao5 = document.querySelector('.imgKakao5');
let mouseOver5 = document.querySelector('.mouseOver5');
if (imgKakao5){
    imgKakao5.addEventListener('mouseover', () => {
        mouseOver5.style.display = 'block';
    })
    imgKakao5.addEventListener('mouseout', () => {
        mouseOver5.style.display = 'none';
    })
    mouseOver5.addEventListener('click', () => {
        location.href = "customer/list/3";
    })
}