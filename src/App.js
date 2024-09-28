import "bootstrap/dist/css/bootstrap.css"; 
import api from "./api/axiosConfig"; 
import { useEffect, useState } from "react";
import logo from './logo.svg';
import './App.css';
import UserappCrud from "./components/UserappCrud";

function App() {
   useEffect(() => {
    (async () => await load())(); 
   }, []); 

   async function load() {
    const result = await api.get("/register"); 
    
   }
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div>
          <h1 className="text-center">Register</h1>
          <UserappCrud load={load} />
        </div>
      </header>
    </div>
  );
}


export default App;
