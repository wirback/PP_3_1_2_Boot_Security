'use strict'

const PREFIX_ROLE = "ROLE_";
const TBODY_ALL_USERS_TABLE = $('#id-all-users-table-tbody');
let newUserForm = document.forms["id-new-user-form"];


getCurrentUser();
getAllUser();
addNewUser();
getRoles();


// document.getElementById("new-user-button").onclick = () => {
//     getRoles($('#id-new-roles').empty());
// }


function rolesToString(roles) {
    return roles.map(
        role => role.name.replace(PREFIX_ROLE, "")
    ).join(" ");
}

// Функция подгрузки авторизованного пользователя
function getCurrentUser() {
    fetch("/api/admin/user")
        .then(response => response.json())
        .then(data => {
            // currentUser = data;

            // заполнение верхней навигационной панели
            document.getElementById("id-navbar-username").innerText = data.username;
            document.getElementById("id-navbar-roles").innerText = rolesToString(data.roles);

            // заполнение данных о пользователе в таблице на вкладке "User"
            const user = $(
                `<tr>
                    <td class="pt-3">${data.id}</td>
                    <td class="pt-3">${data.firstName}</td>
                    <td class="pt-3">${data.lastName}</td>
                    <td class="pt-3">${data.age}</td>
                    <td class="pt-3">${data.username}</td>
                    <td class="pt-3">${rolesToString(data.roles)}</td>
                </tr>`
            );
            $('#id-user-table-tbody').append(user);
        });
}



// Table
function getAllUser() {
    TBODY_ALL_USERS_TABLE.empty();
    fetch("api/admin/users")
        .then(response => response.json())
        .then(data => {
            data.forEach(user => {
                const users = $(
                    `<tr>
                        <td class="pt-3" id="userID">${user.id}</td>
                        <td class="pt-3" >${user.firstName}</td>
                        <td class="pt-3" >${user.lastName}</td>
                        <td class="pt-3" >${user.age}</td>
                        <td class="pt-3" >${user.username}</td>
                        <td class="pt-3" >${rolesToString(user.roles)}</td>
                        <td>
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#id-edit-modal" onclick="editModal(${user.id})">
                            Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete" onclick="deleteModal(${user.id})">
                                Delete
                            </button>
                        </td>
                    </tr>`
                );
                TBODY_ALL_USERS_TABLE.append(users);
            });
        })
}

// Create
function addNewUser() {
    newUserForm.addEventListener("submit", event => {
        event.preventDefault();

        let roles = [];
        for (let i = 0; i < newUserForm.roles.options.length; i++) {
            if (newUserForm.roles.options[i].selected) roles.push({
                id: newUserForm.roles.options[i].value,
                role: PREFIX_ROLE.concat(newUserForm.roles.options[i].text)
            });
        }

        fetch("api/admin", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: newUserForm.first_name.value,
                lastName: newUserForm.last_name.value,
                age: newUserForm.age.value,
                username: newUserForm.email.value,
                password: newUserForm.password.value,
                roles: roles
            })
        }).then(() => {
            event.target.reset();
            newUserForm.reset();
            $('#users-table-button').click();
            getAllUser();
        });
    });
}

// функция подгрузки списка доступных ролей
function getRoles() {
    const rolesNew = $('#id-new-roles').empty();
    const rolesEdit = $('#id-edit-roles').empty();
    const rolesDelete = $('#id-delete-roles').empty();
    fetch("/api/admin/roles")
        .then(response => response.json())
        .then(data => {
            data.forEach(role => {
                rolesNew.append($(`<option value="${role.id}">${role.name.replace(PREFIX_ROLE, "")}</option>`));
                rolesEdit.append($(`<option value="${role.id}">${role.name.replace(PREFIX_ROLE, "")}</option>`));
                rolesDelete.append($(`<option value="${role.id}">${role.name.replace(PREFIX_ROLE, "")}</option>`));
            });
        });
}


