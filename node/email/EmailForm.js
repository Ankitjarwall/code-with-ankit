import React, { useState } from 'react';
import axios from 'axios';

const EmailForm = () => {
    const [email, setEmail] = useState('');
    const [subject, setSubject] = useState('');
    const [message, setMessage] = useState('');

    const handleSendEmail = async () => {
        try {
            await axios.post('http://localhost:3001/send-email', {
                to: email,
                subject,
                text: message,
            });
            alert('Email sent successfully!');
        } catch (error) {
            console.error('Error sending email:', error.response?.data || error.message);
            alert('Failed to send email. Check the console for details.');
        }
    };

    return (
        <div>
            <h1>Email Form</h1>
            <label>
                To:
                <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
            </label>
            <br />
            <label>
                Subject:
                <input type="text" value={subject} onChange={(e) => setSubject(e.target.value)} />
            </label>
            <br />
            <label>
                Message:
                <textarea value={message} onChange={(e) => setMessage(e.target.value)} />
            </label>
            <br />
            <button onClick={handleSendEmail}>Send Email</button>
        </div>
    );
};

export default EmailForm;
