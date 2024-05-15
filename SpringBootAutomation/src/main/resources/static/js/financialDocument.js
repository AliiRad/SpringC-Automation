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
        let financialDocument = JSON.parse(await response.text());
        let modal = document.getElementById("edit-modal");
        modal.style.display = "block";

        let idEdit = document.querySelector("#edit-form #id");
        idEdit.setAttribute("value", financialDocument.id);

        let documentDateEdit = document.querySelector("#edit-form input[name='documentDate']");
        documentDateEdit.value = financialDocument.documentDate;

        let amountEdit = document.querySelector("#edit-form input[name='amount']");
        amountEdit.value = financialDocument.amount;

        let behalfEdit = document.querySelector("#edit-form select[name='behalf']");
        behalfEdit.value = financialDocument.behalf;

        let accountEdit = document.querySelector("#edit-form select[name='account']");
        accountEdit.value = financialDocument.account;

        let transactionTypeEdit = document.querySelector("#edit-form input[name='transactionType']");
        transactionTypeEdit.value = financialDocument.transactionType;

        let documentTypeEdit = document.querySelector("#edit-form select[name='documentType']");
        documentTypeEdit.value = financialDocument.documentType;

        let personEdit = document.querySelector("#edit-form select[name='person']");
        personEdit.value = financialDocument.person;
    }
}

function closeModal() {
    const saveModal = document.getElementById("save-modal");
    const editModal = document.getElementById("edit-modal");
    saveModal.style.display = "none";
    editModal.style.display = "none";
}