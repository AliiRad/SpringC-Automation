// Edit Modal:

document.addEventListener("DOMContentLoaded", function () {
    let editButtonsSkills = document.querySelectorAll('.editButtonSkills');
    let editModalSkills = document.getElementById('editModalSkills');
    let submitButtonSkills = document.getElementById('submitEditModal__skills');


    editButtonsSkills.forEach(function (editButtonSkills) {
        editButtonSkills.addEventListener('click', function (event) {
            event.preventDefault();

            let row = this.closest('tr');
            let id = row.querySelector('td:nth-child(1)').innerText;
            let skillTitle = row.querySelector('td:nth-child(2)').innerText;
            let rate = row.querySelector('td:nth-child(3)').innerText;
            let training = row.querySelector('td:nth-child(4)').innerText;
            let description = row.querySelector('td:nth-child(5)').innerText;
            let certification = row.querySelector('td:nth-child(6)').innerText;


            document.getElementById("id__edit__skills").value = id;
            document.getElementById("skillTitle__edit__skills").value = skillTitle;
            document.getElementById("rate__edit__skills").value = rate;
            document.getElementById("training__edit__skills").value = training;
            document.getElementById("description__edit__skills").value = description;
            document.getElementById("Certification__edit__skills").value = certification;

            let modalSkill = new bootstrap.Modal(editModalSkills);
            modalSkill.show();
        });
    });

    submitButtonSkills.addEventListener('click', function (event) {
        let formData = new FormData(document.getElementById('editForm'));
        fetch('/skills/edit', {
            method: 'POST',
            body: formData
        })

    });
});

// Delete Modal:

document.addEventListener("DOMContentLoaded", function () {
    let deleteButtonsSkills = document.querySelectorAll('.deleteButtonSkills');
    let deleteModalSkills = document.getElementById('deleteModalSkills');
    let deleteConfirmButtonSkills = document.getElementById('deleteConfirmButtonSkills');
    let deleteIdInputSkills = document.getElementById('id__delete__skills');

    deleteButtonsSkills.forEach(function (deleteButtonSkills) {
        deleteButtonSkills.addEventListener('click', function (event) {
            event.preventDefault();


            let row = this.closest('tr');
            let id = row.querySelector('td:nth-child(1)').innerText;


            deleteIdInputSkills.value = id;


            let modalSkills = new bootstrap.Modal(deleteModalSkills);
            modalSkills.show();
        });
    });


    deleteConfirmButtonSkills.addEventListener('click', function (event) {

        let id = deleteIdInputSkills.value;

        fetch('/skills/delete/' + id, {
            method: 'POST'
            // body: id
        }).then(response => {

            console.log('skill deleted successfully !');

        }).catch(error => {

            console.error('Error deleting skill:', error);

        });


        // deleteModalSkills.hide();
    });
});

// Search Box

document.addEventListener("DOMContentLoaded", function () {

    document.getElementById('findByIdForm__skill').addEventListener('submit', function (event) {

        event.preventDefault();

        let findByIdInputSkill = document.getElementById('findByIdInput__skill').value;
        let xhr = new XMLHttpRequest();
        xhr.open('GET', '/skills/findById/' + findByIdInputSkill)
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log(xhr.responseText)


            } else {
                console.error('Request failed. Status: ' + xhr.status);
            }
        };
        xhr.send();


    });

});




//TODO : Putting All Methods in a Single Event Listener Block And Delete The forEach()
