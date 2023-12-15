var figlet = require("figlet")

figlet("Macbease", function (err, data) {
    if (err) {
        console.log("somthing went wrong");
        return;
    }
    console.log(data)
})