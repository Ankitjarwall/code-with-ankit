import React from "react"

import Navbar from "./navbar";
import "./givebooks.css"
function Givebook(){

   
    


    return(
        <div>
             <Navbar />
        <div id="box">
           
        <div className='container-fluid'>
            
            <h1>Give books here:</h1>
            <form>
  <div className="form-group">
    <label for="Bookname">Book name:</label>
    <input type="text" className="form-control" id="Bookname" placeholder="Enter name of book"
   
       required/>
   
  </div>
  <div className="form-group">
    <label for="Publisher">Publisher name:</label>
    <input type="text" className="form-control" id="Publisher" placeholder="Publisher name"
    required/>
  </div>
  <div className="form-group">
  <label for="Year">Publishing year:</label>
    <input type="number" className="form-control" id="Year" placeholder="Enter publishing year"
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

export default Givebook;