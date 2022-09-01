import React from "react"

import Navbar from "./navbar";
import "./givecloth.css"

function Givecloth(){
  
  

   


    return(
        <div>
             <Navbar />
             <div id="box">
        <div className='container-fluid'>
         
            <h1>Give cloths here:</h1>
            <form>
  <div className="form-group">
    <label for="clothname">Cloth name:</label>
    <input type="text" className="form-control" id="clothname" placeholder="Enter name of cloth"
   
       required/>
   
  </div>
  <div className="form-group">
    <label for="size">Size:</label>
    <input type="text" className="form-control" id="size" placeholder="size name"
    required/>
  </div>
  <div className="form-group">
  <label for="gender"> Gender:</label>
    <input type="text" className="form-control" id="gender" placeholder="Enter gender"
   
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

export default Givecloth;