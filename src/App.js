import "bootstrap/dist/css/bootstrap.css"; 
import { useState } from "react"; 
import { BrowserRouter as Router, Route, Routes } from "react-router-dom"; 
import logo from './logo.svg'; 
import './App.css'; 
import RegisterComponent from "./components/RegisterComponent"; 
import LoginComponent from "./components/LoginComponent"; 
import HomeComponent from "./components/HomeComponent"; 

function App() { 
  const [darkMode, setDarkMode] = useState(false);

  function toggleTheme() {
    setDarkMode(!darkMode);
  }

  return ( 
    <div className={darkMode ? 'dark-mode' : 'light-mode'}> 
      <Router> 
        <div className="App"> 
          <header className="App-header"> 
            <img src={logo} className="App-logo" alt="logo" /> 
            <button onClick={toggleTheme} className="btn-toggle-mode"> 
              {darkMode ? 'Switch to Light Mode' : 'Switch to Dark Mode'}
            </button>
          
              
                <Routes> 
                  <Route path="/" element={<LoginComponent />} /> 
                  <Route path="/register" element={<RegisterComponent />} /> 
                  <Route path="/login" element={<LoginComponent />} /> 
                  <Route path="/home" element={<HomeComponent />} /> 
                </Routes> 
            
          </header> 
        </div> 
      </Router> 
    </div> 
  ); 
}

export default App;
