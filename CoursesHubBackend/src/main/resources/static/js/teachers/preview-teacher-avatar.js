document.addEventListener("DOMContentLoaded", function () {
    const avatarUrlInput = document.querySelector('input[name="avatarUrl"].hidden-url');
    const fileInput = document.getElementById("file");
    const previewContainer = document.getElementById("avatar-preview");

    let previousPreviewHTML = '';

    const currentUrl = avatarUrlInput?.value;
    if (currentUrl) {
        const img = document.createElement("img");
        img.classList.add("avatar");
        img.src = currentUrl;
        previewContainer.appendChild(img);
        previousPreviewHTML = previewContainer.innerHTML;
    }

    fileInput.addEventListener("change", function (event) {
        const file = event.target.files[0];

        previewContainer.innerHTML = "";

        if (file && file.type.startsWith("image/")) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const img = document.createElement("img");
                img.classList.add("avatar");
                img.src = e.target.result;
                previewContainer.appendChild(img);
            };
            reader.readAsDataURL(file);
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Wrong file type!',
                text: 'Please choose an image file.',
            }).then(() => {
                previewContainer.innerHTML = previousPreviewHTML;
                fileInput.value = "";
            });
        }
    });
});