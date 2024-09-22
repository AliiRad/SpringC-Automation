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

// "Find By ID Functionality"
async function findById(url, id, formId) {
    console.log("findbyid");

    const response = await fetch(`${url}/${id}`, {
        method: "GET"
    });

    if (!response.ok) {
        showErrorPopup(url, response.status, (await response.text()).toString());
    } else {
        let data = await response.json();
        let editModal = document.getElementById("edit-modal");
        editModal.style.display = "flex";


        let form = document.getElementById(formId);


        for (let key in data) {

            let input = form.querySelector(`[name="${key}"], [id="${key}"]`);

            if (input) {

                if (input.type === 'checkbox' || input.type === 'radio') {
                    input.checked = data[key];
                } else if (input.type === 'select-one') {

                    input.value = data[key] || input.querySelector("option[disabled]").value;
                } else {
                    input.value = data[key];
                }
                console.log(`Populated field: ${key} with value: ${data[key]}`);
            } else {
                console.log(`No matching field found for key: ${key}`);
            }
        }
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