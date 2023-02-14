// // function hello(name) {
// //     console.log("Hello ",name)
// // }

// // hello("Ankit");

console.log("program start");

const EventEmitter = require('events');
// const emitter = new EventEmitter();
const Logger = require('./logger');
const logger = new Logger();

console.log("Mid");

logger.on("logging", function ({ name }) {
    console.log(`log in by ${name}`);
});

logger.print('meena');

console.log("program end");