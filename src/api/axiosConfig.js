// axiosConfig.js
import axios from "axios";
import qs from "qs";

// Base URL for all API requests
const API_BASE_URL = "http://localhost:8080";

// Create an axios instance with interceptors
const instance = axios.create({
    baseURL: API_BASE_URL,
});

// Request Interceptor for adding JWT token if present
instance.interceptors.request.use((config) => {
    const token = localStorage.getItem("jwtToken");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, (error) => Promise.reject(error));

// Response Interceptor for handling unauthorized errors globally
instance.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response && error.response.status === 401) {
            localStorage.removeItem("jwtToken");
            window.location.href = "/login";
        }
        return Promise.reject(error);
    }
);

const axiosConfig = {
    // Method for logging in a user
    async login(credentials) {
        const response = await instance.post(
            "/login", 
            qs.stringify(credentials), 
            { headers: { "Content-Type": "application/x-www-form-urlencoded" } }
        );
        if (response.status === 200 && response.headers.authorization) {
            const token = response.headers.authorization.split(" ")[1];
            localStorage.setItem("jwtToken", token);
        }
        return response;
    },

    // Method for registering a new user with application/json content type
    register(user) {
        return instance.post("/register", user, {
            headers: { "Content-Type": "application/json" },
        });
    },

    // General-purpose request method for other endpoints
    request(endpoint, method = "get", data = {}) {
        const contentType = method === "post" ? "application/json" : "application/x-www-form-urlencoded";
        return instance({
            url: endpoint,
            method,
            data: contentType === "application/json" ? data : qs.stringify(data),
            headers: { "Content-Type": contentType },
        });
    },
};

export default axiosConfig;
