// App.js
import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginComponent from "./components/LoginComponent";
import RegisterComponent from "./components/RegisterComponent";
import HomeComponent from "./components/HomeComponent";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";  
import ProxyRequestComponent from "./components/ProxyRequestComponent";

function App() {
    const [isDarkMode, setIsDarkMode] = useState(false);

    const toggleDarkMode = () => setIsDarkMode(!isDarkMode);

    return (
        <div className={`App ${isDarkMode ? "dark-mode" : "light-mode"}`}>
            <Router>
                <button onClick={toggleDarkMode} className="btn-toggle-mode">
                    Switch to {isDarkMode ? "Light" : "Dark"} Mode
                </button>
                <Routes>
                    <Route path="/*" element={<ProxyRequestComponent />} />
                </Routes>
            </Router>
        </div>
    );
}

export default App;
