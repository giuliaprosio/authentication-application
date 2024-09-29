/**
 * Create a connection to the SpringBoot endpoints
 */

import axios from "axios";
import qs from 'qs'; 

const API_BASE_URL = "http://localhost:8080"; 

class axiosConfig {
    register(user) {
        const url = `${API_BASE_URL}/register`;
        console.log(url);
        return axios.post(`${API_BASE_URL}/register`, user); 
    
    }

   login(credentials) {
        console.log(credentials); 
        console.log(API_BASE_URL); 
        return axios.post(`${API_BASE_URL}/login`, qs.stringify(credentials)); 
    }

   
}

export default new axiosConfig(); 