"use strict"

let currentUser = null;

fetch("/api/admin/current_user")
    .then(dataJSON => dataJSON.json())
    .then(dataJSON => {
        console.log(dataJSON); // TODO log
        currentUser = dataJSON;

        // заполнение верхней навигационной панели
        document.getElementById("id-navbar-username").innerText = currentUser.username;
        document.getElementById("id-navbar-roles").innerText = rolesToString(currentUser);

        // заполнение данных пользователя в таблице
        showUser(currentUser);
    });

function rolesToString(user) {
    return user.roles.map(
        role => role.name.slice("ROLE_".length, role.name.length)
    ).join(" ");
}

function showUser(user) {
    let stringCodeHtml = "";
    const td_HTML = "<td>";
    for (let userKey in user) {
        if (userKey === "roles") {
            stringCodeHtml += td_HTML.concat(rolesToString(user));
        } else {
            stringCodeHtml += td_HTML.concat(user[userKey]);
        }
    }
    document.getElementById("id-table-tr").innerHTML = stringCodeHtml;
}
