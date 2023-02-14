import React, { useState, useEffect } from 'react';
import Home from './Home';
import Nav from './Nav';
import Footer from './Footer';
import Signup from './Signup';
import Whatwedo from './Whatwedo';
import Contactus from './Contactus';

export default function Main() {
    const [page, setPage] = useState("");

    useEffect(() => { console.log(page) }, [])
    return (
        <div>

            <Nav setPage={setPage} />
            {getinfo(page)}
            <Footer />
        </div>
    )
}

const getinfo = (page) => {
    switch (page) {
        case "Home":
            return (
                <Home />
            )

        case "Signup":
            return (
                <Signup />
            )

        case "Contactus":
            return (
                <Contactus />
            )

        case "Whatwedo":
            return (
                <Whatwedo />
            )
    }
}