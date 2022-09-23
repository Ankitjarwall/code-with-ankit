import React, { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom";
import axios from "axios"
import Navbar from "./navbar";
import "./givefood.css"

function Givefood() {

    const navigate = useNavigate();

    const [name, setName] = useState();
    const [quantity, setQuantity] = useState();
    const [expiry, setExpiry] = useState();
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
        data.append('name', name);
        data.append("quantity", quantity);
        data.append("expiry", expiry);
        data.append("image", file);

        axios.post('https://got-it-in.herokuapp.com/give/food', data, config)
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

                    <h1>Give foods here:</h1>
                    <form>
                        <div className="form-group">
                            <label for="foodname">Food name:</label>
                            <input type="text" className="form-control" id="foodname" placeholder="Enter name of food"
                                onChange={event => {
                                    const { value } = event.target;
                                    setName(value);
                                }} required />

                        </div>
                        <div className="form-group">
                            <label for="quantity">Quantity:</label>
                            <input type="number" className="form-control" id="quantity" placeholder="quantity"
                                onChange={event => {
                                    const { value } = event.target;
                                    setQuantity(value);
                                }} required />
                        </div>
                        <div className="form-group">
                            <label for="expiry">Best before:</label>
                            <input type="text" className="form-control" id="expiry" placeholder="Enter expiry data"
                                onChange={event => {
                                    const { value } = event.target;
                                    setExpiry(value);
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

export default Givefood;