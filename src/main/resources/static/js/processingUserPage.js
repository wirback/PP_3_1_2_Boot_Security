"use strict"

let currentUser = null;

fetch("/api/user/show")
    .then(dataJSON => dataJSON.json())
    .then(dataJSON => {
        console.log(dataJSON); // TODO logs
        currentUser = dataJSON;
        document.getElementById("id-navbar-username").innerText = currentUser.username;
        document.getElementById("id-navbar-roles").innerText = currentUser.roles.map(
            role => role.name.slice("ROLE_".length, role.name.length)
        ).join(" ");
    });