function addDisease() {
    let saveModal = document.getElementById("saveModalDisease");
    saveModal.style.display = "block"
}

async function findId(id) {
    let editModal = document.getElementById("editModalDisease")
    editModal.style.display = 'block';
    const resp = await fetch("/disease/" + id, {
        method: "get"
    });
    const data = await resp.json();
    let diseaseId = document.getElementById("id__edit__disease");
    let diseaseName = document.getElementById("name__edit__disease");
    let diseaseType = document.getElementById("type__edit__disease");
    let diseaseGrade = document.getElementById("grade__edit__disease");
    let diseaseDeleted = document.getElementById("deleted__edit__disease");


    diseaseId.value = data.id;
    diseaseName.value= data.name;
    diseaseType.value= data.type;
    diseaseGrade.value=data.grade;
    diseaseDeleted.value=data.deleted;
}

async function edit() {
    let editModal = document.getElementById("editModalDisease")
    editModal.style.display = 'none';
    const formData = new FormData(document.getElementById("editFormDisease"));

    if (confirm("Are you sure?")) {
        const response = await fetch("/disease", {method: "put", body: formData});
        if (!response.ok) {
            showErrorPopup('/disease', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/disease', response.status, (await response.text()).toString());
        }
    }
}

async function save() {
    let saveModal = document.getElementById("saveModalDisease")
    saveModal.style.display = 'none'
    const saveFormData = new FormData(saveFormDisease);

    if (confirm("Are you sure?")) {
        const response = await fetch("/disease", {method: "post", body: saveFormData});
        if (!response.ok) {
            showErrorPopup('/disease', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/disease', response.status, (await response.text()).toString());
        }
    }

}

async function deleted(id) {
    if (confirm(" Confirm delete " + id)) {
        const response = await fetch("/disease/" + id, {
            method: "delete"
        });
        if (!response.ok) {
            showErrorPopup('/disease', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/disease', response.status, (await response.text()).toString());
        }
    }

}

