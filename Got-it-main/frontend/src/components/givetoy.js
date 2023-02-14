import React, { useState, useEffect } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";
import Navbar from "./navbar";
import "./givetoys.css"

function Givetoy() {

    const navigate = useNavigate();

    const [name, setName] = useState();
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
        data.append("image", file);

        axios.post('http://18.237.33.67:5500/give/play', data, config)
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

                    <h1>Give toys here:</h1>
                    <form>
                        <div className="form-group">
                            <label for="toyname">Toy name:</label>
                            <input type="text" className="form-control" id="toyname" placeholder="Enter name of toy"
                                onChange={event => {
                                    const { value } = event.target;
                                    setName(value);
                                }} required />

                        </div>
                        <div className="form-group">
                            <label for="ima">Upload Image:</label>
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

export default Givetoy;