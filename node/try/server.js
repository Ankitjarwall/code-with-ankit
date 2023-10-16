require("dotenv").config();
const cors = require("cors")
const express = require('express');
const app = express();
const connectDB = require("./db/connect");
const userRouter = require("./routes/userRouter");

const port = process.env.PORT || 3090;

app.use(cors());
app.use(express.json());
app.use("/user", userRouter);

const start = async () => {
    await connectDB(process.env.MONGO_URI)
    app.listen(port, () => {
        console.log(`Server is listening to the port ${port}`);
    })
}

app.get("/", (req, res) => {
    res.send("Hello world")
})


start();