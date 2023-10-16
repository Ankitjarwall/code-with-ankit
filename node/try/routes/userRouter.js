const express = require("express");
const router = express.Router();
const { defaultMsg, getAllUsers, search, insertPassion, deleteUser } = require("../controllers/userController");

router.post("/home", defaultMsg);
router.get("/getAllUsers", getAllUsers);
router.get("/searchUser", search);
router.post("/insertPassion", insertPassion);
router.delete("/deleteUser", deleteUser);

module.exports = router;