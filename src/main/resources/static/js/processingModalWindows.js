'use strict'

let editForm = document.forms["id-edit-form"];
let deleteForm = document.forms["id-delete-form"];


// Запрос
async function getUserById(id) {
    let response = await fetch("api/admin/" + id);
    return await response.json();
}

async function showModalWindow(form, modal, id) {
    modal.show();
    let user = await getUserById(id);
    form.id.value = user.id;
    form.first_name.value = user.firstName;
    form.last_name.value = user.lastName;
    form.age.value = user.age;
    form.email.value = user.username;
    for (let i = 0; i < user.roles.length; i++) {
        form.roles.options[--user.roles[i].id].selected = true;
    }
}

// Edit
async function editModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#id-edit-modal'));
    await showModalWindow(editForm, modal, id);
    editUser();
}

function editUser() {
    editForm.addEventListener("submit", event => {
        event.preventDefault();
        let roles = [];
        for (let i = 0; i < editForm.roles.options.length; i++) {
            if (editForm.roles.options[i].selected) {
                roles.push({
                    id: editForm.roles.options[i].value,
                    name: PREFIX_ROLE.concat(editForm.roles.options[i].text)
                });
            }
        }

        fetch("api/admin/", {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: editForm.id.value,
                firstName: editForm.first_name.value,
                lastName: editForm.last_name.value,
                age: editForm.age.value,
                username: editForm.email.value,
                password: editForm.password.value,
                roles: roles
            })
        }).then(() => {
            event.target.reset();
            editForm.reset();
            $('#id-edit-modal-button-close').click();
            getAllUser();
        });
    });
}

// Delete
async function deleteModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#id-delete-modal'));
    await showModalWindow(deleteForm, modal, id);
    deleteUser();
}

function deleteUser() {
    deleteForm.addEventListener("submit", event => {
        event.preventDefault();
        fetch("api/admin/" + deleteForm.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            event.target.reset();
            deleteForm.reset();
            $('#id-delete-modal-button-close').click();
            getAllUser();
        });
    });
}