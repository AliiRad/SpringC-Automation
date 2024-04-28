async function findId(id) {
    let editForm = document.getElementById("divEdit");
    editForm.style.display = "block";
    const resp = await fetch("/ticket/id/" + id, {
        method: "get"
    });
    const data = await resp.json();
    let ticketId = document.getElementById("ticketId");
    let ticketDate = document.getElementById("ticketDate");
    let ticketTitle = document.getElementById("ticketTitle");
    // let ticketApplicant = document.getElementById("ticketApplicant");
    let ticketRequest = document.getElementById("ticketRequest");
    // let ticketGroup = document.getElementById("ticketGroup");
    let ticketStatus = document.getElementById("ticketStatus");
    let ticketDeleted = document.getElementById("ticketActive");

    ticketId.value = data.id;
    ticketDate.value = data.ticketTimeStamp;
    ticketTitle.value = data.title;
    // ticketApplicant.value = data.applicant.username;
    ticketRequest.value = data.request;
    // ticketGroup.value = data.group.title;
    ticketStatus.value = data.status;
    ticketDeleted.value = data.deleted;
    console.log(data)
}

async function edit(id){

    const formData = new FormData(document.getElementById("ticketEditFrom"));
    // formData.append("id" , id);
    // // formData.append("ticketTimeStamp" , ticketDate.value);
    // formData.append("title" , ticketTitle.value);
    // // formData.append("applicant" , ticketApplicant.value);
    // formData.append("request" , request.value);
    // // formData.append("group" , ticketGroup.value);
    // formData.append("deleted" , deleted.value);
    console.log(formData)
//todo: refresh page after edit- melika
    const response = await fetch("/ticket/edit" , {method : "put" , body : formData});
    window.location.replace("/ticket")

}

async function deleted(id) {
    const resp = await fetch("/ticket/delete/" + id, {
        method: "delete"
    });
    window.location.replace("/ticket")

}