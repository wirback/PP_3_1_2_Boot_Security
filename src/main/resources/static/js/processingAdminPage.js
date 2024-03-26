"use strict"

let currentUser = null;
let usersList = [];
let rolesList = [];

document.getElementById("id-new-user-form").addEventListener("submit", addNewUser);

getCurrentUser(); // Получаем авторизованного пользователя
getAllUsers(); // получаем список пользователей из бд
getRoles(); // получаем список ролей из бд


// Функция подгрузки авторизованного пользователя
function getCurrentUser() {
    fetch("/api/admin/current_user")
        .then(dataJSON => dataJSON.json())
        .then(dataJSON => {
            currentUser = dataJSON;

            // заполнение верхней навигационной панели
            document.getElementById("id-navbar-username").innerText = currentUser.username;
            document.getElementById("id-navbar-roles").innerText = rolesToString(currentUser.roles);

            // заполнение данных о пользователе в таблице на вкладке "User"
            showUser(currentUser);
        });
}

function getAllUsers() {
    fetch("/api/admin/users")
        .then(dataJSON => dataJSON.json())
        .then(dataJSON => {
            if (dataJSON != null) {
                dataJSON.forEach(user => {
                    usersList.push(user);
                });
            }

            // заполнение таюлицы юзеров на вкладке "Admin"
            showAllUsers(usersList);
        });
}

// функция подгрузки списка доступных ролей
function getRoles() {
    fetch("/api/admin/roles")
        .then(dataJSON => dataJSON.json())
        .then(dataJSON => {
            if (dataJSON != null) {
                dataJSON.forEach(role => {
                    rolesList.push(role);
                });
            }

            showRoles(rolesList);
        });
}

// функция заполнения данных о пользователе в таблице
function showUser(user) {
    const trUser = document.getElementById("id-user-table-tr");
    let tdString = "";
    for (const userKey in user) {
        const td = document.createElement("td");
        if (userKey === "roles") {
            td.textContent = rolesToString(user[userKey]);
        } else {
            td.textContent = user[userKey];
        }
        trUser.append(td);
    }
    return tdString;
}

// функция заполнения строк таблицы пользователей
function showAllUsers(array) {
    const tbodyUsers = document.getElementById("id-all-users-table-tbody");
    // tbodyUsers.innerText = ''
    // tbodyUsers.innerHTML = ''
    // $(tbodyUsers).empty();
    $(".tbody-table").empty();
    const tbodyId = tbodyUsers.getAttribute("id");
    let count = 0;
    array.forEach(user => {
        const tr = document.createElement("tr");
        tr.setAttribute("class", "row-table-users");
        let trId = tbodyId + ++count;
        tr.setAttribute("id", trId);
        tbodyUsers.append(tr);

        for (const userKey in user) {
            const td = document.createElement("td");
            td.setAttribute("id", "id-td-" + userKey);
            if (userKey === "roles") {
                td.append(rolesToString(user[userKey]));
            } else {
                td.append(user[userKey]);
            }
            tr.append(td);
        }
        tbodyUsers.append(tr);
    });
}



function showRoles(array) {
    array.forEach(role => {
        const option = document.createElement("option");
        option.setAttribute("value", role.id);
        option.append(role.name.slice("ROLE_".length, role.name.length));
        document.getElementById("id-all-roles").append(option);
    });
}

function rolesToString(roles) {
    return roles.map(
        role => role.name.slice("ROLE_".length, role.name.length)
    ).join(" ");
}

function setRoles(ids) {
    let roles = [];
    ids.forEach(id => {
        for (const roleKey in rolesList) {
            if (rolesList[roleKey].id === +id) {
                roles.push(rolesList[roleKey]);
            }
        }
    })

    return roles;
}

function addNewUser(form) {
    form.preventDefault();
    let newUserForm = new FormData(form.target);
    let data = {
        firstName: newUserForm.get('firstName'),
        lastName: newUserForm.get('lastName'),
        age: newUserForm.get("age"),
        username: newUserForm.get('username'),
        password: newUserForm.get('password'),
        roles: setRoles(newUserForm.getAll('roles'))
    };

    let response = fetch('/api/admin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            // 'X-CSRFToken': document.querySelector('[name=csrfmiddlewaretoken]').value
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {

    }
    // .then()
    //     .then(() => getAllUsers());
    form.target.reset();

    getAllUsers()
    document.getElementById("users-table-button").click();
}
