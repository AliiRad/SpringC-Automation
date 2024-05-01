// "Find Functionality"
async function findById(id){

    let editModal = document.getElementById("editModalJobs");
    let modalJobs = new bootstrap.Modal(editModal);
    modalJobs.show();

    const resp = await fetch("/jobs/findById/" + id,{
        method:"GET"
    });


    const data = await resp.json()
    let jobsId = document.getElementById("id__edit__jobs");
    let jobsCompanyName = document.getElementById("companyName__edit__jobs");
    let jobsAddress = document.getElementById("address__edit__jobs");
    let jobsPositions = document.getElementById("positions__edit__jobs");
    let jobsStartDate = document.getElementById("startDate__edit__jobs");
    let jobsEndDate = document.getElementById("endDate__edit__jobs");


    jobsId.value = data.id;
    jobsCompanyName.value = data.companyName;
    jobsAddress.value = data.address;
    jobsPositions.value = data.positions;
    jobsStartDate.value = data.startDate;
    jobsEndDate.value = data.endDate;

}

// "Edit Functionality"
async function edit(){
    const formData = new FormData(document.getElementById("editFormJobs"));

    const resp = await fetch("/jobs/edit",{
        method:"PUT",
        body: formData
    }).then(() => {
        window.location.replace("/jobs");

    });
}

//"Delete Functionality"
async function openDeleteModal(id){
    let deleteIdInputJobs = document.getElementById("id__delete__jobs");

    deleteIdInputJobs.value = id ;

    let deleteModal = document.getElementById("deleteModalJobs");
    let modalJobs =  new bootstrap.Modal(deleteModal);
    modalJobs.show();
}


async function deleteById(id){
    const resp= await fetch(`/jobs/delete/` + id,{
        method:"DELETE"
    });
    console.log(id)
    window.location.replace("/jobs")
}