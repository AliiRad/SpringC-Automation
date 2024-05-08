async function findById(id) {
    let editModal = document.getElementById("editModalAppointmentDecree");
    let modalAppointmentDecree = new bootstrap.Modal(editModal);
    modalAppointmentDecree.show();

    const resp = await fetch("/appointmentDecree/findById/" + id, {
        method: "GET"
    });

    const data = await resp.json();
    let appointmentDecreeId = document.getElementById("id__edit__appointmentDecree");
    let appointmentDecreePerson = document.getElementById("person__edit__appointmentDecree");
    let appointmentDecreeTypeOfEmployment = document.getElementById("typeOfEmployment__edit__appointmentDecree");
    let appointmentDecreeOrganizationalUnit = document.getElementById("organizationalUnit__edit__appointmentDecree");
    let appointmentDecreeJobType = document.getElementById("jobType__edit__appointmentDecree");
    let appointmentDecreeProfessionalField = document.getElementById("professionalField__edit__appointmentDecree");
    let appointmentDecreeJobCode = document.getElementById("jobCode__edit__appointmentDecree");
    let appointmentDecreeJobTitle = document.getElementById("jobTitle__edit__appointmentDecree");
    let appointmentDecreeStartDate = document.getElementById("startDate__edit__appointmentDecree");
    let appointmentDecreeEndDate = document.getElementById("endDate__edit__appointmentDecree");
    let appointmentDecreePlaceOfEmployment = document.getElementById("placeOfEmployment__edit__appointmentDecree");
    let appointmentDecreeWorkingHours = document.getElementById("workingHours__edit__appointmentDecree");
    let appointmentDecreeWorkingDay = document.getElementById("workingDay__edit__appointmentDecree");
    let appointmentDecreeAppointmentType = document.getElementById("appointmentType__edit__appointmentDecree");
    let appointmentDecreeAppointmentDescription = document.getElementById("appointmentDescription__edit__appointmentDecree");


    appointmentDecreeId.value = data.id;
    appointmentDecreePerson.value = data.person;
    appointmentDecreeTypeOfEmployment.value = data.typeOfEmployment;
    appointmentDecreeOrganizationalUnit.value = data.organizationalUnit;
    appointmentDecreeJobType.value = data.jobType;
    appointmentDecreeProfessionalField.value = data.professionalField;
    appointmentDecreeJobCode.value = data.jobCode;
    appointmentDecreeJobTitle.value = data.jobTitle;
    appointmentDecreeStartDate.value = data.startDate;
    appointmentDecreeEndDate.value = data.endDate;
    appointmentDecreePlaceOfEmployment.value = data.placeOfEmployment;
    appointmentDecreeWorkingHours.value = data.workingHours;
    appointmentDecreeWorkingDay.value = data.workingDay;
    appointmentDecreeAppointmentType.value = data.appointmentType;
    appointmentDecreeAppointmentDescription.value = data.appointmentDescription;

}

// "Edit Functionality"
async function edit() {
    const formData = new FormData(document.getElementById("editFormAppointmentDecree"));

    const resp = await fetch("/appointmentDecree/edit", {
        method: "PUT",
        body: formData
    }).then(() => {
        window.location.replace("/appointmentDecree");

    });
}

async function openDeleteModal(id) {

    let deleteIdInputAppointmentDecree = document.getElementById("id__delete__appointmentDecree");

    deleteIdInputAppointmentDecree.value = id;

    let deleteModal = document.getElementById("deleteModalAppointmentDecree")
    let modalAppointmentDecree = new bootstrap.Modal(deleteModal);
    modalAppointmentDecree.show();
}

async function deleteById(id) {
    const resp = await fetch(`/appointmentDecree/delete/` + id, {
        method: "DELETE"
    });
    console.log(id)
    window.location.replace("/appointmentDecree")
}