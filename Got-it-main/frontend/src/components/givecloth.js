import React, { useState, useEffect } from "react"
import { useNavigate } from 'react-router-dom';
import axios from "axios"
import Navbar from "./navbar";
import "./givecloth.css"

function Givecloth() {

    const navigate = useNavigate();
    const [name, setName] = useState();
    const [size, setSize] = useState();
    const [gender, setGender] = useState();
    const [file, setFile] = useState();

    useEffect(() => {
        if (!localStorage.getItem("authToken")) {
            alert("Please login:")
            navigate("/login");
        }
    }, [navigate])


    function handleClick(event) {
        event.preventDefault();

        const config = {
            headers: {
                authorisation: `Bearer ${localStorage.getItem("authToken")}`
            }
        }


        const data = new FormData();
        data.append("name", name);
        data.append("size", size);
        data.append("gender", gender);
        data.append("image", file);

        axios.post('https://got-it-in.herokuapp.com/give/cloth', data, config)
            .then(() => {
                alert("successfully donated")
            }).catch(err => {
                alert(err);
            })

    }



    return (
        <div>
            <Navbar />
            <div id="box">
                <div className='container-fluid'>

                    <h1>Give cloths here:</h1>
                    <form>
                        <div className="form-group">
                            <label for="clothname">Cloth name:</label>
                            <input type="text" className="form-control" id="clothname" placeholder="Enter name of cloth"
                                onChange={event => {
                                    const { value } = event.target;
                                    setName(value);
                                }} required />

                        </div>
                        <div className="form-group">
                            <label for="size">Size:</label>
                            <input type="text" className="form-control" id="size" placeholder="size name"
                                onChange={event => {
                                    const { value } = event.target;
                                    setSize(value);
                                }} required />
                        </div>
                        <div className="form-group">
                            <label for="gender"> Gender:</label>
                            <input type="text" className="form-control" id="gender" placeholder="Enter gender"
                                onChange={event => {
                                    const { value } = event.target;
                                    setGender(value);
                                }} required />

                        </div>
                        <div className="form-group">
                            <label for="ima">Upload image:</label>
                            <input type="file" id="ima" className="form-control" name="image" onChange={event => {
                                const file = event.target.files[0];
                                setFile(file);
                            }} />
                        </div>
                        <button type="submit" className="btn btn-primary"
                            onClick={handleClick}>Give</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default Givecloth;