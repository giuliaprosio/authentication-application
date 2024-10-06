/**
 * Create a connection to the SpringBoot endpoints
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
}, (error) => {
    return Promise.reject(error); 
}); 

class axiosConfig {
    register(user) {
        const url = `${API_BASE_URL}/register`;
        console.log(url);
        return axios.post(`${API_BASE_URL}/register`, user); 
    
    }

   login(credentials) {
        return instance.post(`${API_BASE_URL}/login`, qs.stringify(credentials)); 
    }

    home() {
        return instance.get(`${API_BASE_URL}/home`)
    }


   
}

export default new axiosConfig(); 