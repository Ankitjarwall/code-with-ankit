const express = require("express")
const router = express.Router();
const { defaultMsg } = require("../controllers/userControllers")

router.get("/create", defaultMsg);

module.exports = router;