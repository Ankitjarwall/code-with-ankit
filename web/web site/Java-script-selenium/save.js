const fs = require('fs');
const content = "hello ankit my name is ankit.";
fs.appendFile('test.txt', content, err => {
    if (err) {
        console.error(err);
        return;
    }
})