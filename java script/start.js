// const jobs = [
//     { id: 1, isactive: true },
//     { id: 2, isactive: false },
//     { id: 3, isactive: true },
// ];

// const activejobs = jobs.filter(job => job.isactive)

// console.log(activejobs);


// const person = {
//     talk() {
//         setTimeout(() =>{
//             console.log("this value : ", this);
//         }, 1000);
//     }
// };

// person.talk();


// const colors = ['red', 'green', 'blue'];

// const item = colors.map(color => `<li>${color}</li>`);

// console.log(item);

// const address = {
//     street: 'new colony',
//     city: 'dausa',
//     country: 'india'
// };

// const { street:st, city, country } = address;

// console.log("hi",st);


// const first = {Name:"Ankit"};
// const second = {last:"meena"};

// const three = first.concat(second);
// const three = [...first, "a", ...second, "b"];
// console.log(three);

// const clone = [...first];

// const three = { ...first, ...second, location: "india" };

// console.log(three);


// class Person {
//     constructor(name) {
//         this.name = name;
//     }
//     walk() {
//         console.log("Walk");
//     }
// }

// const person = new Person("Ankit");

// console.log(person.walk);


// console.log("hello");



function createcircle() {
    return {
        radius: 1,
        location: {
            x: 1,
            y: 1
        }
    };
}

function Circle(radius) {
    console.log("this", this);
    this.radius = radius;
    this.draw = function () {
        console.log("draw");
    }
}
const another = new Circle(1);