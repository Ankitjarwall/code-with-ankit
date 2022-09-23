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
import Takebook from "./components/takebook"
import Takefood from "./components/takefood"
import Takecloth from "./components/takecloth"
import Takemed from "./components/takemed"
import Taketoy from "./components/taketoy"

import Onebook from "./components/onebook"
import Onefood from "./components/onefood"
import Onecloth from "./components/onecloth"
import Onemed from "./components/onemed"
import Onetoy from "./components/onetoy"
import Profile from "./components/profile";
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
       <Route path="/take/book" element={<Takebook />}/>
       <Route path="/take/food" element={<Takefood />}/>
       <Route path="/take/cloth" element={<Takecloth />}/>
       <Route path="/take/heal" element={<Takemed />}/>
       <Route path="take/play" element={<Taketoy />}/>

       <Route path="/take/book/:id" element={<Onebook/>}/>
       <Route path="/take/food/:id" element={<Onefood/>}/>
       <Route path="/take/cloth/:id" element={<Onecloth/>}/>
       <Route path="/take/heal/:id" element={<Onemed/>}/>
       <Route path="/take/play/:id" element={<Onetoy/>}/>

       <Route path="/profile" element={<Profile />}/>
       </Routes>
  </BrowserRouter>
 </div>
 );
}

export default App;
