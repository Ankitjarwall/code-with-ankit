const express = require("express")
const app = express()
const PORT = 3000

app.get("/home", (req, res) => {
    res.send("This is home")
})

app.get("/hello", (req,res)=> {
    res.send("Hello")
})

app.listen(PORT, () => {
    console.log("Server is running...")
})