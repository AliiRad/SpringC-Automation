function addNew() {
    let modal = document.getElementById("save-modal");
    modal.style.display = "block";
}

async function getDataForEdit(url, id) {
    const response = await fetch(url + "/" + id,
        {
            method: "GET"
        }
    );
    if (!response.ok) {
        alert("Error : " + response.status + "\n" + (await response.text()).toString());
    } else {
        let appointmentDecree = JSON.parse(await response.text());
        let modal = document.getElementById("edit-modal");
        modal.style.display = "block";

        let idEdit = document.querySelector("#edit-form #id");
        idEdit.value = appointmentDecree.id;

        let personEdit = document.querySelector("#edit-form #edit-person");
        personEdit.id = "edit-person";
        personEdit.value = appointmentDecree.person.username;

        let typeOfEmploymentEdit = document.querySelector("#edit-form div :nth-child(1)");
        typeOfEmploymentEdit.id = "edit-typeOfEmployment";
        typeOfEmploymentEdit.value = appointmentDecree.typeOfEmployment;

        let organizationalUnitEdit = document.querySelector("#edit-form div :nth-child(2)");
        organizationalUnitEdit.id = "edit-organizationalUnit";
        organizationalUnitEdit.value = appointmentDecree.organizationalUnit;

        let jobTypeEdit = document.querySelector("#edit-form div :nth-child(3)");
        jobTypeEdit.id = "edit-jobType";
        jobTypeEdit.value = appointmentDecree.jobType;

        let professionalFieldEdit = document.querySelector("#edit-form div :nth-child(4)");
        professionalFieldEdit.id = "edit-professionalField";
        professionalFieldEdit.value = appointmentDecree.professionalField;

        let jobCodeEdit = document.querySelector("#edit-form div :nth-child(5)");
        jobCodeEdit.id = "edit-jobCode";
        jobCodeEdit.value = appointmentDecree.jobCode;

        let jobTitleEdit = document.querySelector("#edit-form div :nth-child(6)");
        jobTitleEdit.id = "edit-jobTitle";
        jobTitleEdit.value = appointmentDecree.jobTitle;

        let startDateEdit = document.querySelector("#edit-form div :nth-child(7)");
        startDateEdit.id = "edit-startDate";
        startDateEdit.value = appointmentDecree.startDate;

        let endDateEdit = document.querySelector("#edit-form div :nth-child(8)");
        endDateEdit.id = "edit-endDate";
        endDateEdit.value = appointmentDecree.endDate;

        let placeOfEmploymentEdit = document.querySelector("#edit-form div :nth-child(9)");
        placeOfEmploymentEdit.id = "edit-placeOfEmployment";
        placeOfEmploymentEdit.value = appointmentDecree.placeOfEmployment;

        let workingHoursEdit = document.querySelector("#edit-form div :nth-child(10)");
        workingHoursEdit.id = "edit-workingHours";
        workingHoursEdit.value = appointmentDecree.workingHours;

        let workingDayEdit = document.querySelector("#edit-form div :nth-child(11)");
        workingDayEdit.id = "edit-workingDay";
        workingDayEdit.value = appointmentDecree.workingDay;

        let appointmentTypeEdit = document.querySelector("#edit-form div :nth-child(12)");
        appointmentTypeEdit.id = "edit-appointmentType";
        appointmentTypeEdit.value = appointmentDecree.appointmentType;

        let appointmentDescriptionEdit = document.querySelector("#edit-form div :nth-child(13)");
        appointmentDescriptionEdit.id = "edit-appointmentDescription";
        appointmentDescriptionEdit.value = appointmentDecree.appointmentDescription;

    }
}

function closeModal() {
    const saveModal = document.getElementById("save-modal");
    const editModal = document.getElementById("edit-modal");
    saveModal.style.display = "none";
    editModal.style.display = "none";
}