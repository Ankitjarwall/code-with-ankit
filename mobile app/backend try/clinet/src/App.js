import Home from "./component/Home";
import Login from "./component/Login";
import Explore from "./component/Explore";
import { useState } from "react";

function App() {
    const [page, setpage] = useState("newpage");

    return (
        <div className='App' >

            <h1 className="heading">kingstagram</h1>
            <div className="content-center">
                <button className="bg-green" onClick={() => { setpage("Home") }} >Home</button>
                <button className="bg-green" onClick={() => { setpage("Explore") }} >Explore</button>
                <button className="bg-green" onClick={() => { setpage("login") }}>log in</button>
            </div>
            {getpageload(page)};
        </div>
    )
    
}

const getpageload = (page) => {
    switch (page) {
        case "Home":
            return (<Home />)
        case "login":
            return (<Login />)
        case "Explore":
            return (<Explore />)
    }
}



export default App