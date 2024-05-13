function addNew(){
    let modal = document.getElementById("save-modal");
    modal.style.display = "block";
}

// todo : is just for person - its not generic
async function getDataForEdit(url, id) {
    const response = await fetch(url + "/" + id,
        {
            method: "GET"
        }
    );
    if (!response.ok) {
        alert("Error : " + response.status + "\n" + (await response.text()).toString());
    } else {
        // todo : make it automate
        let contact = JSON.parse(await response.text());
        let modal = document.getElementById("edit-modal");
        modal.style.display = "block";

        let idEdit = document.querySelector("#edit-form #id");
        idEdit.value = contact.id;

        let nameEdit = document.querySelector("#edit-form div :nth-child(1)");
        nameEdit.id = "edit-name";
        nameEdit.value = contact.title;

        let familyEdit = document.querySelector("#edit-form div :nth-child(2)");
        // familyEdit.id = "edit-family";
        familyEdit.value = contact.phoneNumber;
    }
}

function closeModal(){
    const saveModal = document.getElementById("save-modal");
    const editModal = document.getElementById("edit-modal");
    saveModal.style.display = "none";
    editModal.style.display = "none";
}

