import React,{useEffect} from "react"
import {useNavigate} from 'react-router-dom'
import Navbar from "./navbar";
import "./take.css"

function Take(){

    const navigate=useNavigate();
useEffect(() =>{
    if(!localStorage.getItem("authToken")){
       alert("Please login");
       navigate("/login");
    }
},[navigate]);


    return(
        <div>
            <Navbar/>
            <div id="box00">
                <div id='box1'>
                    <div className='container-fluid0'>
                        <h1 class="hh1"><a href="take/Book" className="takeH">Books</a></h1><br></br>

                    </div>
                </div>
                <div id='box1'>
                    <div className='container-fluid0'>
                        <h1 class="hh1"><a href="take/Food" className="takeH">Foods</a></h1><br></br>

                    </div>
                </div>
                <div id="box2">
                    <div className='container-fluid2'>
                        <h1 class="hh1"><a href="take/Cloth" className="takeH">Clothes</a></h1><br></br>
                    </div>
                </div>
                <div id="box3">

                    <div className='container-fluid3'>
                        <h1 class="hh1"><a href="take/play" className="takeH">Toys</a></h1><br></br>
                    </div>
                </div>
                <div id="box4">

                    <div className='container-fluid4'>
                        <h1 class="hh1"><a href="take/heal" className="takeH">Medicines</a></h1><br></br>
                    </div>
                </div>
            </div>
        
        </div>
    )
}

export default Take;