
async function findById(id){

    let editModal = document.getElementById("editModalSalary")
    let modalSalary = new bootstrap.Modal(editModal);
    modalSalary.show();

    const resp = await fetch("/salary/findById/" + id ,{
        method :"GET"
    });

    const data = await resp.json();
    let salaryId =document.getElementById("id__edit__salary");
    let salaryBasicHourlyPay = document.getElementById("basicHourlyPay__edit__salary");
    let salaryBasicDailyPay = document.getElementById("basicDailyPay__edit__salary")
    let salaryBasicMonthlyPay = document.getElementById("basicMonthlyPay__edit__salary");
    let salaryWorkingCoupon = document.getElementById("workingCoupon__edit__salary");
    let salaryHousingRight = document.getElementById("housingRight__edit__salary");
    let salaryWorkingYearPay = document.getElementById("workingYearPay__edit__salary");
    let salaryPayForEachChild = document.getElementById("payForEachChild__edit__salary");
    let salaryMarriedPeopleRights = document.getElementById("marriedPeopleRights__edit__salary");
    let salaryInsurance = document.getElementById("insurance__edit__salary");
    let salaryYear = document.getElementById("year__edit__salary");


    salaryId.value = data.id;
    salaryBasicHourlyPay.value = data.basicHourlyPay;
    salaryBasicDailyPay.value = data.basicDailyPay;
    salaryBasicMonthlyPay.value =data.basicMonthlyPay;
    salaryWorkingCoupon.value = data.workingCoupon;
    salaryHousingRight.value = data.housingRight;
    salaryWorkingYearPay.value = data.workingYearPay;
    salaryPayForEachChild.value = data.payForEachChild;
    salaryMarriedPeopleRights.value = data.marriedPeopleRights;
    salaryInsurance.value = data.insurance;
    salaryYear.value = data.year;


}


// "Edit Functionality"
async function edit(){

    const formData = new FormData(document.getElementById("editFormSalary"));

    const resp = await fetch("/salary/edit" ,{
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

    let deleteIdInputSalary =document.getElementById("id__delete__salary");

    deleteIdInputSalary.value = id;

    let deleteModal = document.getElementById("deleteModalSalary")
    let modalSalary = new bootstrap.Modal(deleteModal);
    modalSalary.show();


}

async function deleteById(id){
    const resp= await fetch(`/salary/delete/` + id,{
        method:"DELETE"
    });
    console.log(id)
    window.location.replace("/salary")
}