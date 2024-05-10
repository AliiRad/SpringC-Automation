async function findId(id) {
    let editModal = document.getElementById("editModalTicket")
    let modalTicket = new bootstrap.Modal(editModal);
    modalTicket.show();
    const resp = await fetch("/ticket/" + id, {
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
    if (confirm("از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticket", {method: "put", body: formData});
    }

    window.location.replace("/ticket")

}

async function save() {
    const saveFormData = new FormData(saveFormTicket);

    // let ticketTitle = document.getElementById("title__add__ticket");
    // let ticketRequest = document.getElementById("request__add__ticket");
    // // let ticketGroup = document.getElementById("group__add__ticket");
    //
    // saveFormData.append('title' , ticketTitle.value)
    // saveFormData.append('request' , ticketRequest.value)

    if (confirm(  "از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticket", {method: "post", body: saveFormData});
        console.log(saveFormData)
    }
    //
    window.location.replace("/ticket")

}

async function deleted(id) {
    if (confirm("آیا از حذف تیکت " + id + " اطمینان دارید؟")) {
        const resp = await fetch("/ticket/" + id, {
            method: "delete"
        });
    }
    window.location.replace("/ticket")

}
function handleSelectChange(event) {
    const selectedId = event.target.value;  // Get the selected option's value
    getSubGroups(selectedId);  // Call the getSubGroups function with the selected ID
}

async function getSubGroups(id){
    const resp = await fetch("/ticketGroup/parent/" + id ,  {
        method : "get"
    });
    let data;
    let select = document.getElementById('child__add__ticket');
    const label = document.querySelector('label[for="child__add__ticket"]');
    select.innerHTML = '';
    try {
        data = await resp.json();
        select.name = 'group'
        const defaultOption = document.createElement('option');
        defaultOption.value = '-1';
        defaultOption.textContent = 'Select The Relevant Sub Group:';
        defaultOption.disabled = true;
        defaultOption.selected = true;
        select.appendChild(defaultOption);
        data.forEach(function (item){
            let opt = document.createElement('option');
            opt.value = item.id;
            opt.innerHTML = item.title;
            select.appendChild(opt);
            select.style.display='block';
            label.style.display = 'block';
        });
    } catch (e) {
        data = null;
        let select2 = document.getElementById("parent__add__ticket")
        select2.name = 'group'
        select.innerHTML = '';
        select.style.display='none';
        label.style.display = 'none';
    }




}