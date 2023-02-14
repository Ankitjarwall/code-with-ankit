const express = require('express')
const app = express()

app.get("", (req, res) => {
    res.send("running....")
    res.json({ "user": ["userone", "usertwo", "userThree"] })
})
app.listen(5000, () => { console.log("server started on port 5000") })