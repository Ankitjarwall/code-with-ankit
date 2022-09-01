import React from "react";
import { Route,BrowserRouter,Routes} from "react-router-dom";

import Home from "./components/home"



import Login from "./components/login";
import Register from "./components/register";

import Give from "./components/give"
import Givebook from "./components/givebook"
import Givefood from "./components/givefood"
import Givecloth from "./components/givecloth"
import Givemed from "./components/givemed"
import Givetoy from "./components/givetoy"

import Take from "./components/take"



function App() {
 return( 
   <div className="App">
     <BrowserRouter>
     
     <Routes>
     <Route path="/" exact element={<Home />}/>
       <Route path="/login"  element={<Login />}/>
       <Route path="/register" element={<Register />}/>
       <Route path="/give" element={<Give />}/>
       <Route path="/give/book" element={<Givebook />}/>
       <Route path="/give/food" element={<Givefood />}/>
       <Route path="/give/cloth" element={<Givecloth />}/>
       <Route path="/give/heal" element={<Givemed />}/>
       <Route path="/give/play" element={<Givetoy />}/>

       <Route path="/take" element={<Take />}/>
      

       

     
       </Routes>
  </BrowserRouter>
 </div>
 );
}

export default App;
