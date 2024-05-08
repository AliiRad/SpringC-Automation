async function findById(id){

    let editModal = document.getElementById("editModalFinancialDocument");
    let modalFinancialDocument = new bootstrap.Modal(editModal);
    modalFinancialDocument.show();

    const resp = await fetch("/financialDocument/findById/" + id ,{
        method :"GET"
    });

    const data = await resp.json();

    console.log(data);

    let financialDocumentId = document.getElementById("id__edit__financialDocument");
    let financialDocumentDocumentDate = document.getElementById("documentDate__edit__financialDocument");
    let financialDocumentAmount = document.getElementById("amount__edit__financialDocument");
    let financialDocumentBehalf = document.getElementById("behalf__edit__financialDocument");
    let financialDocumentAccount = document.getElementById("account__edit__financialDocument");
    let financialDocumentTransactionType = document.getElementById("transactionType__edit__financialDocument");
    let financialDocumentDocumentType = document.getElementById("documentType__edit__financialDocument");
    let financialDocumentPerson = document.getElementById("person__edit__financialDocument");

    financialDocumentId.value = data.id;
    financialDocumentDocumentDate.value = data.documentDate;
    financialDocumentAmount.value = data.amount;
    financialDocumentBehalf.value = data.behalf;
    financialDocumentAccount.value = data.account;
    financialDocumentTransactionType.value = data.transactionType;
    financialDocumentDocumentType.value = data.documentType;
    financialDocumentPerson.value = data.person;
}

function edit() {
    document.getElementById("editFormFinancialDocument").submit();
}

async function openDeleteModal(id){

    let deleteIdInputFinancialDocument = document.getElementById("id__delete__financialDocument");

    deleteIdInputFinancialDocument.value = id;

    let deleteModal = document.getElementById("deleteModalFinancialDocument");
    let modalFinancialDocument = new bootstrap.Modal(deleteModal);
    modalFinancialDocument.show();
}

async function deleteById(id){
    const resp = await fetch(`/financialDocument/delete/` + id,{
        method:"DELETE"
    });
    console.log(id);
    window.location.replace("/financialDocument");
}