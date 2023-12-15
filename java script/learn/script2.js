const express = require("express")
var figlet =require("figlet")
const app = express()

app.use(function (req, res, next) {

    console.log("hello")
    next();
})
var tdata;
app.get("/figlet", function (req, res) {
    figlet("MACBEASE", function (err, data) {
        if (err) {
            console.log("something went wrong.")
            return;
        }
        tdata=data
    })
    res.send(tdata)
})

app.get("/", function (req, res) {
    res.send("home")
});

app.get("/like", function (req, res) {
    res.send("like me")
})

app.listen(3000);