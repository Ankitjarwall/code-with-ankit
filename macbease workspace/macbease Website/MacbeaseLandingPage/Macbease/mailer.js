const http = require('http');
const qs = require('querystring');
const nodemailer = require('nodemailer');

// Create an HTTP server
const server = http.createServer((req, res) => {
    if (req.method === 'POST' && req.url === '/submit') {
        let body = '';

        req.on('data', (chunk) => {
            body += chunk;
        });

        req.on('end', () => {
            const formData = qs.parse(body);
            const { name, email, message } = formData;

            // Check for missing or invalid data
            if (!name || !email || !message) {
                res.writeHead(400, { 'Content-Type': 'text/plain' });
                res.end('Oops! There was a problem with your submission. Please complete the form and try again.');
            } else {
                // Send the email using nodemailer (you'll need to configure your email transport)
                const transporter = nodemailer.createTransport({
                    // Configure your email transport options (e.g., SMTP)
                    // Example: send email using Gmail
                    service: 'Gmail',
                    auth: {
                        user: 'your_email@gmail.com',
                        pass: 'your_password'
                    }
                });

                const mailOptions = {
                    from: email,
                    to: 'support@macbease.com', // Replace with your recipient's email
                    subject: `New contact from ${name}`,
                    text: `Name: ${name}\nEmail: ${email}\n\nMessage:\n${message}`
                };

                transporter.sendMail(mailOptions, (error, info) => {
                    if (error) {
                        res.writeHead(500, { 'Content-Type': 'text/plain' });
                        res.end('Oops! Something went wrong, and we couldn\'t send your message.');
                    } else {
                        res.writeHead(200, { 'Content-Type': 'text/plain' });
                        res.end('Thank You! Your message has been sent.');
                    }
                });
            }
        });
    } else {
        res.writeHead(403, { 'Content-Type': 'text/plain' });
        res.end('There was a problem with your submission, please try again.');
    }
});

const port = process.env.PORT || 3000;
server.listen(port, () => {
    console.log(`Server is listening on port ${port}`);
});
