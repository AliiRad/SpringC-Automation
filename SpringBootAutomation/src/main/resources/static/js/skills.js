// "Find Functionality"
async function findById(id){
    let editModal =document.getElementById("editModalSkills");
    let modalSkills = new bootstrap.Modal(editModal);
    modalSkills.show();

    const resp = await fetch("/skills/findById/" + id,{
        method:"GET"
    });

    const data = await resp.json();
    let skillId = document.getElementById("id__edit__skills");
    let skillTitle = document.getElementById("skillTitle__edit__skills");
    let skillRate = document.getElementById("rate__edit__skills");
    let skillTraining = document.getElementById("training__edit__skills");
    let skillDescription = document.getElementById("description__edit__skills");
    let skillCertification = document.getElementById("Certification__edit__skills");


    skillId.value = data.id;
    skillTitle.value = data.skillTitle;
    skillRate.value = data.rate;
    skillTraining.value = data.training ;
    skillDescription.value = data.description;
    skillCertification.value = data.certification;
}

// "Edit Functionality"
async function edit(){
    const formData = new FormData(document.getElementById("editFormSkills"));

    const resp = await fetch("/skills/edit",{
        method:"PUT",
        body: formData
    }).then(() => {
        window.location.replace("/skills");

    });
}





//"Delete Functionality"
async function openDeleteModal(id){
    let deleteIdInputSkills = document.getElementById("id__delete__skills");

    deleteIdInputSkills.value = id ;

    let deleteModal = document.getElementById("deleteModalSkills");
    let modalSkills =  new bootstrap.Modal(deleteModal);
    modalSkills.show();
}


async function deleteById(id){
    const resp= await fetch(`/skills/delete/` + id,{
        method:"DELETE"
    });
    console.log(id)
    window.location.replace("/skills")
}