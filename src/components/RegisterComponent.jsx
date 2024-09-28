import React, { useState } from "react";
import { useNavigate, Link} from "react-router-dom"; 
import api from "../api/axiosConfig"; 
import "bootstrap/dist/css/bootstrap.min.css"; 
import axiosConfig from "../api/axiosConfig";

const RegisterComponent = () => {

    //state definition 
    const [id, setId] = useState(""); 
    const [username, setUsername] = useState(""); 
    const [email, setEmail] = useState(""); 
    const [password, setPassword] = useState(""); 
    const [secondPassword, setSecondPassword] = useState("");
    const [message, setMessage] = useState(""); 
    const navigate = useNavigate(); 

    //handlers 
    async function register(event) {
        event.preventDefault(); 
        console.log({username, email, password, secondPassword}); 
        const response = await axiosConfig.register({username, email, password, secondPassword});
        setMessage(response.data); 
        if(response.data === "added") {
            navigate("/login")
        }
        console.log(response.data)
        

        //reset state
        setId("");
        setUsername("");
        setEmail("");
        setPassword("");
        setSecondPassword("");
        
    }

    //jsx 

    return (
        <div className="container mt-4">
            <form>
                <div className="form-gourp my-2">
                    <input
                        type="text"
                        className="form-control"
                        hidden 
                        value={id}
                        onChange={e => setId(e.target.value)} />
                    <label>Username</label>
                    <input 
                    type="text"
                    className="form-control"
                    value={username}
                    placeholder="Enter username"
                    onChange={e => setUsername(e.target.value)} />
                </div>
                <div className="form-group mb-2">
                    <label>Email</label>
                    <input
                        type="text"
                        className="form-control"
                        value={email}
                        placeholder="Enter email"
                        onChange={e => setEmail(e.target.value)}
                    />
                </div>
                <div className="form-group mb-2">
                    <label>Password</label>
                    <input
                        type="password"
                        className="form-control"
                        value={password}
                        placeholder="Enter password"
                        onChange={e => setPassword(e.target.value)}
                    />
                </div>
                <div className="form-group mb-2">
                    <label>Second Password</label>
                    <input
                        type="password"
                        className="form-control"
                        value={secondPassword}
                        placeholder="Enter password again"
                        onChange={e => setSecondPassword(e.target.value)}
                    />
                </div>

                <div>
                    <button className="btn btn-primary m-4" onClick={register}>
                        Register
                    </button>
                </div>
            </form>
        </div>
    );
}; 

export default RegisterComponent; 