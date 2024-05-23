// "Find By ID Functionality"
async function findById(url,id){
    console.log("find by id")

    const response = await fetch(url + "/" + id,
        {
            method: "GET"
        }
    );
    if (! response.ok){
        showErrorPopup(url,response.status , (await response.text()).toString());
    }else{
        let person = JSON.parse(await response.text());

        let editModal = document.getElementById("edit-modal");
        editModal.style.display="flex";

        let idEdit= document.querySelector("#edit-modal #id");
        idEdit.value = person.id;

        let nameEdit = document.querySelector("#edit-form div :nth-child(2)");
        nameEdit.value =person.name;

        let lastNameEdit = document.querySelector("#edit-form div :nth-child(5)");
        lastNameEdit.value =person.lastname;

        let fathersNameEdit = document.querySelector("#edit-form div :nth-child(8)");
        fathersNameEdit.value =person.fathersName;

        let certificateIDEdit = document.querySelector("#edit-form div :nth-child(11)");
        certificateIDEdit.value =person.certificateID;

        let nationalIDEdit = document.querySelector("#edit-form div :nth-child(14)");
        nationalIDEdit.value =person.nationalID;

        let birthdateEdit = document.querySelector("#edit-form div :nth-child(17)");
        birthdateEdit.value =person.birthdate;

        let genderEdit = document.querySelector("#edit-form div :nth-child(20)");
        genderEdit.value =person.gender;

        let marriageStatusEdit = document.querySelector("#edit-form div :nth-child(23)");
        marriageStatusEdit.value =person.marriageStatus;



    }

}


