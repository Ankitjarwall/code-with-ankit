import React, { useState, useEffect } from "react"
import axios from "axios"
import { useNavigate } from 'react-router-dom';
import Navbar from "./navbar";
import "./givebooks.css"
function Givebook() {

    const navigate = useNavigate();
    const [name, setName] = useState();
    const [publisher, setPublisher] = useState();
    const [year, setYear] = useState();
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
        data.append("publisher", publisher);
        data.append("year", year);
        data.append("image", file);

        axios.post('https://got-it-in.herokuapp.com/give/book', data, config)
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

                    <h1>Give books here:</h1>
                    <form>
                        <div className="form-group">
                            <label for="Bookname">Book name:</label>
                            <input type="text" className="form-control" id="Bookname" placeholder="Enter name of book"
                                onChange={event => {
                                    const { value } = event.target;
                                    setName(value);
                                }} required />

                        </div>
                        <div className="form-group">
                            <label for="Publisher">Publisher name:</label>
                            <input type="text" className="form-control" id="Publisher" placeholder="Publisher name"
                                onChange={event => {
                                    const { value } = event.target;
                                    setPublisher(value);
                                }} required />
                        </div>
                        <div className="form-group">
                            <label for="Year">Publishing year:</label>
                            <input type="number" className="form-control" id="Year" placeholder="Enter publishing year"
                                onChange={event => {
                                    const { value } = event.target;
                                    setYear(value);
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

export default Givebook;