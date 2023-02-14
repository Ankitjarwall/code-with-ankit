const express = require('express')
const app = express()
const cors = require('cors')
const paymentRout=require('./paymentRoute')
const bodyParser = require('body-parser')

const port = 5000;
app.use(cors())
app.use(bodyParser.json())
app.use('/api',paymentRout);
app.listen(port, () => {
    console.log('App is running at ${port}')
})
