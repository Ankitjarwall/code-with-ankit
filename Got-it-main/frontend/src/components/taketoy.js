import React, { useEffect, useState } from "react"
import axios from "axios";
import { Link, useNavigate } from "react-router-dom"
import Navbar from "./navbar";


function Taketoy() {

    const navigate = useNavigate();

    const [toys, setToys] = useState([{
        name: ''

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
                const { data } = await axios.get("https://got-it-in.herokuapp.com/take/play", config);
                console.log(data);
                setToys(data);

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

                <h1>Take toys here</h1>
                <h2>Total number of toys:{toys.length}</h2>
                <div className="row">
                    {toys.map((toy, index) => {
                        return <div className="col-sm-4">
                            <div className="card" >
                                <img src={`https://got-it-in.herokuapp.com/${toy.image}`} className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Toy name:{toy.name}</h5>

                                    <Link to={`/take/play/${toy._id}`} className="btn btn-primary">More info</Link>
                                </div>
                            </div>
                        </div>
                    })}
                </div>
            </div>
        </div>
    )
}

export default Taketoy;