import React from 'react'

export default function Explore() {
    return (
        <div>Explore page</div>
    )
}


// function App() {

//     const [homestate, sethome] = useState("home");

//     return (

//         <div className='App' >

//             <h1 className="heading">kingstagram</h1>

//             <div className="content-center">

//                 <button className="bg-green" onClick={() => { sethome("home") }}>Home</button>

//                 <button className="bg-green" onClick={() => { sethome("explore") }}>Explore</button>

//                 <button className="bg-green" onClick={() => { sethome("login") }}>log in</button>
//             </div>
//             {getCorrectVisual(homestate, sethome)}
//         </div>
//     )
// }

// const getCorrectVisual = (homestate, sethome) => {
//     switch (homestate) {

//         case "home":
//             return (
//                 <Home sethome={sethome} />
//             )
//         case "login":
//             return (
//                 <Login />
//             )
//         case "explore":
//             return (
//                 <Explore />
//             )
//     }
// }