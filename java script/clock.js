let hour = document.getElementById('web_hour');
let minute = document.getElementById('web_minute');
let second = document.getElementById('web_second');
let secondup = 1;
let minuteup = 1;
let hourup = 1;

let curr_hour = document.getElementById('curr_hour');
let curr_minute = document.getElementById('curr_minute');
let curr_second = document.getElementById('curr_second');

let curr_day = document.getElementById('Day');
let curr_date = document.getElementById('DD');
let curr_month = document.getElementById('MM');
let curr_year = document.getElementById('YY');

let day = new Date().getDay();;


function calculateHour(hourupdate, minuteupdate, secondupdate) {
    return (
        setInterval(() => {

            second.innerText = secondupdate
            if (secondupdate == 59) {
                minuteupdate++;
                minute.innerText = minuteupdate;
                second.innerText = 0;
                secondupdate = 0;

                if (minuteupdate == 59) {
                    hourupdate++;
                    minuteupdate = 0;
                    minute.innerText = minuteupdate;
                    hour.innerText = hourupdate;
                    if (hourupdate == 24) {
                        hour.innerText = 0;
                    }
                }
            }
            secondupdate++;

            if (day == 0) {
                curr_day.innerText = "Sun";
            }
            else if (day == 1) {
                curr_day.innerText = "Mon";

            }
            else if (day == 2) {
                curr_day.innerText = "Tue";

            }
            else if (day == 3) {
                curr_day.innerText = "Wed";

            }
            else if (day == 4) {
                curr_day.innerText = "Thu";

            }
            else if (day == 5) {
                curr_day.innerText = "Fri";

            }
            else if (day == 6) {
                curr_day.innerText = "Sat";

            }
            curr_date.innerText = new Date().getDate();
            curr_month.innerText = new Date().getMonth() + 1;
            curr_year.innerText = new Date().getFullYear();

            curr_hour.innerText = new Date().getHours();
            curr_minute.innerText = new Date().getMinutes();

        }, 1000)
    )
};

calculateHour(hourup, minuteup, secondup);

let joke="";

fetch("https://v2.jokeapi.dev/joke/Any")
    .then((response) => {
        // console.log(response);
        data = response.json()
        return data;
    })
    .then((data) => {
        joke = data.setup +"\n"+ data.delivery;
        // console.log("joke : ",joke);

        let Joke = document.getElementById("joke");
        Joke.innerText = joke;
    })
    .catch();

