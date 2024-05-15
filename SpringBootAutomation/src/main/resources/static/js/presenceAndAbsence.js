function addNew(){
    let modal = document.getElementById("save-modal");
    modal.style.display = "block";
}

// todo : is just for presenceAndAbsence - its not generic
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
        let presenceAndAbsence = JSON.parse(await response.text());
        let modal = document.getElementById("edit-modal");
        modal.style.display = "block";

        let idEdit = document.querySelector("#edit-form #id");
        idEdit.value = presenceAndAbsence.id;

        let employeeEdit = document.querySelector("#edit-form div :nth-child(1)");
        employeeEdit.id = "edit-employee";
        employeeEdit.value = presenceAndAbsence.employee;

        let dateEdit = document.querySelector("#edit-form div :nth-child(2)");
        dateEdit.value = presenceAndAbsence.date;

        let enterTimeEdit = document.querySelector("#edit-form div :nth-child(3)");
        enterTimeEdit.value = presenceAndAbsence.enterTime;

        let exitTimeEdit = document.querySelector("#edit-form div :nth-child(4)");
        exitTimeEdit.value = presenceAndAbsence.exitTime;
    }
}

function closeModal(){
    const saveModal = document.getElementById("save-modal");
    const editModal = document.getElementById("edit-modal");
    saveModal.style.display = "none";
    editModal.style.display = "none";
}