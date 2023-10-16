const mongoose = require("mongoose");

const userSchema = new mongoose.Schema({
    name: {
        type: String,
    },
    age: {
        type: Number
    },
    passion: {
        type: Array
    }
});

module.exports = mongoose.model("User", userSchema);