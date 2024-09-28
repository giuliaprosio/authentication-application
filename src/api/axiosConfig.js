/**
 * Create a connection to the SpringBoot endpoints
 */

import axios from "axios";

const API_BASE_URL = "http://localhost:8080"; 

class axiosConfig {
    register(user) {
        const url = `${API_BASE_URL}/register`;
        console.log(url);
        return axios.post(`${API_BASE_URL}/register`, user); 
    
    }

    login(credentials) {
        return axios.post(`${API_BASE_URL}/login`, credentials); 
    }

   
}

export default new axiosConfig(); 