function addMedicalHistory() {
    let saveModal = document.getElementById("saveModalMedicalHistory");
    saveModal.style.display = "block"
}

async function findId(id) {
    let editModal = document.getElementById("editModalMedicalHistory")
    editModal.style.display = 'block';
    const resp = await fetch("/medicalHistory/" + id, {
        method: "get"
    });
    const data = await resp.json();
    let medicalHistoryId = document.getElementById("id__edit__medicalHistory");
    let medicalHistoryWeight = document.getElementById("weight__edit__medicalHistory");
    let medicalHistoryHeight = document.getElementById("height__edit__medicalHistory");
    let medicalHistoryBloodPressure = document.getElementById("bloodPressure__edit__medicalHistory");
    let medicalHistoryHeartRate = document.getElementById("heartRate__edit__medicalHistory");
    let medicalHistoryAllergy = document.getElementById("allergy__edit__medicalHistory");
    let medicalHistorySurgery = document.getElementById("surgery__edit__medicalHistory");
    let medicalHistoryEmergencyDrug = document.getElementById("emergencyDrug__edit__medicalHistory");
    let medicalHistoryDisease = document.getElementById("disease__edit__medicalHistory");
    let medicalHistoryEmergencyPhoneNumber = document.getElementById("emergencyPhoneNumber__edit__medicalHistory");

    medicalHistoryId.value = data.id;
    medicalHistoryWeight.value = data.weight;
    medicalHistoryHeight.value = data.height;
    medicalHistoryBloodPressure.value = data.bloodPressure;
    medicalHistoryHeartRate.value = data.heartRate;
    medicalHistoryAllergy.value = data.allergy;
    medicalHistorySurgery.value = data.surgery;
    medicalHistoryEmergencyDrug.value = data.emergencyDrug;
    medicalHistoryDisease.value = data.disease;
    medicalHistoryEmergencyPhoneNumber.value = data.emergencyPhoneNumber;
}

async function edit() {
    let editModal = document.getElementById("editModalMedicalHistory")
    editModal.style.display = 'none';
    const formData = new FormData(document.getElementById("editFormMedicalHistory"));

    if (confirm("Are you sure?")) {
        const response = await fetch("/medicalHistory", {method: "put", body: formData});
        if (!response.ok) {
            showErrorPopup('/medicalHistory', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/medicalHistory', response.status, (await response.text()).toString());
        }
    }
}

async function save() {
    let saveModal = document.getElementById("saveModalMedicalHistory")
    saveModal.style.display = 'none'
    const saveFormData = new FormData(saveFormMedicalHistory);

    if (confirm("Are you sure?")) {
        const response = await fetch("/medicalHistory", {method: "post", body: saveFormData});
        if (!response.ok) {
            showErrorPopup('/medicalHistory', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/medicalHistory', response.status, (await response.text()).toString());
        }
    }

}

async function deleted(id) {
    if (confirm(" Confirm delete " + id)) {
        const response = await fetch("/medicalHistory/" + id, {
            method: "delete"
        });
        if (!response.ok) {
            showErrorPopup('/medicalHistory', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/medicalHistory', response.status, (await response.text()).toString());
        }
    }

}

