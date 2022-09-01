import React from "react";
import "./home.css"
import donate from "./images/donate-removebg-preview.png"
import joy from "./images/joy.jpeg"
import feedb from "./images/feed.jpeg"
import rakkt from "./images/rakkt.jpeg"
import naari from "./images/Naari.jpeg"
import adoptaplant from "./images/adoptAplant.jpeg"

import Navbar from "./navbar";
function Home(){

 
    return (
        
         <div>   
             <Navbar />    
               <div className="fir">  
      <img src={donate} alt="" className="donate_img"/>
   

     <div className="containerx">
   <h1 className="text">JOIN OUR GOT FAMILY!</h1> 
   <p>AND START YOUR JOUNEY OF MAKING SMILE AFFORDABLE FOR OTHERS!
</p>


<div className="button">
<a href="/give" className="btn a">Give</a>
<a href="/take" className="btn a">Take</a>
</div>
</div>
</div>

<div className="two">
    <div className="two_0">
        <h3>Our Partners</h3>
    </div>
    <div className="two_1">
        <img src={feedb} alt="" className="img_two"/>
      <h5>Feed the belly</h5>
      </div>
      <div className="two_2">
      <img src={joy} alt="" className="img_two"/>
      <h5>Joy of giving</h5>
 </div>
 <div className="two_3">
 <img src={rakkt} alt="" className="img_two"/>
      <h5>Rakkt</h5>
      </div>
      <div className="two_4">
      <img src={adoptaplant} alt="" className="img_two"/>
      <h5>Adopt a plant</h5>
      </div>
      <div className="two_5">
      <img src={naari} alt="" className="img_two"/>
      <h5>Naari</h5>
      </div>
       
   </div>

   <div className="sec">
       <div className="sec_1">
           <h1 className="que">What we do?</h1>
           <p className="ans">We have created an interesting internet application called GOT IT . GOT stands for GIVE OR TAKE . on this platform people can give what they dont need anymore, anything thats redundant for them. also they can take anything they need without any cost. a kind of symbiotic relationship . A certain form of barter system with no profit intented </p>
       </div>
       <div className="sec_2">
           <h1 className="que">How it works</h1>
           <p className="ans">On this platform people in need of anything can search on the application . If they are unable to find it, they can make a small reel and post it</p>
           </div>
           <div className="sec_3">
               <h1>Footer</h1>
           </div>
       
   </div>

   </div>

     
     
        
    )
}

export default Home;