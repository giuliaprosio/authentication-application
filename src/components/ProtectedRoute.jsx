import { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import axiosConfig from "../api/axiosConfig";

const ProtectedRoute = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(null);  // null means checking, true means authenticated, false means not authenticated

  useEffect(() => {
    const checkAuth = async () => {
      try {
        const response = await axiosConfig.home();  // Make a request to the /home or a JWT validation endpoint
        if (response.status === 200) {
          setIsAuthenticated(true);  // User is authenticated
        }
      } catch (error) {
        setIsAuthenticated(false);  // User is not authenticated
      }
    };

    checkAuth();
  }, []);  // Run this effect when the component mounts

  // Render nothing while checking authentication
  if (isAuthenticated === null) {
    return <div>Loading...</div>;
  }

  // If authenticated, render the children (e.g., HomeComponent)
  if (isAuthenticated === true) {
    return children;
  }

  // If not authenticated, redirect to login
  return <Navigate to="/login" />;
};

export default ProtectedRoute;
