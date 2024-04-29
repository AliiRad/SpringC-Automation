// "Find Functionality"
async function findById(id){


    let editModal = document.getElementById("editModalPerson")
    let modalPerson = new bootstrap.Modal(editModal);
    modalPerson.show();

    const resp = await fetch("/person/findById/" + id ,{
        method :"GET"
    });

    const data = await resp.json();
    let personId =document.getElementById("id__edit__person");
    let personName = document.getElementById("firstName__edit__person");
    let personFamily = document.getElementById("lastName__edit__person")
    let personFather = document.getElementById("fathersName__edit__person");
    let personUsername = document.getElementById("username__edit__person");
    let personPassword = document.getElementById("password__edit__person");
    let personCertificationID = document.getElementById("certificateID__edit__person");
    let personNationalID = document.getElementById("nationalId__edit__person");
    let personBirthdate = document.getElementById("birthdate__edit__person");
    let personGender = document.getElementById("gender__edit__person");
    let personMarriage = document.getElementById("marriageStatus__edit__person");


    personId.value = data.id;
    personName.value = data.name;
    personFamily.value = data.lastname;
    personFather.value =data.fathersName;
    personUsername.value = data.username;
    personPassword.value = data.password;
    personCertificationID.value = data.certificateID;
    personNationalID.value = data.nationalId;
    personBirthdate.value = data.birthdate;
    personGender.value = data.gender;
    personMarriage.value = data.marriageStatus;


}


// "Edit Functionality"
async function edit(){

    const formData = new FormData(document.getElementById("editFormPerson"));

    const resp = await fetch("/person/edit" ,{
        method:"POST",
        body: formData
    });
    // window.location.href = "/person"
    // window.location.replace("/person")
    // window.location.reload();
    //
    // if (resp.ok) {
    //      console.log("ok")
    //
    // } else {
    //     console.error("Failed to save edited person.");
    // }


}


//"Delete Functionality"
async function openDeleteModal(id){

    let deleteIdInputPerson =document.getElementById("id__delete__person");

    deleteIdInputPerson.value = id;

    let deleteModal = document.getElementById("deleteModalPerson")
    let modalPerson = new bootstrap.Modal(deleteModal);
    modalPerson.show();


}

async function deleteById(id){
    const resp= await fetch(`/person/delete/` + id,{
        method:"DELETE"
    });
    console.log(id)
    window.location.replace("/person")
}