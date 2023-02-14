import React, { useEffect, useState } from "react"
import axios from "axios";
import { Link, useNavigate } from "react-router-dom"
import Navbar from "./navbar";


function Takecloth() {

    const navigate = useNavigate();
    const [cloths, setCloths] = useState([{
        name: '',
        size: '',
        gender: ''
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
                const { data } = await axios.get("http://18.237.33.67:5500/take/cloth", config);
                console.log(data);
                setCloths(data);

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

                <h1>Take cloths here:</h1>
                <h2>Total number of cloths:{cloths.length}</h2>
                <div className="row">
                    {cloths.map((cloth, index) => {
                        return <div className="col-sm-4">
                            <div className="card">
                                <img src={`http://18.237.33.67:5500/${cloth.image}`} className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Cloth name:{cloth.name}</h5>
                                    <p className="card-text">Size:{cloth.size}</p>
                                    <p className="card-text">Gender:{cloth.gender}</p>
                                    <Link to={`/take/cloth/${cloth._id}`} className="btn btn-primary">More info</Link>
                                </div>
                            </div>
                        </div>
                    })}
                </div>
            </div>
        </div>
    )
}

export default Takecloth;