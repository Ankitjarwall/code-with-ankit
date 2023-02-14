import React, { useEffect, useState } from 'react'
import "./profile.css"
import img from "./images/avatar.png"
import { useNavigate } from "react-router-dom"
import axios from 'axios'

function Profile() {

    const navigate = useNavigate();

    const [user, setUser] = useState([{
        name: '',
        email: ''

    }])

    const [book, setBook] = useState([{
        name: '',
        publisher: ''
    }]);
    const [cloth, setCloth] = useState([{
        name: ''
    }]);
    const [food, setFood] = useState([{
        name: ''
    }]);
    const [toy, setToy] = useState([{
        name: ''
    }]);
    const [med, setMed] = useState([{
        name: ''
    }]);

    let B_len, C_len, F_len, T_len, M_len, Total_len = 0, Total_points = 0;

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

        const fetchbookData = async () => {
            const bookconfig = {
                headers: {
                    authorisation: `Bearer ${localStorage.getItem("authToken")}`
                }
            }

            try {
                const bookdata = await axios.get("http://18.237.33.67:5500/profile/book", bookconfig);

                setBook(bookdata.data);


            } catch (error) {

                alert(error.message);
                console.log(error);
            }
        }
        fetchbookData();

        const fetchclothData = async () => {
            const clothconfig = {
                headers: {
                    authorisation: `Bearer ${localStorage.getItem("authToken")}`
                }
            }

            try {
                const clothdata = await axios.get("http://18.237.33.67:5500/profile/cloth", clothconfig);

                setCloth(clothdata.data);


            } catch (error) {

                alert(error.message);
                console.log(error);
            }
        }
        fetchclothData();

        const fetchfoodData = async () => {
            const foodconfig = {
                headers: {
                    authorisation: `Bearer ${localStorage.getItem("authToken")}`
                }
            }

            try {
                const fooddata = await axios.get("http://18.237.33.67:5500/profile/food", foodconfig);

                setFood(fooddata.data);


            } catch (error) {

                alert(error.message);
                console.log(error);
            }
        }
        fetchfoodData();

        const fetchtoyData = async () => {
            const toyconfig = {
                headers: {
                    authorisation: `Bearer ${localStorage.getItem("authToken")}`
                }
            }

            try {
                const toydata = await axios.get("http://18.237.33.67:5500/profile/play", toyconfig);

                setToy(toydata.data);


            } catch (error) {

                alert(error.message);
                console.log(error);
            }
        }
        fetchtoyData();

        const fetchmedData = async () => {
            const medconfig = {
                headers: {
                    authorisation: `Bearer ${localStorage.getItem("authToken")}`
                }
            }

            try {
                const meddata = await axios.get("http://18.237.33.67:5500/profile/heal", medconfig);

                setMed(meddata.data);


            } catch (error) {

                alert(error.message);
                console.log(error);
            }
        }
        fetchmedData();

    }, [navigate])



    B_len = book.length;
    C_len = cloth.length;
    F_len = food.length;
    M_len = med.length;
    T_len = toy.length;
    Total_len = B_len + C_len + F_len + M_len + T_len;

    let B_points = B_len * 50;
    let F_points = F_len * 50;
    let C_points = C_len * 50;
    let M_points = M_len * 50;
    let T_points = T_len * 50;

    if (B_points > 200)
        B_points = 200;

    if (F_points > 200)
        F_points = 200;

    if (C_points > 200)
        C_points = 200;

    if (M_points > 200)
        M_points = 200;

    if (T_points > 200)
        T_points = 200;



    Total_points = B_points + C_points + F_points + M_points + T_points;

    function logout() {
        alert("Click ok to logout");
        localStorage.removeItem("authToken");
        navigate("/");
    }

    return (
        <div className="content">
            <div className="container">
                <div className="row">
                    <div className="col-sm-12">

                        <div className="profile-user-box card-box bg-custom">
                            <div className=" mr-3"><img src={`http://18.237.33.67:5500/${user.image}`} alt="" className="thumb-lg rounded-circle" /></div>
                            <div className="media-body text-white">
                                <h4 className="mt-1 mb-1 font-18">{user.username}</h4>
                                <p className="mt-1 mb-1 font-13">Registration number:{user.registration}</p>
                                <p className="font-13 text-light">{user.program},Lovely Professional University</p>
                                <p className="text-light mb-0">India</p>
                                <button className="btn btn-danger log" type="submit" onClick={logout}>Logout</button>

                            </div>
                        </div>

                    </div>
                </div>

                <div className="row">
                    <div className="col-xl-4">

                        <div className="card-box">
                            <h4 className="header-title mt-0">Personal Information</h4>
                            <div className="panel-body">
                                <p className="text-muted font-13">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Est aspernatur harum numquam eligendi modi repudiandae? Repellat, quisquam? Tenetur, repellendus reiciendis odio iste, dolores voluptas vel ratione laudantium commodi</p>

                                <div className="text-left">
                                    <p className="text-muted font-13"><strong>Full Name :</strong> <span className="m-l-15">{user.username}</span></p>
                                    <p className="text-muted font-13"><strong>Email :</strong> <span className="m-l-15">{user.email}</span></p>
                                    <p className="text-muted font-13"><strong>Program :</strong><span className="m-l-15">B.Tech</span></p>

                                    <p className="text-muted font-13"><strong>University :</strong> <span className="m-l-15">Lovely Professional University</span></p>
                                    <p className="text-muted font-13"><strong>Location :</strong> <span className="m-l-15">India</span></p>

                                </div>

                            </div>
                        </div>

                        <div className="card-box">
                            <h4 className="header-title mt-0">Recent Donations</h4>
                            <div className="panel-body">
                                <p className="header-title mb-3">Recent items that user has given will be shown here...</p>
                                <div className="table-responsive">
                                    <table className="table">
                                        <thead>
                                            <tr>
                                                <th>/</th>

                                                <th>Item name</th>
                                                <th>Date</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>

                                                <td><span className="label label-info">working on it</span></td>
                                                <td>5-6-2022</td>
                                                <td>pending</td>
                                            </tr>
                                            <tr>
                                                <td>2</td>


                                                <td><span className="label label-success">working on it</span></td>
                                                <td>5-6-2022</td>
                                                <td>pending</td>
                                            </tr>
                                            <tr>
                                                <td>3</td>


                                                <td><span className="label label-pink">working on it</span></td>
                                                <td>5-6-2022</td>
                                                <td>pending</td>
                                            </tr>
                                            <tr>
                                                <td>4</td>


                                                <td><span className="label label-purple">working on it</span></td>
                                                <td>5-6-2022</td>
                                                <td>pending</td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>

                        <div className="card-box ribbon-box">
                            <div className="ribbon ribbon-primary">Upcoming events</div>
                            <div className="clearfix"></div>
                            <div className="inbox-widget">
                                <a href="/">
                                    <div className="inbox-item">
                                        <div className="inbox-item-img"><img src={img} className="rounded-circle" alt="" /></div>
                                        <p className="inbox-item-author">Title</p>
                                        <p className="inbox-item-text">Event details</p>
                                        <p className="inbox-item-date">
                                            <button type="button" className="btn btn-icon btn-sm waves-effect waves-light btn-success">join</button>
                                        </p>
                                    </div>
                                </a>
                                <a href="/">
                                    <div className="inbox-item">
                                        <div className="inbox-item-img"><img src={img} className="rounded-circle" alt="" /></div>
                                        <p className="inbox-item-author">Title</p>
                                        <p className="inbox-item-text">Event details</p>
                                        <p className="inbox-item-date">
                                            <button type="button" className="btn btn-icon btn-sm waves-effect waves-light btn-success">Join</button>
                                        </p>
                                    </div>
                                </a>
                                <a href="/">
                                    <div className="inbox-item">
                                        <div className="inbox-item-img"><img src={img} className="rounded-circle" alt="" /></div>
                                        <p className="inbox-item-author">Title</p>
                                        <p className="inbox-item-text">Event details</p>
                                        <p className="inbox-item-date">
                                            <button type="button" className="btn btn-icon btn-sm waves-effect waves-light btn-success">Join</button>
                                        </p>
                                    </div>
                                </a>
                                <a href="/">
                                    <div className="inbox-item">
                                        <div className="inbox-item-img"><img src={img} className="rounded-circle" alt="" /></div>
                                        <p className="inbox-item-author">Title</p>
                                        <p className="inbox-item-text">Event details</p>
                                        <p className="inbox-item-date">
                                            <button type="button" className="btn btn-icon btn-sm waves-effect waves-light btn-success">Join</button>
                                        </p>
                                    </div>
                                </a>
                                <a href="/">
                                    <div className="inbox-item">
                                        <div className="inbox-item-img"><img src={img} className="rounded-circle" alt="" /></div>
                                        <p className="inbox-item-author">Title</p>
                                        <p className="inbox-item-text">Event details</p>
                                        <p className="inbox-item-date">
                                            <button type="button" className="btn btn-icon btn-sm waves-effect waves-light btn-success">Join</button>
                                        </p>
                                    </div>
                                </a>
                                <a href="/">
                                    <div className="inbox-item">
                                        <div className="inbox-item-img"><img src={img} className="rounded-circle" alt="" /></div>
                                        <p className="inbox-item-author">Title</p>
                                        <p className="inbox-item-text">Event details</p>
                                        <p className="inbox-item-date">
                                            <button type="button" className="btn btn-icon btn-sm waves-effect waves-light btn-success">join</button>
                                        </p>
                                    </div>
                                </a>
                                <a href="/">
                                    <div className="inbox-item">
                                        <div className="inbox-item-img"><img src={img} className="rounded-circle" alt="" /></div>
                                        <p className="inbox-item-author">Title</p>
                                        <p className="inbox-item-text">Event details</p>
                                        <p className="inbox-item-date">
                                            <button type="button" className="btn btn-icon btn-sm waves-effect waves-light btn-success">Join</button>
                                        </p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div className="col-xl-8">
                        <div className="row">
                            <h4 className="header-title mt-0 mb-3">Your CDP Details:</h4>
                            <div className="col-sm-4">
                                <div className="card-box tilebox-one"><i className="icon-layers float-right text-muted"></i>
                                    <h6 className="text-muted text-uppercase mt-0">Points Earned:</h6>
                                    <h2 className="" data-plugin="counterup">{Total_points}</h2></div>
                            </div>

                            <div className="col-sm-4">

                                <div className="card-box tilebox-one"><i className="icon-paypal float-right text-muted"></i>
                                    <h6 className="text-muted text-uppercase mt-0">Total donations:</h6>
                                    <h2 className=""><span data-plugin="counterup">{Total_len}</span></h2></div>
                            </div>
                            <h4 className="header-title mt-0 mb-3">Your contribution towards making world a better place:</h4>
                            <div className="col-sm-4">
                                <div className="card-box tilebox-one"><i className="icon-rocket float-right text-muted"></i>
                                    <h6 className="text-muted text-uppercase mt-0">Carbon emmision saved:</h6>
                                    <h2 className="" data-plugin="counterup">1</h2></div>
                            </div>

                            <div className="col-sm-4">
                                <div className="card-box tilebox-one"><i className="icon-rocket float-right text-muted"></i>
                                    <h6 className="text-muted text-uppercase mt-0">Food wastage saved:</h6>
                                    <h2 className="" data-plugin="counterup">1</h2></div>
                            </div>

                            <div className="col-sm-4">
                                <div className="card-box tilebox-one"><i className="icon-rocket float-right text-muted"></i>
                                    <h6 className="text-muted text-uppercase mt-0">Hungers fed:</h6>
                                    <h2 className="" data-plugin="counterup">1</h2></div>
                            </div>

                        </div>


                        <div className="card-box">
                            <h4 className="header-title mt-0 mb-3">This will be your log book</h4>
                            <div className="">
                                <div className="">
                                    <h5 className="text-custom">Work In Progress....<br />It will be used in making your project file</h5>
                                    <p className="text-muted font-13 mb-0">This is your custom gallery.Here you will be uploading pictures that you will take whenever you volunteer in any event or donate anything.</p>
                                </div>



                            </div>
                        </div>
                        <div className="card-box">
                            <h4 className="header-title mb-3">Your Points history:</h4>
                            <div className="table-responsive">
                                <table className="table">
                                    <thead>
                                        <tr>
                                            <th>/</th>
                                            <th>Donation type</th>


                                            <th>Total Donation</th>
                                            <th>Points earned</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Book</td>
                                            <td><span className="label label-info">{B_len}</span></td>
                                            <td>{B_points}</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Food</td>

                                            <td><span className="label label-success">{F_len}</span></td>
                                            <td>{F_points}</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>Cloth</td>

                                            <td><span className="label label-pink">{C_len}</span></td>
                                            <td>{C_points}</td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>Medicine</td>

                                            <td><span className="label label-purple">{M_len}</span></td>
                                            <td>{M_points}</td>
                                        </tr>
                                        <tr>
                                            <td>5</td>
                                            <td>Toy</td>

                                            <td><span className="label label-warning">{T_len}</span></td>
                                            <td>{T_points}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div className="card-box">
                            <h4 className="header-title mb-3">Taken items:</h4>
                            <p className="header-title mb-3">Items that user has taken will be shown here...</p>
                            <div className="table-responsive">
                                <table className="table">
                                    <thead>
                                        <tr>
                                            <th>/</th>
                                            <th>Type</th>


                                            <th>Item name</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Book</td>
                                            <td><span className="label label-info">working on it</span></td>
                                            <td>5-6-2022</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Food</td>

                                            <td><span className="label label-success">working on it</span></td>
                                            <td>5-6-2022</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>Cloth</td>

                                            <td><span className="label label-pink">working on it</span></td>
                                            <td>5-6-2022</td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>Medicine</td>

                                            <td><span className="label label-purple">working on it</span></td>
                                            <td>5-6-2022</td>
                                        </tr>
                                        <tr>
                                            <td>5</td>
                                            <td>Toy</td>

                                            <td><span className="label label-warning">working on it</span></td>
                                            <td>5-6-2022</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>

    )
}


export default Profile
