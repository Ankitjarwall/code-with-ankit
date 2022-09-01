import React from "react";

import {Link} from "react-router-dom"
import "./navbar.css"

import img from "./images/logoGOT-removebg-preview.png"


function Navbar(){
 
  
  
  
  
    return (
     
        <nav className="navbar navbar-light ">
              <div className="container">
             <a className="navbar-brand" href="/">
                 <img src={img} alt="" width="70" height="50" className="d-inline-block align-text-top" />
                   GOT-IT
                  </a>
                

       <ul className="nav justify-content-end">
        <li className="nav-item">
          <a className="active btn a" aria-current="page" href="/login">Login</a>
        
         
        </li> 
        
      
 
     </ul>
      </div>
      </nav>
    )
}

export default Navbar;