const { StatusCodes } = require("http-status-codes");
const User = require("../models/user");

//Controller 1
const defaultMsg = async (req, res) => {
    const { name } = req.body;
    const { age } = req.query;
    const user = await User.create({ name, age });
    if (user) {
        res.status(StatusCodes.OK).json({ text: "successful", createdoc: user })
    }
    else {
        res.status(StatusCodes.OK).send("Unsuccessful")
    }
}

//Controller 2
const getAllUsers = async (req, res) => {
    const users = await User.find({});
    return res.status(StatusCodes.OK).json(users)
}

//Controller 3
const search = async (req, res) => {
    const { name } = req.query;
    const user = await User.find({ name });
    if (user.length !== 0) {
        return res.status(StatusCodes.OK).json(user)
    }
    return res.status(StatusCodes.OK).send("User does not exists");
}

//Controller 4
const insertPassion = async (req, res) => {
    const { passion, name } = req.body;
    User.findOne({ name }).then((user) => {
        user.passion = passion;
        user.save()
        return res.status(StatusCodes.OK).json(user)
    })
}

//Controller 5
const deleteUser = async (req, res) => {
    const { name } = req.query;
    const deletedUser = await User.findOneAndDelete({ name });
    if (deleteUser) {
        return res.status(StatusCodes.OK).send("Successfully deleted")
    }
    return res.status(StatusCodes.OK).send("User does not exist")
}

module.exports = { defaultMsg, getAllUsers, search, insertPassion, deleteUser };