// ProxyRequestComponent.js
import React, { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "../api/axiosConfig";

const ProxyRequestComponent = () => {
    const [responseData, setResponseData] = useState(null);
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        const fetchData = async () => {
            try {
                // Send GET request to the current location pathname
                const response = await axios.get(location.pathname);
                
                if (response.data) {
                    navigate(response.data);
                } else {
                    // Otherwise, set the response data to display it
                    setResponseData(response.data);
                }
            } catch (error) {
                // If unauthorized, redirect to login
                if (error.response && error.response.status === 401) {
                    navigate("/login");
                } else {
                    // Set error message if there's another type of error
                    setResponseData({ error: error.message });
                }
            }
        };

        // Fetch data every time the location changes
        fetchData();
    }, [location, navigate]);

    return (
        <div>
            {responseData ? (
                <div>
                    {/* Display data if no redirection */}
                    <h3>Response:</h3>
                    <pre>{JSON.stringify(responseData, null, 2)}</pre>
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
};

export default ProxyRequestComponent;
