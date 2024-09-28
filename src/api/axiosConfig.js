/**
 * Create a connection to the SpringBoot endpoints
 */

import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8080/",
})