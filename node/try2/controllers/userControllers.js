const { StatusCodes } = require("http-status-codes")
const User = require("../models/user")

//controller 1
const defaultMsg = async (req, res) => {
    const { name } = req.body;
    const { age } = req.body;

    console.log(age);
    console.log(name);

    const user = await User.create({ name, age });
    if (user) {
        res.status(StatusCodes.OK).json({ text: "successful", createdoc: user })
    }
    else {
        res.status(StatusCodes.OK).send("Unsuccessful")
    }
}

module.exports = { defaultMsg }