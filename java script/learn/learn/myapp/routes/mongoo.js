const mongoose = require("mongoose");

mongoose.connect("mongodb://localhost:27017/practickero");

const userschema = mongoose.Schema({
    userName: String,
    name: String,
    age: Number,
})

module.exports = mongoose.model("user", userschema);