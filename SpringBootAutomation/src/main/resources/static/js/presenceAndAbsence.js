
async function findById(id){

    let editModal = document.getElementById("editModalPresence")
    let modalPresence = new bootstrap.Modal(editModal);
    modalPresence.show();

    const resp = await fetch("/presenceAndAbsence/findById/" + id ,{
        method :"GET"
    });

    const data = await resp.json();
    let presenceId =document.getElementById("id__edit__presence");
    let presenceEmployee = document.getElementById("employee__edit__presence");
    let presenceDate = document.getElementById("date__edit__presence")
    let presenceEnterTime = document.getElementById("enterTime__edit__presence");
    let presenceExitTime = document.getElementById("exitTime__edit__presence");


    presenceId.value = data.id;
    presenceEmployee.value = data.employee;
    presenceDate.value = data.date;
    presenceEnterTime.value =data.enterTime;
    presenceExitTime.value = data.exitTime;


}


// "Edit Functionality"
async function edit(){

    const formData = new FormData(document.getElementById("editFormPresence"));

    const resp = await fetch("/presenceAndAbsence/edit" ,{
        method:"POST",
        body: formData
    });
    // window.location.href = "/person"
    // window.location.replace("/person")
    // window.location.reload();
    //
    // if (resp.ok) {
    //      console.log("ok")
    //
    // } else {
    //     console.error("Failed to save edited person.");
    // }


}


//"Delete Functionality"
async function openDeleteModal(id){

    let deleteIdInputPresence =document.getElementById("id__delete__presence");

    deleteIdInputPresence.value = id;

    let deleteModal = document.getElementById("deleteModalPresence")
    let modalPresence = new bootstrap.Modal(deleteModal);
    modalPresence.show();


}

async function deleteById(id){
    const resp= await fetch(`/presenceAndAbsence/delete/` + id,{
        method:"DELETE"
    });
    console.log(id)
    window.location.replace("/presenceAndAbsence")
}