import React, { useEffect, useState } from "react"
import axios from "axios";
import { Link } from "react-router-dom"
import { useNavigate } from 'react-router-dom'
import Navbar from "./navbar";

function Takebook() {

    const navigate = useNavigate();
    const [books, setBooks] = useState([{
        name: '',
        publisher: '',
        year: null
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
                const { data } = await axios.get("http://18.237.33.67:5500/take/book", config);
                console.log(data);
                setBooks(data);

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

                <h1>Take books here:</h1>
                <h2>Total number of books:{books.length}</h2>
                <div className="row">
                    {books.map((book, index) => {
                        console.log(book.image);
                        return <div className="col-sm-4">
                            <div className="card " key={index}>
                                <img src={`http://18.237.33.67:5500/${book.image}`} className="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 className="card-title">Book name:{book.name}</h5>
                                    <p className="card-text">Publisher:{book.publisher}</p>
                                    <p className="card-text">Published year:{book.year}</p>
                                    <Link to={`/take/book/${book._id}`} exact className="btn btn-primary">More info</Link>
                                </div>
                            </div>
                        </div>


                    })}
                </div>
            </div>
        </div>
    )
}

export default Takebook;