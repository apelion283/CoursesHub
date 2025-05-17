document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('form');
    const submitBtn = document.querySelector('.submit-button');
    let isUploading = false;

    if (submitBtn && form) {
        submitBtn.addEventListener('click', function () {

            if (!form.checkValidity()) {
                form.reportValidity();
                return;
            }

            Swal.fire({
                title: 'Uploading...',
                text: 'Please wait while the data is uploading. Do not close the browser or refresh the page.',
                allowOutsideClick: false,
                allowEscapeKey: false,
                didOpen: () => {
                    Swal.showLoading();
                }
            });

            isUploading = true;

            window.onbeforeunload = function () {
                if (isUploading) {
                    return 'The data is uploading. Are you sure you want to leave?';
                }
            };
        });

        form.addEventListener('submit', function () {
            isUploading = false;
            window.onbeforeunload = null;
        })
    }
});