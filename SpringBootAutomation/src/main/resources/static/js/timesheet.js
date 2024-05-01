
async function findById(id){

    let editModal = document.getElementById("editModalTimesheet")
    let modalTimesheet = new bootstrap.Modal(editModal);
    modalTimesheet.show();

    const resp = await fetch("/timesheet/findById/" + id ,{
        method :"GET"
    });

    const data = await resp.json();
    let timesheetId =document.getElementById("id__edit__timesheet");
    let timesheetEmployee = document.getElementById("employee__edit__timesheet");
    let timesheetDate = document.getElementById("date__edit__timesheet")
    let timesheetManager = document.getElementById("manager__edit__timesheet");
    let timesheetRegularTimeIn = document.getElementById("regularTimeIn__edit__timesheet");
    let timesheetRegularTimeOut = document.getElementById("regularTimeOut__edit__timesheet");
    let timesheetOverTimeIn = document.getElementById("overTimeIn__edit__timesheet");
    let timesheetOverTimeOut = document.getElementById("overTimeOut__edit__timesheet");
    let timesheetEmployeeSignature = document.getElementById("employeeSignature__edit__timesheet");
    let timesheetManagerSignature = document.getElementById("managerSignature__edit__timesheet");



    timesheetId.value = data.id;
    timesheetEmployee.value = data.employee;
    timesheetDate.value = data.date;
    timesheetManager.value =data.manager;
    timesheetRegularTimeIn.value = data.regularTimeIn;
    timesheetRegularTimeOut.value = data.regularTimeOut;
    timesheetOverTimeIn.value = data.overTimeIn;
    timesheetOverTimeOut.value = data.overTimeOut;
    timesheetEmployeeSignature.value = data.employeeSignature;
    timesheetManagerSignature.value = data.managerSignature;


}


// "Edit Functionality"
async function edit(){

    const formData = new FormData(document.getElementById("editFormTimesheet"));

    const resp = await fetch("/timesheet/edit" ,{
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

    let deleteIdInputTimesheet =document.getElementById("id__delete__timesheet");

    deleteIdInputTimesheet.value = id;

    let deleteModal = document.getElementById("deleteModalTimesheet")
    let modalTimesheet = new bootstrap.Modal(deleteModal);
    modalTimesheet.show();


}

async function deleteById(id){
    const resp= await fetch(`/timesheet/delete/` + id,{
        method:"DELETE"
    });
    console.log(id)
    window.location.replace("/timesheet")
}