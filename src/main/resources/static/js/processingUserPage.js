"use strict"

let currentUser = null;
let rolesAsText = null;


fetch("/api/user/show")
    .then(dataJSON => dataJSON.json())
    .then(dataJSON => {
        console.log(dataJSON); // TODO log
        currentUser = dataJSON;

        rolesAsText = currentUser.roles.map(
            role => role.name.slice("ROLE_".length, role.name.length)
        ).join(" ");

        // заполнение верхней навигационной панели
        document.getElementById("id-navbar-username").innerText = currentUser.username;
        document.getElementById("id-navbar-roles").innerText = rolesAsText;

        // заполнение данных пользователя в таблице
        document.getElementById("id-table-row-id").innerText = currentUser.id;
        document.getElementById("id-table-row-first-name").innerText = currentUser.firstName;
        document.getElementById("id-table-row-last-name").innerText = currentUser.lastName;
        document.getElementById("id-table-row-age").innerText = currentUser.age;
        document.getElementById("id-table-row-email").innerText = currentUser.username;
        document.getElementById("id-table-row-roles").innerText = rolesAsText;
    });
