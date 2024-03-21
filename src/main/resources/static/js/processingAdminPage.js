"use strict"

let currentUser = null;
let usersTable = [];

// Получаем авторизованного пользователя
fetch("/api/admin/current_user")
    .then(dataJSON => dataJSON.json())
    .then(dataJSON => {
        console.log(dataJSON); // TODO log
        currentUser = dataJSON;

        // заполнение верхней навигационной панели
        document.getElementById("id-navbar-username").innerText = currentUser.username;
        document.getElementById("id-navbar-roles").innerText = rolesToString(currentUser);

        // заполнение данных о пользователе в таблице на вкладке "User"
        showUser(currentUser);
    });

// получаем список пользователей из бд
fetch("api/admin/users")
    .then(dataJSON => dataJSON.json())
    .then(dataJSON => {
        console.log(dataJSON); // TODO log
        if (dataJSON != null) {
            dataJSON.forEach(user => {
                usersTable.push(user);
            });
        }
        usersTable.forEach(user => {
            console.log(user);
        });
    });

// функция преобразования коллекции ролей в строковое представление
function rolesToString(user) {
    return user.roles.map(
        role => role.name.slice("ROLE_".length, role.name.length)
    ).join(" ");
}

// функция заполнения данных о пользователе в таблице
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
