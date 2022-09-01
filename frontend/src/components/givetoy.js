import React from "react"

import Navbar from "./navbar";
import "./givetoys.css"

function Givetoy(){
  
   

    return(
        <div>
             <Navbar />
             <div id="box">
        <div className='container-fluid'>
           
            <h1>Give toys here:</h1>
            <form>
  <div className="form-group">
    <label for="toyname">Toy name:</label>
    <input type="text" className="form-control" id="toyname" placeholder="Enter name of toy"
    
     required/>
   
  </div>
  <div className="form-group">
      <label for="ima">Upload Image:</label>
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

export default Givetoy;