function addNew() {
    let modal = document.getElementById("save-modal");
    modal.style.display = "block";
}

async function getDataForEdit(url, id) {
    const response = await fetch(url + "/" + id, {
        method: "GET"
    });
    if (!response.ok) {
        alert("Error : " + response.status + "\n" + (await response.text()).toString());
    } else {
        let bankTransaction = JSON.parse(await response.text());
        let modal = document.getElementById("edit-modal");
        modal.style.display = "block";

        let idEdit = document.querySelector("#edit-form #id");
        idEdit.setAttribute("value", bankTransaction.id);

        let balanceEdit = document.querySelector("#edit-form input[name='balance']");
        balanceEdit.value = bankTransaction.balance;

        let financialDocumentEdit = document.querySelector("#edit-form select[name='financialDocument']");
        financialDocumentEdit.value = bankTransaction.financialDocument;

        let personEdit = document.querySelector("#edit-form select[name='person']");
        personEdit.value = bankTransaction.person;
    }
}

function closeModal() {
    const saveModal = document.getElementById("save-modal");
    const editModal = document.getElementById("edit-modal");
    saveModal.style.display = "none";
    editModal.style.display = "none";
}