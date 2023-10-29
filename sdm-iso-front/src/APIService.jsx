import axios from 'axios';
import React from 'react';
function APIService(url) {
    let users = [];
    axios.get(url)
        .then((response) => {
            users = response.data;
            console.log(response.data)
        });
    return users;
}
export default APIService;