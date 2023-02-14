// import './App.css';
// import { useState } from 'react';
import Nav from './component/Nav';
import Home from './component/Home';
import Contact from './component/Contact';
import About from './component/About';
import { useState } from 'react';

function App() {
  const [page, setPage] = useState("Nav");

  // let screen = <Nav page={page} setPage={setPage} switchScreen={switchScreen} />
  
  const switchScreen = (scr) => {
    switch (scr) {
      case 'Home': setPage('Home')
        break;
      case 'About': setPage('About')
        break;
      case 'Contact': setPage('Contact')
    }
  }
  return (
    <>
      { page == 'Nav' && (<Nav switchScreen={switchScreen}/>) ||page=='Home' && (<Home />) ||page=='About'&&(<About/>)||page=='Contact'&&(<Contact/>)}
      
    </>

  )
  

}

export default App;
