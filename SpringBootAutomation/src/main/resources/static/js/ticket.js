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
    let ticketGroup = document.getElementById("group__edit__ticket");
    let ticketStatus = document.getElementById("status__edit__ticket");
    let ticketDeleted = document.getElementById("deleted__edit__ticket");


    ticketId.value = data.id;
    ticketDate.value = new Date(data.ticketTimeStamp).toISOString().slice(0, 16);
    ticketTitle.value = data.title;
    // ticketApplicant.value = data.applicant.username;
    ticketRequest.value = data.request;
    ticketGroup.value = data.group.title;
    ticketStatus.value = data.status;
    ticketDeleted.value = data.deleted;

    console.log(data)
}





function handleSelectChange(event , selectTagId) {
    const selectedId = event.target.value;
    getSubGroups(selectedId , selectTagId);
}

async function getSubGroups(id , selectTagId){
    const resp = await fetch("/ticketGroup/parent/" + id ,  {
        method : "get"
    });

    let childId;
    if (selectTagId === 'parent__add__ticket'){
        childId = 'child__add__ticket'
    }else if (selectTagId === 'parent__edit__ticket'){
        childId = 'child__edit__ticket'
    }

    let data;
    let select = document.getElementById(childId);
    const label = document.querySelector(`label[for="${childId}"]`);
    select.innerHTML = '';
    try {
        data = await resp.json();
        select.name = 'group';
        select.style.display='block';
        label.style.display = 'block';
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

        });
    } catch (e) {
        data = null;
        let select2 = document.getElementById(selectTagId)
        select2.name = 'group'
        select.innerHTML = '';
        select.style.display='none';
        label.style.display = 'none';
    }

}