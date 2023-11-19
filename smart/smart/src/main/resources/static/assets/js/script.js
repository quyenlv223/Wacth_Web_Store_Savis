// Starting page
$(document).ready(function () {
    // Submit change password by key press Enter
    $('#formPassword').keypress((e) => {
        if (e.which === 13) {
            $('#btnChangePassword').click();
        }
    });
});

// Button change password
$('#btnChangePassword').click(function () {
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    });

    // Collect form data
    let data = {};
    let formData = $('#formPassword').serializeArray();
    $.each(formData, function (i, v) {
        data["" + v.name + ""] = v.value;
    });

    $.ajax({
        url: `/api/change-password`,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (data) {
            $('.modal-password').modal('hide');
            Toast.fire({
                icon: 'success',
                title: `${data}`
            });
        },
        error: function (data) {
            Toast.fire({
                icon: 'error',
                title: `${data.responseText}`
            });
        }
    });
});