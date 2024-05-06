async function findId(id) {
    let editModal = document.getElementById("editModalTicketGroup")
    let modalTicketGroup = new bootstrap.Modal(editModal);
    modalTicketGroup.show();
    const resp = await fetch("/ticketGroup/id/" + id, {
        method: "get"
    });
    const data = await resp.json();
    let ticketId = document.getElementById("id__edit__ticketGroup");
    let ticketParent = document.getElementById("parent__edit__ticketGroup");
    let ticketTitle = document.getElementById("title__edit__ticketGroup");


    ticketId.value = data.id;
    ticketTitle.value = data.title;
    if (data.parent) {
        ticketParent.value = data.parent.id;
    } else {
        ticketParent.value = ""; // Reset the dropdown if there's no parent
    }

    console.log(data)
}

async function edit() {
    const formData = new FormData(document.getElementById("editFormTicketGroup"));
    console.log(formData)

    if (confirm("از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticketGroup/edit", {method: "put", body: formData});
    }

    window.location.replace("/ticketGroup")

}

async function save() {
    const saveFormData = new FormData();

    let ticketGroupTitle = document.getElementById("title__add__ticketGroup");
    let ticketGroupparent = document.getElementById("parent__add__ticketGroup");

    saveFormData.append('title' , ticketGroupTitle.value)
    // saveFormData.append('root' , ticketGroupParent.value)
    if(ticketGroupparent != null){saveFormData.append('parent' , ticketGroupparent.value)}


    if (confirm(  "از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticketGroup/save", {method: "post", body: saveFormData});
        console.log(saveFormData)
    }
    //
    window.location.replace("/ticketGroup")

}

async function deleted(id) {
    //todo : cascade all? - melika
    if (confirm("آیا از حذف تیکت " + id + " اطمینان دارید؟")) {
        const resp = await fetch("/ticketGroup/delete/" + id, {
            method: "delete"
        });
    }
    window.location.replace("/ticketGroup")

}