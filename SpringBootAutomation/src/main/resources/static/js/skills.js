// "Find By ID Functionality"
// TODO: Ostad Messbah : Make FindById Automatic
async function findById(url,id){
    console.log("findbyid")

    const response = await fetch(url + "/" + id,
        {
            method: "GET"
        }
    );
    if (! response.ok){
        showErrorPopup(url,response.status , (await response.text()).toString());
    }else{
        let data = JSON.parse(await response.text());
        // const data = await response.json();
        let editModal = document.getElementById("edit-modal");
        editModal.style.display="flex";

        let idEdit= document.querySelector("#edit-modal #id");
        idEdit.value = data.id;
        console.log(idEdit)

        let skillTitleEdit = document.querySelector("#edit-form div :nth-child(2)");
        skillTitleEdit.value =data.skillTitle;
        console.log(skillTitleEdit)

        let rateEdit = document.querySelector("#edit-form div :nth-child(5)");
        rateEdit.value = data.rate;
        console.log(rateEdit)

        let trainingEdit = document.querySelector("#edit-form div :nth-child(8)");
        trainingEdit.value = data.training;
        console.log(trainingEdit)

        let descriptionEdit = document.querySelector("#edit-form div :nth-child(11)");
        descriptionEdit.value = data.description;
        console.log(descriptionEdit)

        let certificationEdit = document.querySelector("#edit-form div :nth-child(14)");
        certificationEdit.value = data.certification;
        console.log(certificationEdit)


    }

}

