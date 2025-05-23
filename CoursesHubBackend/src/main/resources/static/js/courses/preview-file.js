function isValidFileType(file, input) {
    const accept = input.getAttribute('accept');
    if (!accept) return true;

    const mimeTypes = accept.split(',').map(type => type.trim().toLowerCase());
    const fileType = file.type.toLowerCase();
    const fileExt = file.name.split('.').pop().toLowerCase();
    return mimeTypes.some(type => {
        if (type.startsWith('.')) {
            return file.name.toLowerCase().endsWith(type);
        } else if (type.endsWith('/*')) {
            const baseType = type.split('/')[0];
            return fileType.startsWith(baseType + '/');
        } else {
            return fileType === type;
        }
    });

}

function showInvalidFileAlert(fileName, input) {
    Swal.fire({
        icon: 'error',
        title: 'The file is invalid!',
        text: `The "${fileName}" is not a valid file type. Please choose a file with the correct extension.!`,
    }).then(() => {
        input.value = '';
    });
}

function previewResourceFile(containerElement) {
    const urlInputs = containerElement.querySelectorAll('input[type="hidden"].hidden-url');
    const fileInputs = containerElement.querySelectorAll('input[type="file"]');

    const oldPreviews = containerElement.querySelectorAll('.file-preview');
    oldPreviews.forEach(preview => preview.remove());

    urlInputs.forEach((urlInput, index) => {
        const fileUrl = urlInput?.value;
        const fileInput = fileInputs[index];
        if (!fileUrl) return;

        const fileName = fileUrl.split('/').pop();
        const fileExt = fileName.split('.').pop().toLowerCase();
        let previewHTML = "";

        const isImage = ['jpg', 'jpeg', 'png', 'gif', 'bmp'].includes(fileExt);
        const isVideo = ['mp4', 'webm', 'ogg'].includes(fileExt);

        if (isVideo) {
            previewHTML = `<video src="${fileUrl}" controls style="max-width: 250px; display:block; height: auto;"></video><p>${fileName}</p>`;
        } else if (isImage) {
            previewHTML = `<img src="${fileUrl}" alt="${fileName}" style="max-width: 200px; border:1px solid black;"><p>${fileName}</p>`;
        } else {
            let iconUrl;
            switch (fileExt) {
                case 'pdf': iconUrl = "https://img.icons8.com/color/96/pdf.png"; break;
                case 'doc': case 'docx': iconUrl = "https://img.icons8.com/color/96/ms-word.png"; break;
                case 'xls': case 'xlsx': iconUrl = "https://img.icons8.com/color/96/ms-excel.png"; break;
                default: iconUrl = "https://img.icons8.com/ios-filled/100/file.png";
            }
            previewHTML = `<a href="${fileUrl}" target="_blank" style="text-decoration: none; color: black;"><img src="${iconUrl}" alt="${fileName}" style="max-width: 150px;"><p>${fileName}</p></a>`;
        }

        const previewDiv = document.createElement('div');
        previewDiv.className = 'file-preview mt-2';
        previewDiv.innerHTML = previewHTML;

        if (fileInput && fileInput.parentNode) {
            fileInput.parentNode.insertBefore(previewDiv, fileInput.nextSibling);
        } else {
            containerElement.appendChild(previewDiv);
        }
    });
}

function handleFileChange(event) {
    const fileInput = event.target;
    const file = fileInput.files[0];
    if (!file) return;

    if (!isValidFileType(file, fileInput)) {
        showInvalidFileAlert(file.name, fileInput);
        return;
    }

    const fileName = file.name;
    const fileExt = fileName.split('.').pop().toLowerCase();
    let previewHTML = "";

    const isImage = ['jpg', 'jpeg', 'png', 'gif', 'bmp'].includes(fileExt);
    const isVideo = ['mp4', 'webm', 'ogg'].includes(fileExt);

    if (isVideo) {
        const videoURL = URL.createObjectURL(file);
        previewHTML = `<video src="${videoURL}" controls style="max-width: 250px; height: auto;"></video><p>${fileName}</p>`;
    } else if (isImage) {
        const imageURL = URL.createObjectURL(file);
        previewHTML = `<img src="${imageURL}" alt="${fileName}" style="max-width: 200px;"><p>${fileName}</p>`;
    } else {
        let iconUrl;
        switch (fileExt) {
            case 'pdf': iconUrl = "https://img.icons8.com/color/96/pdf.png"; break;
            case 'doc':
            case 'docx': iconUrl = "https://img.icons8.com/color/96/ms-word.png"; break;
            case 'xls':
            case 'xlsx': iconUrl = "https://img.icons8.com/color/96/ms-excel.png"; break;
            default: iconUrl = "https://img.icons8.com/ios-filled/100/file.png";
        }
        previewHTML = `<a href="#" style="text-align: center; display: inline-block; text-decoration: none; color: black;">
                            <img src="${iconUrl}" alt="${fileName}" style="max-width: 150px;">
                            <p>${fileName}</p>
                       </a>`;
    }

    const containerElement = fileInput.closest('.resource-block, .lecture-block, .course-image-container');
    if (!containerElement) return;

    const nextSibling = fileInput.nextElementSibling;
    if (nextSibling && nextSibling.classList.contains('file-preview')) {
        nextSibling.remove();
    }

    const previewDiv = document.createElement('div');
    previewDiv.className = 'file-preview mt-2';
    previewDiv.innerHTML = previewHTML;
    fileInput.insertAdjacentElement('afterend', previewDiv);
}

window.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.resource-block').forEach(resourceEl => {
        previewResourceFile(resourceEl);
    });
    document.querySelectorAll('.lecture-block').forEach(lectureEl => {
        previewResourceFile(lectureEl);
    });
    document.querySelectorAll('.course-image-container').forEach(block => {
        previewResourceFile(block);
    });

    document.querySelectorAll('input[type="file"]').forEach(inputFile => {
        inputFile.addEventListener('change', handleFileChange);
    });
});