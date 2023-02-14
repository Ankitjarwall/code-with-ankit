import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import { Link } from "react-router-dom"
import "./navbar.css"
import axios from "axios";
import img from "./images/logoGOT-removebg-preview.png"


function Navbar() {
    const navigate = useNavigate();




    const [user, setUser] = useState([{
        name: '',
        email: ''

    }])



    useEffect(() => {


        const fetchData = async () => {
            const config = {
                headers: {
                    authorisation: `Bearer ${localStorage.getItem("authToken")}`
                }
            }

            try {
                const { data } = await axios.get("http://18.237.33.67:5500/profile/user", config);

                setUser(data);

            } catch (error) {
                localStorage.removeItem("authToken");
                alert("please login");
                console.log(error);
            }



        }

        fetchData();
    }, [navigate])



    return (

        <nav className="navbar navbar-light ">
            <div className="container">
                <a className="navbar-brand" href="/">
                    <img src={img} alt="" width="70" height="50" className="d-inline-block align-text-top" />
                    GOT-IT
                </a>


                <ul className="nav justify-content-end">
                    <li className="nav-item">
                        {!localStorage.getItem("authToken") ? (<a className="active btn a" aria-current="page" href="/login">Login</a>
                        ) :
                            (
                                <Link to="/profile" className="btn btn-primary">Welcome {user.username}</Link>
                            )}

                    </li>



                </ul>
            </div>
        </nav>
    )
}

export default Navbar;