const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const port = 3000; // You can change the port number

// Middleware to parse JSON data
app.use(bodyParser.json());


// GET route
app.get('/api/data', (req, res) => {
    // Replace this with your desired response
    const data = {
        message: 'This is a GET request example',
    };
    res.json(data);
});

// POST route
app.post('/api/data', (req, res) => {
    // Log the data received in the request body to the console
    console.log('Data received via POST request:', req.body);

    // Respond to the client
    res.json({ message: 'Data received and logged', data: req.body });
});

// Start the server
app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
