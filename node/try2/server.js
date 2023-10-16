require("dotenv").config();
const express = require("express");
const app = express();
const cors = require("cors");
const connectDB = require('./db/connect');
const userRoutes = require('./routes/userRoutes')

const port = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());
app.use("/user", userRoutes)

const start = async () => {
    await connectDB(process.env.MONGO_URI)
    app.listen(port, () => {
        console.log(`Good to go.. ${port}`);
    });
}

app.get("/", (req, res) => {
    res.send("Hello world...")
})


start();