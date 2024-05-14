function addNew(){
    let modal = document.getElementById("save-modal");
    modal.style.display = "block";
}

// todo : is just for salary - its not generic
async function getDataForEdit(url, id) {
    const response = await fetch(url + "/" + id,
        {
            method: "GET"
        }
    );
    if (!response.ok) {
        alert("Error : " + response.status + "\n" + (await response.text()).toString());
    } else {
        // todo : make it automate
        let salary = JSON.parse(await response.text());
        let modal = document.getElementById("edit-modal");
        modal.style.display = "block";

        let idEdit = document.querySelector("#edit-form #id");
        idEdit.value = salary.id;

        let basicHourlyPayEdit = document.querySelector("#edit-form div :nth-child(1)");
        basicHourlyPayEdit.id = "edit-basicHourlyPay";
        basicHourlyPayEdit.value = salary.basicHourlyPay;

        let basicDailyPayEdit = document.querySelector("#edit-form div :nth-child(2)");
        basicDailyPayEdit.value = salary.basicDailyPay;

        let basicMonthlyPayEdit = document.querySelector("#edit-form div :nth-child(3)");
        basicMonthlyPayEdit.value = salary.basicMonthlyPay;

        let workingCouponEdit = document.querySelector("#edit-form div :nth-child(4)");
        workingCouponEdit.value = salary.workingCoupon;

        let housingRightEdit = document.querySelector("#edit-form div :nth-child(5)");
        housingRightEdit.value = salary.housingRight;

        let workingYearPayEdit = document.querySelector("#edit-form div :nth-child(6)");
        workingYearPayEdit.value = salary.workingYearPay;

        let payForEachChildEdit = document.querySelector("#edit-form div :nth-child(7)");
        payForEachChildEdit.value = salary.payForEachChild;

        let marriedPeopleRightsEdit = document.querySelector("#edit-form div :nth-child(8)");
        marriedPeopleRightsEdit.value = salary.marriedPeopleRights;

        let insuranceEdit = document.querySelector("#edit-form div :nth-child(9)");
        insuranceEdit.value = salary.insurance;

        let yearEdit = document.querySelector("#edit-form div :nth-child(10)");
        yearEdit.value = salary.year;
    }
}

function closeModal(){
    const saveModal = document.getElementById("save-modal");
    const editModal = document.getElementById("edit-modal");
    saveModal.style.display = "none";
    editModal.style.display = "none";
}
