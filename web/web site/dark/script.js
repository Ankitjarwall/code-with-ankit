document.querySelector('.dark').addEventListener('click', function () {
    document.querySelector('.text-span').style.color = 'white';
    document.querySelector('.main-div').style.backgroundColor = 'black';
});

document.querySelector('.light').addEventListener('click', function () {
    document.querySelector('.text-span').style.color = 'black';
    document.querySelector('.main-div').style.backgroundColor = 'white';
});