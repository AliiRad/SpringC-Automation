async function findById(id){

    let editModal = document.getElementById("editModalAccount")
    let modalAccount = new bootstrap.Modal(editModal);
    modalAccount.show();

    const resp = await fetch("/account/findById/" + id ,{
        method :"GET"
    });

    const data = await resp.json();
    let accountId =document.getElementById("id__edit__Account");
    let accountAccountNumber = document.getElementById("accountNumber__edit__account");
    let accountCardNumber = document.getElementById("cardNumber__edit__account")
    let accountPerson = document.getElementById("person__edit__account");
    let accountBankAndBranch = document.getElementById("bankAndBranch__edit__account");
    let accountAccountType = document.getElementById("accountType__edit__account");
    let accountAccountStatus = document.getElementById("accountStatus__edit__account");

    accountId.value = data.id;
    accountAccountNumber.value = data.accountNumber;
    accountCardNumber.value = data.cardNumber;
    accountPerson.value =data.person;
    accountBankAndBranch.value = data.bankAndBranch;
    accountAccountType.value = data.accountType;
    accountAccountStatus.value = data.accountStatus;
}

function edit() {
    document.getElementById("editFormAccount").submit();
}

async function openDeleteModal(id){

    let deleteIdInputAccount =document.getElementById("id__delete__account");

    deleteIdInputAccount.value = id;

    let deleteModal = document.getElementById("deleteModalAccount")
    let modalAccount = new bootstrap.Modal(deleteModal);
    modalAccount.show();
}

async function deleteById(id){
    const resp= await fetch(`/account/delete/` + id,{
        method:"DELETE"
    });
    console.log(id)
    window.location.replace("/account")
}