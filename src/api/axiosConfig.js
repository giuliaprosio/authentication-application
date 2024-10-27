/**
 * Create a connection to the SpringBoot endpoints
 */

/* this works partially. Now all get requests sent to backend but 
the post requests (login and register) are not working.
*/

import axios from "axios";
import qs from 'qs'; 

const API_BASE_URL = "http://localhost:8080"; 

const instance = axios.create({
    baseURL: API_BASE_URL, 
    headers: {
        "Content-Type": "application/x-www-form-urlencoded"
    }
}); 

instance.interceptors.request.use((config) => {
    const token = localStorage.getItem("jwtToken"); 
    console.log("token: ", token); 
    if(token) {
        config.headers.Authorization = `Bearer ${token}`; 
    }
    return config; 
}, (error) =>  Promise.reject(error)); 

instance.interceptors.response.use((response) => {
    // check if the response contains the auth token (from login)
    if(response.headers.authorization) {
        const token = response.headers.authorization.split(' ')[1]; 
        localStorage.setItem("jwtToken", token); 
    }

    return response; 
}, (error) => {
    if(error.response && error.response.status === 401) {
        localStorage.removeItem("jwtToken"); 
        window.location.href = "/login"; 
    }
    return Promise.reject(error); 
})

class axiosConfig {
    register(user) {
        const url = `${API_BASE_URL}/register`;
        console.log(url);
        return axios.post(`${API_BASE_URL}/register`, user); 
    
    }

   login(credentials) {
        return instance.post(`${API_BASE_URL}/login`, qs.stringify(credentials)); 
    }
   
}

export default instance;