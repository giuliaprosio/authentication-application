import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom"; 
import axiosConfig from "../api/axiosConfig";
import "bootstrap/dist/css/bootstrap.min.css"; 

const LoginComponent = () => {
    const [username, setUsername] = useState(""); 
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState(""); 
    const navigate = useNavigate(); 

    const handleLogin = async (e) => {
        e.preventDefault(); 
        
        const response = await axiosConfig.login({username, password}); 
        if( response.data === "login") {
            navigate("/Chome"); 
        }
        console.log(response.data); 
    }; 

    return ( <div className="container mt-5">
        <div className="row justify-content-center">
            <div className="col-md-6">
                <div className="card">
                    <div className="card-header">Login Form</div>
                    <div className="card-body">
                        {message && <div className="alert alert-danger">{message}</div>}
                        <form onSubmit={handleLogin}>
                            <div className="form-group">
                                <label>Username</label>
                                <input
                                    type="tedxt"
                                    className="form-control"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                />
                            </div>
                            <div className="form-group">
                                <label>Password</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                />
                            </div>
                            <button type="submit" className="btn btn-primary">Login</button>
                        </form>
                        <div className="mt-3">
                            <span>Not registered? <Link to="/register">Register here</Link></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
);
}

export default LoginComponent; 