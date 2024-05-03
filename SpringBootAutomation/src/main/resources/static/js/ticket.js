async function findId(id) {
    let editModal = document.getElementById("editModalTicket")
    let modalTicket = new bootstrap.Modal(editModal);
    modalTicket.show();
    const resp = await fetch("/ticket/id/" + id, {
        method: "get"
    });
    const data = await resp.json();
    let ticketId = document.getElementById("id__edit__ticket");
    let ticketDate = document.getElementById("ticketTimeStamp__edit__ticket");
    let ticketTitle = document.getElementById("title__edit__ticket");
    // let ticketApplicant = document.getElementById("applicant__edit__ticket");
    let ticketRequest = document.getElementById("request__edit__ticket");
    // let ticketGroup = document.getElementById("group__edit__ticket");
    let ticketStatus = document.getElementById("status__edit__ticket");
    // let ticketDeleted = document.getElementById("deleted__edit__ticket");


    ticketId.value = data.id;
    ticketDate.value = new Date(data.ticketTimeStamp).toISOString().slice(0, 16);
    ticketTitle.value = data.title;
    // ticketApplicant.value = data.applicant.username;
    ticketRequest.value = data.request;
    // ticketGroup.value = data.group.title;
    ticketStatus.value = data.status;
    ticketDeleted.value = data.deleted;
    console.log(data)
}

async function edit() {
    const formData = new FormData(document.getElementById("editFormTicket"));
    console.log(formData)
//todo: refresh page after edit- melika
    if (confirm("از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticket/edit", {method: "put", body: formData});
    }

    window.location.replace("/ticket")

}

async function save() {
    const saveFormData = new FormData();

    let ticketTitle = document.getElementById("title__add__ticket");
    let ticketRequest = document.getElementById("request__add__ticket");
    // let ticketGroup = document.getElementById("group__add__ticket");

    saveFormData.append('title' , ticketTitle.value)
    saveFormData.append('request' , ticketRequest.value)

    if (confirm(  "از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticket/save", {method: "post", body: saveFormData});
        console.log(saveFormData)
    }
    //
    window.location.replace("/ticket")

}

async function deleted(id) {
    if (confirm("آیا از حذف تیکت " + id + " اطمینان دارید؟")) {
        const resp = await fetch("/ticket/delete/" + id, {
            method: "delete"
        });
    }
    window.location.replace("/ticket")

}