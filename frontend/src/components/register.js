
import React  from "react";

import "./register.css"

const Register=() =>{

  

    return (
      <section class="vh-100 gradient-custom">
      <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-12 col-md-8 col-lg-6 col-xl-5">
            <div class="card text-white register-card" style={{"border-radius": "1rem"}}>
              <div class="card-body p-5 text-center">
    
                <div class="mb-md-5 mt-md-4 pb-5">
    
                  <h2 class="fw-bold mb-2 text-uppercase">Register</h2>
                  <div class="form-outline form-white mb-4">
                    <input type="text" class="form-control form-control-lg" 
                        placeholder="Username"/>
                   
                  </div>

                  <div class="form-outline form-white mb-4">
                    <input type="number" class="form-control form-control-lg" 
                        placeholder="Registrastion number"/>
                   
                  </div>

                  <div class="form-outline form-white mb-4">
                    <input type="text" class="form-control form-control-lg" 
                       placeholder="Course name"/>
                   
                  </div>
    
                  <div class="form-outline form-white mb-4">
                    <input type="email"  class="form-control form-control-lg" 
                       placeholder="E-mail"/>
                   
                  </div>
    
                  <div class="form-outline form-white mb-4">
                    <input type="password"  class="form-control form-control-lg" 
                      placeholder="Password"/>
                    
                  </div>
    
                  <div className="form-group">
                    <label for="img">Choose Profile photo:</label>
                        <input type="file" className="form-control" id="img" name="image" 
                          /> 
                        </div>
    
                  <button class="btn btn-outline-light btn-lg px-5" type="submit" >Register</button>
    
                
                </div>
    
               
    
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    )
}

export default Register;