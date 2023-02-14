const EventEmitter = require('events');

console.log("program in alag file");

class Logger extends EventEmitter {
    print(mess) {
        console.log("Message 1..." + mess);

        this.emit("logging", { name: "ankit" });
    }
};

console.log("alag end");
module.exports = Logger;


