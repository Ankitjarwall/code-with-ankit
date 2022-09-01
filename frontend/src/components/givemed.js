import React from "react"

import Navbar from "./navbar";
import "./givemeds.css"

function Givemed(){
  
  

    return(
        <div>
             <Navbar />
             <div id="box">
        <div className='container-fluid'>
          
            <h1>Give Medicines here:</h1>
            <form>
  <div className="form-group">
    <label for="medname">Medicine name:</label>
    <input type="text" className="form-control" id="medname" placeholder="Enter name of medicine"
     required/>
   
  </div>
  <div className="form-group">
    <label for="purpose">Purpose:</label>
    <input type="text" className="form-control" id="purpose" placeholder="Purpose of medicine"
    
      required/>
  </div>
  <div className="form-group">
    <label for="quantity">Quantity:</label>
    <input type="number" className="form-control" id="quantity" placeholder="Enter quantity"
    
      required/>
  </div>
  <div className="form-group">
  <label for="expiry">Expiry date:</label>
    <input type="text" className="form-control" id="expiry" placeholder="Enter expiry date"
    
     required/>
    
  </div>
  <div className="form-group">
      <input type="file" className="form-control" name="image" 
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

export default Givemed;