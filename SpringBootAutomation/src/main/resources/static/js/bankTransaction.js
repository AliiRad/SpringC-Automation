async function findById(id){

    let editModal = document.getElementById("editModalBankTransaction")
    let modalBankTransaction = new bootstrap.Modal(editModal);
    modalBankTransaction.show();

    const resp = await fetch("/bankTransaction/findById/" + id ,{
        method :"GET"
    });

    const data = await resp.json();
    let bankTransactionId =document.getElementById("id__edit__bankTransaction");
    let bankTransactionBalance = document.getElementById("balance__edit__bankTransaction");
    let bankTransactionFinancialDocument = document.getElementById("financialDocument__edit__bankTransaction")
    let bankTransactionPerson = document.getElementById("person__edit__bankTransaction");

    bankTransactionId.value = data.id;
    bankTransactionBalance.value = data.balance;
    bankTransactionFinancialDocument.value = data.financialDocument;
    bankTransactionPerson.value =data.person;
}

function edit() {
    document.getElementById("editFormBankTransaction").submit();
}

async function openDeleteModal(id){

    let deleteIdInputBankTransaction =document.getElementById("id__delete__bankTransaction");

    deleteIdInputBankTransaction.value = id;

    let deleteModal = document.getElementById("deleteModalBankTransaction")
    let modalBankTransaction = new bootstrap.Modal(deleteModal);
    modalBankTransaction.show();
}

async function deleteById(id){
    const resp= await fetch(`/bankTransaction/delete/` + id,{
        method:"DELETE"
    });
    console.log(id)
    window.location.replace("/bankTransaction")
}