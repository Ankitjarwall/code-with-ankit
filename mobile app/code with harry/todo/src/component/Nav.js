import React from 'react';
import './Nav.css';
// import { useState } from 'react';


export default function Nav({page,setPage,switchScreen}) {


    return (
        <div>

            <div className="nav-outer">
                <div className='navigation-bar'>
                    <button className='btn-nav' onClick={() => {
                        // setPage('Home');
                        switchScreen('Home')
                    }}>Home</button>
                    <button className='btn-nav' onClick={() => {
                        switchScreen('Contact')
                    }}>Contact Us</button>
                    <button className='btn-nav' onClick={() => {
                        switchScreen('About')
                    }}>About Us</button>
                </div>
            </div>

        </div>
    )


}
