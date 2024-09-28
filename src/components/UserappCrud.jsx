import { useState } from "react";
import api from "../api/axiosConfig"; 

const UserappCrud = ({ load }) => {

    //state definition 
    const [id, setId] = useState(""); 
    const [username, setUsername] = useState(""); 
    const [email, setEmail] = useState(""); 
    const [password, setPassword] = useState(""); 
    const [secondPassword, setSecondPassword] = useState("");

    //handlers 
    async function register(event) {
        event.preventDefault(); 
        await api.post("/register", {
            username: username, 
            email: email, 
            password: password, 
            secondPassword: secondPassword,
        }); 
        alert("Registered");

        //reset state
        setId("");
        setUsername("");
        setEmail("");
        setPassword("");
        setSecondPassword("");
        load();
    }

    /*async function login(event) {
        event.preventDefault(); 
        await api.post("/login", {
            username: username, 
            password: password,
        }); 

        setUsername(""); 
        setPassword(""); 
    }*/

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
                        type="text"
                        className="form-control"
                        value={password}
                        placeholder="Enter password"
                        onChange={e => setPassword(e.target.value)}
                    />
                </div>
                <div className="form-group mb-2">
                    <label>SecondPassword</label>
                    <input
                        type="text"
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

export default UserappCrud; 