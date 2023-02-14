import React from 'react'
import Food from '../component/images/Food.jpg';

export default function Header() {
    return (
        <div className="card allinone">
            <div className="card-header">
                <img src={Food} className="img-fluid" alt="food image"/>
            </div>
        </div>
    )
}


