const express = require("express")

const app = express()

app.get("/", function (req, res) {
    res.send("Thank you for visting us.")
})

app.get("/home", function (req, res) {
    res.send("Welcome to the home.")
})

app.get("/:username", function (req, res) {
    res.send(`this page doesn't exists...${req.params.username}`)
})

app.listen(3000)
