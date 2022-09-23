import React, { useEffect, useState } from "react"
import axios from "axios";
import { Link, useNavigate } from "react-router-dom"
import Navbar from "./navbar";


function Takemed() {

    const navigate = useNavigate();

    const [meds, setMeds] = useState([{
        name: '',
        purpose: '',
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
                const { data } = await axios.get("https://got-it-in.herokuapp.com/take/heal", config);
                console.log(data);
                setMeds(data);

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

                <h1>Take mediciness here</h1>
                <h2>Total number of meds:{meds.length}</h2>
                <div className="row">
                    {meds.map((med, index) => {
                        return <div className="col-sm-4">
                            <div className="card">
                                <img src={`https://got-it-in.herokuapp.com/${med.image}`} className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Medicine name:{med.name}</h5>
                                    <p className="card-text">Used for:{med.purpose}</p>
                                    <p className="card-text">quantity:{med.quantity}</p>
                                    <p className="card-text">expiry date:{med.expiry}</p>
                                    <Link to={`/take/heal/${med._id}`} className="btn btn-primary">More info</Link>
                                </div>
                            </div>
                        </div>
                    })}
                </div>
            </div>
        </div>
    )
}

export default Takemed;