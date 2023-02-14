import React from 'react';
// import './main.css';


export default function Nav({ setPage }) {

    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary allinone">

            <div className="container-fluid">
                <button className="navbar-brand">MacBease</button>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">

                        <li className="nav-item">
                            <button className="nav-link active" aria-current="page" onClick={() => { setPage("Home"); console.log("hiii") }}>Home</button>
                        </li>

                        <li className="nav-item">
                            <button className="nav-link" onClick={() => { setPage("Signup"); console.log("hiii2") }}>Sign up</button>
                        </li>

                        <li className="nav-item dropdown">
                            <button className="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                About us
                            </button>
                            <ul className="dropdown-menu">
                                <li><button className="dropdown-item" onClick={() => { setPage("Contactus"); console.log("hiii3") }}>Contact us</button></li>
                                <li><button className="dropdown-item" onClick={() => { setPage("Whatwedo"); console.log("hiii4") }}>What we Do</button></li>
                            </ul>
                        </li>

                    </ul>
                    <form className="d-flex" role="search">
                        <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                        <button className="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
    )
}

