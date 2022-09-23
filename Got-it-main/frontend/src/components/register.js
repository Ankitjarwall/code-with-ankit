
import React, { useState, useEffect } from "react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import "./register.css"

const Register = () => {

  const navigate = useNavigate();
  const [username, setUsername] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [file, setFile] = useState();
  const [registration, setRegistrastion] = useState();
  const [program, setProgram] = useState();

  useEffect(() => {
    if (localStorage.getItem("authToken")) {
      navigate("/");
    }
  }, [navigate]);

  function handleClick(event) {
    event.preventDefault();

    const data = new FormData();
    data.append("username", username);
    data.append("email", email);
    data.append("password", password);
    data.append("image", file);
    data.append("registration", registration);
    data.append("program", program);




    if (!username || !email || !password || !registration || !program) {
      return alert("Please fill out the form ")
    }

    axios.post('https://got-it-in.herokuapp.com/auth/register', data).then(res => {
      if (res.status == 201) {
        console.log(res.data["message"]);
        alert(res.data["message"]);
      }
      else {
        alert("Successfully registered");
        localStorage.setItem("authToken", res.data.token);
        navigate("/");
      }

    }).catch(err => {
      alert(err);
    })


  }

  return (
    <section class="vh-100 gradient-custom">
      <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-12 col-md-8 col-lg-6 col-xl-5">
            <div class="card text-white register-card" style={{ "border-radius": "1rem" }}>
              <div class="card-body p-5 text-center">

                <div class="mb-md-5 mt-md-4 pb-5">

                  <h2 class="fw-bold mb-2 text-uppercase">Register</h2>
                  <div class="form-outline form-white mb-4">
                    <input type="text" class="form-control form-control-lg" onChange={event => {
                      const { value } = event.target;
                      setUsername(value)
                    }} placeholder="Username" />

                  </div>

                  <div class="form-outline form-white mb-4">
                    <input type="number" class="form-control form-control-lg" onChange={event => {
                      const { value } = event.target;
                      setRegistrastion(value)
                    }} placeholder="Registrastion number" />

                  </div>

                  <div class="form-outline form-white mb-4">
                    <input type="text" class="form-control form-control-lg" onChange={event => {
                      const { value } = event.target;
                      setProgram(value)
                    }} placeholder="Course name" />

                  </div>

                  <div class="form-outline form-white mb-4">
                    <input type="email" class="form-control form-control-lg" onChange={event => {
                      const { value } = event.target;
                      setEmail(value)
                    }} placeholder="E-mail" />

                  </div>

                  <div class="form-outline form-white mb-4">
                    <input type="password" class="form-control form-control-lg" onChange={event => {
                      const { value } = event.target;
                      setPassword(value)
                    }} placeholder="Password" />

                  </div>

                  <div className="form-group">
                    <label for="img">Choose Profile photo:</label>
                    <input type="file" className="form-control" id="img" name="image" onChange={event => {
                      const file = event.target.files[0];
                      setFile(file);
                    }} />
                  </div>

                  <button class="btn btn-outline-light btn-lg px-5" type="submit" onClick={handleClick}>Register</button>


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