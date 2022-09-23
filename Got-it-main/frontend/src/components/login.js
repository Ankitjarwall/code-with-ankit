import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import "./login.css"
function Login() {

  const navigate = useNavigate();

  const [email, setEmail] = useState();
  const [password, setPassword] = useState();

  useEffect(() => {
    if (localStorage.getItem("authToken")) {
      navigate("/");
    }
  }, [navigate]);

  function handleClick(event) {
    event.preventDefault();

    const data = {
      email,
      password
    }


    axios.post('https://got-it-in.herokuapp.com/auth/login', data).then(res => {
      if (res.status === 201) {

        alert(res.data["message"]);
      }
      else {

        alert("Login successful");
        localStorage.setItem('authToken', res.data.token);
        navigate("/");
      }
    }).catch(err => {
      alert(err);
      console.log(err);
    })
  }

  return (
    <section class="vh-100 gradient-custom">
      <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-12 col-md-8 col-lg-6 col-xl-5">
            <div class="card text-white login-card" style={{ "border-radius": "1rem" }}>
              <div class="card-body p-5 text-center">

                <div class="mb-md-5 mt-md-4 pb-5">

                  <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                  <p class="text-white-50 mb-5">Please enter your Email and password!</p>

                  <div class="form-outline form-white mb-4">
                    <input type="email" id="typeEmailX" class="form-control form-control-lg" onChange={event => {
                      const { value } = event.target;
                      setEmail(value)
                    }} placeholder="E-mail" />

                  </div>

                  <div class="form-outline form-white mb-4">
                    <input type="password" id="typePasswordX" class="form-control form-control-lg" onChange={event => {
                      const { value } = event.target;
                      setPassword(value)
                    }} placeholder="Password" />

                  </div>



                  <button class="btn btn-outline-light btn-lg px-5" type="submit" onClick={handleClick}>Login</button>


                </div>

                <div>
                  <p class="mb-0">Don't have an account? <a href="/register" class="text-white fw-bold">Register</a></p>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

  )
}

export default Login;