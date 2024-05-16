// "Save Functionality"
async function save(url ,formID) {
    const saveForm = document.getElementById(formID);
    let formData = new FormData(saveForm)


    console.log("before fetch")
    const response = await fetch(url,
        {
            method: "POST",
            body: formData
        }
    );
    if(!response.ok){
        showErrorPopup(url,response.status ,  (await response.text()).toString());

    }else{
        showInfoPopup(url,response.status , (await response.text()).toString())
    }
}

// "Edit Functionality"
async function edit(url , formId){
    const editForm = document.getElementById(formId);
    let formData = new FormData(editForm)

    const response = await fetch(url,
        {
            method:"PUT",
            body:formData

        }
    );
    if(!response.ok){
        showErrorPopup(url,response.status , (await response.text()).toString());

    }else{
        showInfoPopup(url,response.status , (await response.text()).toString())
    }
}

// "Remove Functionality"
async function remove(url , id){
    const response = await fetch(url +"/"+ id ,
        {
            method:"DELETE"
        }
    );
    if(!response.ok){
        console.log(id)
        showErrorPopup(url,response.status ,  (await response.text()).toString());

    }else{
        showInfoPopup(url,response.status , (await response.text()).toString())
    }
}

// "Opening Save Modal"
function addNew() {
    let modal = document.getElementById('save-modal');
    modal.style.display = "flex";
}

// "Closing Save/Edit Modal"
function closeModal(){
    const saveModal =document.getElementById("save-modal");
    const editModal=document.getElementById("edit-modal");
    saveModal.style.display="none";
    editModal.style.display="none";
}