import React from 'react';
import Pizza from './images/Pizza.jpg';
import './main.css';
import './FoodCard.css';

export default function FoodCard(props) {
    return (

        <div >
            <div className="row row-cols-1 row-cols-md-6 g-4 allinone foodcard-top">

                <div className="col">
                    <div className="card">
                        <img src={Pizza} className="card-img-top" alt="pizza" />
                        <div className="card-body">
                            <h5 className="card-title">Pizza</h5>
                        </div>
                    </div>
                </div>


                <div className="col">
                    <div className="card">
                        <img src={Pizza} className="card-img-top" alt="pizza" />
                        <div className="card-body">
                            <h5 className="card-title">Pizza</h5>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <img src={Pizza} className="card-img-top" alt="pizza" />
                        <div className="card-body">
                            <h5 className="card-title">Pizza</h5>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <img src={Pizza} className="card-img-top" alt="pizza" />
                        <div className="card-body">
                            <h5 className="card-title">Pizza</h5>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <img src={Pizza} className="card-img-top" alt="pizza" />
                        <div className="card-body">
                            <h5 className="card-title">Pizza</h5>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <img src={Pizza} className="card-img-top" alt="pizza" />
                        <div className="card-body">
                            <h5 className="card-title">Pizza</h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>



    )
}
