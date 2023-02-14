import React, { useEffect, useState } from "react"
import axios from "axios";
import { Link, useNavigate } from "react-router-dom"
import Navbar from "./navbar";


function Takefood() {

    const navigate = useNavigate();
    const [foods, setFoods] = useState([{
        name: '',
        quantity: null,
        expiry: ''
    }])

    useEffect(() => {
        if (!localStorage.getItem("authToken")) {
            alert("Please login")
            navigate("/login");
        }

        const fetchData = async () => {
            const config = {
                headers: {
                    authorisation: `Bearer ${localStorage.getItem("authToken")}`
                }
            }

            try {
                const { data } = await axios.get("http://18.237.33.67:5500/take/food", config);
                console.log(data);
                setFoods(data);

            } catch (error) {
                localStorage.removeItem("authToken");
                alert("please login");
                console.log(error);
            }



        }

        fetchData();
    }, [navigate])





    return (
        <div>
            <Navbar />
            <div className="container-fluid">

                <h1>Take foods here</h1>
                <h2>Total number of foods:{foods.length}</h2>
                <div className="row">
                    {foods.map((food, index) => {
                        return <div className="col-sm-4">
                            <div className="card">
                                <img src={`https://got-it-in.herokuapp.com/${food.image}`} className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Food name:{food.name}</h5>
                                    <p className="card-text">quantity:{food.quantity}</p>
                                    <p className="card-text">expiry date:{food.expiry}</p>
                                    <Link to={`/take/food/${food._id}`} className="btn btn-primary">More info</Link>
                                </div>
                            </div>
                        </div>
                    })}
                </div>
            </div>
        </div>

    )
}

export default Takefood;