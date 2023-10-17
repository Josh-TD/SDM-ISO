import axios from 'axios';
import React from 'react';
const USERS_API_URL = "http://localhost:8080/users";
function APIService() {
    let users = [];
    axios.get(USERS_API_URL)
        .then((response) => {
            users = response.data;
            console.log(response.data)
        });
    return users;
}
export default APIService;