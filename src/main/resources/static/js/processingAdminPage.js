"use strict"

let currentUser = null;
let usersTable = [];

// Получаем авторизованного пользователя
fetch("/api/admin/current_user")
    .then(dataJSON => dataJSON.json())
    .then(dataJSON => {
        currentUser = dataJSON;

        // заполнение верхней навигационной панели
        document.getElementById("id-navbar-username").innerText = currentUser.username;
        document.getElementById("id-navbar-roles").innerText = rolesToString(currentUser);

        // заполнение данных о пользователе в таблице на вкладке "User"
        document.getElementById("id-user-table-tr").innerHTML = showUser(currentUser);
    });

// получаем список пользователей из бд
fetch("api/admin/users")
    .then(dataJSON => dataJSON.json())
    .then(dataJSON => {
        if (dataJSON != null) {
            dataJSON.forEach(user => {
                usersTable.push(user);
            });
        }

        // заполнение таюлицы юзеров на вкладке "Admin"
        document.getElementById("id-all-users-table-tbody").innerHTML = showAllUsers(usersTable);
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
    return stringCodeHtml;
}

// функция заполнения строк таблицы
function showAllUsers(array) {
    let stringCodeHtml = "";
    const tr_HTML = "<tr>";
    array.forEach(user => {
        stringCodeHtml += tr_HTML.concat(showUser(user));
    });
    return stringCodeHtml;
}