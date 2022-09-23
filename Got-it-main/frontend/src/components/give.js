import React,{useEffect} from "react"
import {useNavigate} from 'react-router-dom';
import "./give.css"
import * as LottiePlayer from "@lottiefiles/lottie-player";
import food_image from "./images/food_image.png"
import cloth_image from "./images/cloth_image.png"
import med_image from "./images/med_image.png"
import book_image from "./images/book_image.png"
import toy_image from "./images/toy_image.png"
import Navbar from "./navbar";
function Give(){

const navigate=useNavigate();
useEffect(() =>{
    if(!localStorage.getItem("authToken")){
       alert("Please login");
       navigate("/login");
    }
},[navigate]);
  
  const styles={
    background:"transparent",width: "400px", height: "400px"
  }


    return(
        
 <div>
     <Navbar />
  <div class="first">  
  <lottie-player src="https://assets2.lottiefiles.com/packages/lf20_udTJtk.json"    loop autoplay style={styles}></lottie-player>
     <h1 className="heading">DONATE ANYTHING HERE...</h1>
     
  </div>  
  <div class="second">
<img src={book_image} alt="" className="img_1"/>
    <div class="container1">
        <h1>READ WORLD</h1>
        <p class="para">A book never gets old.A book never loosen its wisdom.Someone needs them.</p>
        <a href="/give/book" className="btn btn-primary">Give</a>
    </div>
</div>
  <div className="third ">
  <img src={food_image} alt="" className="img_1"/>
      <div className="container1">
         
          <h1>FEED WORLD</h1>
          <p class="para">Leftover foods may not taste as delicious but it is still enough to feed the appetite of a burning stomach. It is more worthy to put in stomach than in refrigerator.</p>
          <a href="/give/food" className="btn btn-primary">Give</a>
      </div>
  </div>
  <div class="fourth">
  <img src={cloth_image} alt="" className="img_1"/>
    <div class="container1">
    <h1>CLOTH WORLD</h1>
        <p class="para">A cloth may loose its fashion appeal but it is still enough to cover body. Someone out there needs them</p>
        <a href="/give/cloth" className="btn btn-primary">Give</a>
    </div>
</div>
<div class="fifth">
<img src={med_image} alt="" className="img_1"/>
    <div class="container1">
        <h1>HEAL WOLRD</h1>
        <p class="para">Medicines left unconsumed do not loose their curing power. Someone out there needs them</p>
        <a href="/give/heal" className="btn btn-primary">Give</a>
    </div>
</div>

<div class="sixth">
    
<img src={toy_image} alt="" className="img_1"/>
    <div class="container1">
        <h1>PLAY WORLD</h1>
        <p class="para">Toys you have played out haven't lost their delight. A child out there in dark is waiting that someone will bring his childhood back to him. You are the one.</p>
        <a href="/give/play" className="btn btn-primary">Give</a>
        </div>
   
</div>

      
        </div>
    )
}

export default Give;