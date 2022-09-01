import React from "react"

import Navbar from "./navbar";
import "./givefood.css"

function Givefood(){
 
     
    


    return(
        <div>
             <Navbar />
             <div id="box">
        <div className='container-fluid'>
          
            <h1>Give foods here:</h1>
            <form>
  <div className="form-group">
    <label for="foodname">Food name:</label>
    <input type="text" className="form-control" id="foodname" placeholder="Enter name of food"
   
       required/>
   
  </div>
  <div className="form-group">
    <label for="quantity">Quantity:</label>
    <input type="number" className="form-control" id="quantity" placeholder="quantity"
   
        required/>
  </div>
  <div className="form-group">
  <label for="expiry">Best before:</label>
    <input type="text" className="form-control" id="expiry" placeholder="Enter expiry data"
    
      required/>
    
  </div>
  <div className="form-group">
      <label for="ima">Upload image:</label>
      <input type="file" id="ima" className="form-control" name="image"
       /> 
  </div>
  <button type="submit" className="btn btn-primary"
>Give</button>
</form> 
        </div>
        </div>
        </div>
    )
}

export default Givefood;