//Edit Modal

document.addEventListener("DOMContentLoaded", function() {
    let editButtonsJobs = document.querySelectorAll('.editButtonJobs');
    let editModalJobs = document.getElementById('editModalJobs');
    let submitButtonJobs = document.getElementById('submitEditModal__jobs');



    editButtonsJobs.forEach(function(editButton) {
        editButton.addEventListener('click', function(event) {
            event.preventDefault();

            let row = this.closest('tr');
            let id = row.querySelector('td:nth-child(1)').innerText;
            let companyName = row.querySelector('td:nth-child(2)').innerText;
            let address = row.querySelector('td:nth-child(3)').innerText;
            let positions = row.querySelector('td:nth-child(4)').innerText;
            let startDate =new Date( row.querySelector('td:nth-child(5)').innerText).toISOString().slice(0,10);
            let endDate = new Date( row.querySelector('td:nth-child(6)').innerText).toISOString().slice(0,10);






            document.getElementById("id__edit__jobs").value = id;
            document.getElementById("companyName__edit__jobs").value = companyName;
            document.getElementById("address__edit__jobs").value = address;
            document.getElementById("positions__edit__jobs").value = positions;
            document.getElementById("startDate__edit__jobs").value = startDate;
            document.getElementById("endDate__edit__jobs").value = endDate;

            let modalJobs = new bootstrap.Modal(editModalJobs);
            modalJobs.show();
        });
    });

    submitButtonJobs.addEventListener('click', function(event) {
        let formData = new FormData(document.getElementById('editForm'));
        fetch('/jobs/edit', {
            method: 'POST',
            body: formData
        })

    });
});

//Delete Modal

document.addEventListener("DOMContentLoaded", function() {
    let deleteButtonsJobs = document.querySelectorAll('.deleteButtonJobs');
    let deleteModalJobs = document.getElementById('deleteModalJobs');
    let deleteConfirmButtonJobs = document.getElementById('deleteConfirmButtonJobs');
    let deleteIdInputJobs = document.getElementById('id__delete__jobs');

    deleteButtonsJobs.forEach(function(deleteButtonJobs) {
        deleteButtonJobs.addEventListener('click', function(event) {
            event.preventDefault();


            let row = this.closest('tr');
            let id = row.querySelector('td:nth-child(1)').innerText;


            deleteIdInputJobs.value = id;


            let modalJobs = new bootstrap.Modal(deleteModalJobs);
            modalJobs.show();
        });
    });


    deleteConfirmButtonJobs.addEventListener('click', function(event) {

        let id = deleteIdInputJobs.value;


        fetch('/jobs/delete/' + id, {
            method: 'POST'
        }).then(response => {

            // window.location.reload();

            console.log('Item deleted successfully');
        }).catch(error => {

            console.error('Error deleting item:', error);
        });


        deleteModalJobs.hide();
    });
});
