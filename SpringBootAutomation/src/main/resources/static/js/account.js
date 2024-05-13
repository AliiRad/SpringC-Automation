function addNew(){
    let modal = document.getElementById("save-modal");
    modal.style.display = "block";
}

async function getDataForEdit(url, id) {
    const response = await fetch(url + "/" + id,
        {
            method: "GET"
        }
    );
    if (!response.ok) {
        alert("Error : " + response.status + "\n" + (await response.text()).toString());
    } else {
        let account = JSON.parse(await response.text());
        let modal = document.getElementById("edit-modal");
        modal.style.display = "block";

        let idEdit = document.querySelector("#edit-form #id");
        idEdit.value = account.id;

        let accountNumberEdit = document.querySelector("#edit-form div :nth-child(1)");
        accountNumberEdit.id = "edit-accountNumber";
        accountNumberEdit.value = account.accountNumber;

        let cardNumberEdit = document.querySelector("#edit-form div :nth-child(2)");
        cardNumberEdit.id = "edit-cardNumber";
        cardNumberEdit.value = account.cardNumber;

        let personEdit = document.querySelector("#edit-form div :nth-child(3)");
        personEdit.id = "edit-person";
        personEdit.value = account.person;

        let bankAndBranchEdit = document.querySelector("#edit-form div :nth-child(4)");
        bankAndBranchEdit.id = "edit-bankAndBranch";
        bankAndBranchEdit.value = account.bankAndBranch;

        let accountTypeEdit = document.querySelector("#edit-form div :nth-child(5)");
        accountTypeEdit.id = "edit-accountType";
        accountTypeEdit.value = account.accountType;

        let accountStatusEdit = document.querySelector("#edit-form div :nth-child(6)");
        accountStatusEdit.id = "edit-accountStatus";
        accountStatusEdit.value = account.accountStatus;
    }
}

function closeModal(){
    const saveModal = document.getElementById("save-modal");
    const editModal = document.getElementById("edit-modal");
    saveModal.style.display = "none";
    editModal.style.display = "none";
}