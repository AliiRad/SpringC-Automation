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
        let account = JSON.parse(await response.text());
        let modal = document.getElementById("edit-modal");
        modal.style.display = "block";

        let idEdit = document.querySelector("#edit-form #id");
        idEdit.setAttribute("value", account.id);

        let accountNumberEdit = document.querySelector("#edit-form input[name='accountNumber']");
        accountNumberEdit.value = account.accountNumber;

        let cardNumberEdit = document.querySelector("#edit-form input[name='cardNumber']");
        cardNumberEdit.value = account.cardNumber;

        let personEdit = document.querySelector("#edit-form select[name='person']");
        personEdit.value = account.person;

        let bankAndBranchEdit = document.querySelector("#edit-form input[name='bankAndBranch']");
        bankAndBranchEdit.value = account.bankAndBranch;

        let accountTypeEdit = document.querySelector("#edit-form select[name='accountType']");
        accountTypeEdit.value = account.accountType;

        let accountStatusEdit = document.querySelector("#edit-form select[name='accountStatus']");
        accountStatusEdit.value = account.accountStatus;
    }
}

function closeModal() {
    const saveModal = document.getElementById("save-modal");
    const editModal = document.getElementById("edit-modal");
    saveModal.style.display = "none";
    editModal.style.display = "none";
}
