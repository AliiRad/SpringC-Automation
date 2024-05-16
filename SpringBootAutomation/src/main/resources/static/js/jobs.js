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
        let job = JSON.parse(await response.text());
        // const data = await response.json();
        let editModal = document.getElementById("edit-modal");
        editModal.style.display="flex";

        let idEdit= document.querySelector("#edit-modal #id");
        idEdit.value = job.id;

        let companyNameEdit = document.querySelector("#edit-form div :nth-child(2)");
        companyNameEdit.value =job.companyName;

        let addressEdit = document.querySelector("#edit-form div :nth-child(5)");
        addressEdit.value = job.address;

        let positionsEdit = document.querySelector("#edit-form div :nth-child(8)");
        positionsEdit.value = job.positions;

        let startDateEdit = document.querySelector("#edit-form div :nth-child(11)");
        startDateEdit.value = job.startDate;

        let endDateEdit = document.querySelector("#edit-form div :nth-child(14)");
        endDateEdit.value = job.endDate;


    }

}

