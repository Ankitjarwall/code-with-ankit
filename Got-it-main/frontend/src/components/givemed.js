import React, { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom";
import axios from "axios"
import Navbar from "./navbar";
import "./givemeds.css"

function Givemed() {

    const navigate = useNavigate();

    const [name, setName] = useState();
    const [purpose, setPurpose] = useState();
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
        data.append("name", name);
        data.append("purpose", purpose);
        data.append("quantity", quantity);
        data.append("expiry", expiry);
        data.append("image", file);

        axios.post('http://18.237.33.67:5500/give/heal', data, config)
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

                    <h1>Give Medicines here:</h1>
                    <form>
                        <div className="form-group">
                            <label for="medname">Medicine name:</label>
                            <input type="text" className="form-control" id="medname" placeholder="Enter name of medicine"
                                onChange={event => {
                                    const { value } = event.target;
                                    setName(value);
                                }} required />

                        </div>
                        <div className="form-group">
                            <label for="purpose">Purpose:</label>
                            <input type="text" className="form-control" id="purpose" placeholder="Purpose of medicine"
                                onChange={event => {
                                    const { value } = event.target;
                                    setPurpose(value);
                                }} required />
                        </div>
                        <div className="form-group">
                            <label for="quantity">Quantity:</label>
                            <input type="number" className="form-control" id="quantity" placeholder="Enter quantity"
                                onChange={event => {
                                    const { value } = event.target;
                                    setQuantity(value);
                                }} required />
                        </div>
                        <div className="form-group">
                            <label for="expiry">Expiry date:</label>
                            <input type="text" className="form-control" id="expiry" placeholder="Enter expiry date"
                                onChange={event => {
                                    const { value } = event.target;
                                    setExpiry(value);
                                }} required />

                        </div>
                        <div className="form-group">
                            <input type="file" className="form-control" name="image" onChange={event => {
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

export default Givemed;