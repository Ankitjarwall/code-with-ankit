var express = require('express');
var router = express.Router();
var userModel = require('./mongoo')

/* GET users listing. */
router.get('/', function (req, res, next) {
  res.send('respond with a resource');
});

router.get('/create', async function (req, res) {
  data = await userModel.create({
    userName: "Ankit Meena",
    name: "Ankit",
    age: 21,
  })
  console.log("user created.")
  res.send("the user is created..." + JSON.stringify(data))
})

router.get('/*', async function (req, res) {
  let data = await userModel.find()
  res.send("all users"+ JSON.stringify(data))
})

router.get('/delete', async function (req, res) {
  try {
    let data1 = await userModel.findOneAndDelete({ userName: "Ankit Meena" })
    console.log("user deleted... ", data1)
    res.send("user deleted..."+ JSON.stringify(data1))
  }
  catch (error) {
    console.log(error)
  }
});

module.exports = router;
