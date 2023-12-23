const express = require('express');
const nodemailer = require('nodemailer');
const bodyParser = require('body-parser');
const aws = require('aws-sdk');
const cors = require('cors');

const app = express();
const PORT = process.env.PORT || 3001;

app.use(cors());
app.use(bodyParser.json());

aws.config.update({
    accessKeyId: ' ', //here add
    secretAccessKey: '', //here add
    region: 'us-east-1',
});

const ses = new aws.SES({ apiVersion: '2010-12-01', region: 'us-east-1', });

app.post('/send-email', async (req, res) => {
    const { to, subject, text } = req.body;

    const params = {
        Destination: {
            ToAddresses: [to],
        },
        Message: {
            Body: {
                Text: {
                    Charset: 'UTF-8',
                    Data: text,
                },
            },
            Subject: {
                Charset: 'UTF-8',
                Data: subject,
            },
        },
        Source: 'support@macbease.com', // Replace with your SES-verified sender email
    };

    try {
        const data = await ses.sendEmail(params).promise();
        res.status(200).send(`Email sent: Message ID ${data.MessageId}`);
    } catch (error) {
        console.error('Error sending email:', error);
        res.status(500).send(error.toString());
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
