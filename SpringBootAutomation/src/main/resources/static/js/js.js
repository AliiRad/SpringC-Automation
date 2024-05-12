async function save(url, formData) {
    const saveForm = document.getElementById(formData);

    const response = await fetch(url,
        {
            method: "POST",
            body: new FormData(saveForm)
        }
    );
    if(!response.ok){
        showErrorPopup(url,response.status ,(await response.text()).toString());
    }else {
        showInfoPopup(url, response.status , (await response.text()).toString());
    }
}

async function edit(url, formData) {
    const editForm = document.getElementById(formData);

    const response = await fetch(url,
        {
            method: "PUT",
            body: new FormData(editForm)
        }
    );
    if(!response.ok){
        showErrorPopup(url,response.status ,(await response.text()).toString());
    }else {
        showInfoPopup(url, response.status , (await response.text()).toString());
    }
}

async function remove(url, id) {
    const response = await fetch(url + "/" + id,
        {
            method: "DELETE"
        }
    );
    if(!response.ok && confirm("آیا از حذف تیکت " + id + " اطمینان دارید؟")){
        showErrorPopup(url,response.status ,(await response.text()).toString());
    }else {
        showInfoPopup(url, response.status , (await response.text()).toString());
    }
}


