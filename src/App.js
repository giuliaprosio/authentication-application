import "bootstrap/dist/css/bootstrap.css"; 
import api from "./api/axiosConfig"; 
import { useEffect, useState } from "react";
import logo from './logo.svg';
import './App.css';
import RegisterComponent from "./components/RegisterComponent";
import LoginComponent from "./components/LoginComponent";
import HomeComponent from "./components/HomeComponent";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom"; 
import axiosConfig from "./api/axiosConfig";


function App() {  

   async function load() {
    const result = await api.get("/login"); 
    
   }
  return (
    <Router>
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </header>
    </div>
    <div className="container">
      <Routes>
        <Route path="/" element={<LoginComponent/>} />
        <Route path="/register" element={<RegisterComponent/>} />
        <Route path="/login" element={<LoginComponent/>} />
        <Route path="/home" element={<HomeComponent/>} />
      </Routes>
    </div>
    </Router>
  );
}


export default App;
