const mongoose = require('mongoose')

const connectDB = (url) => {
    return mongoose.connect(url, {
        useNewUrlParser: true,
        useUniFiedTopology: true
    })
}

module.exports = connectDB;