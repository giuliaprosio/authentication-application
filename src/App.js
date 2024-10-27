// App.js
import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginComponent from "./components/LoginComponent";
import RegisterComponent from "./components/RegisterComponent"
import ProxyRequestComponent from "./components/ProxyRequestComponent";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<LoginComponent />} />
                <Route path='/register' element={<RegisterComponent />} />
                <Route path="/*" element={<ProxyRequestComponent />} /> {/* Wildcard to forward all routes */}
            </Routes>
        </Router>
    );
}

export default App;
