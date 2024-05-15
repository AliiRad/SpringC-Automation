function addNew(){
    let modal = document.getElementById("save-modal");
    modal.style.display = "block";
}

// todo : is just for timesheet - its not generic
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
        //todo : employee and manager
        let timesheet = JSON.parse(await response.text());
        let modal = document.getElementById("edit-modal");
        modal.style.display = "block";

        let idEdit = document.querySelector("#edit-form #id");
        idEdit.value = timesheet.id;

        let dateEdit = document.querySelector("#edit-form div :nth-child(1)");
        dateEdit.id = "edit-date";
        dateEdit.value = timesheet.date;

        let regularTimeInEdit = document.querySelector("#edit-form div :nth-child(2)");
        regularTimeInEdit.value = timesheet.regularTimeIn;

        let regularTimeOutEdit = document.querySelector("#edit-form div :nth-child(3)");
        regularTimeOutEdit.value = timesheet.regularTimeOut;

        let overTimeInEdit = document.querySelector("#edit-form div :nth-child(4)");
        overTimeInEdit.value = timesheet.overTimeIn;

        let overTimeOutEdit = document.querySelector("#edit-form div :nth-child(5)");
        overTimeOutEdit.value = timesheet.overTimeOut;

        let employeeEdit = document.querySelector("#edit-form div :nth-child(6)");
        employeeEdit.value = timesheet.employee;

        let managerEdit = document.querySelector("#edit-form div :nth-child(7)");
        managerEdit.value = timesheet.manager;

    }
}

function closeModal(){
    const saveModal = document.getElementById("save-modal");
    const editModal = document.getElementById("edit-modal");
    saveModal.style.display = "none";
    editModal.style.display = "none";
}