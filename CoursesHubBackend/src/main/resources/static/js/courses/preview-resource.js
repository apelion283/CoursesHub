function previewResource(event, chapterIndex, resourceIndex) {
    const file = event.target.files[0];
    const previewContainer = document.getElementById(`chapter-${chapterIndex}-resource-${resourceIndex}-preview`);
    if (!file || !previewContainer) return;

    const fileName = file.name;
    const fileExt = fileName.split('.').pop().toLowerCase();

    const reader = new FileReader();

    reader.onload = function (e) {
        const fileUrl = e.target.result;
        let previewHTML = "";

        if (['mp4', 'webm', 'ogg'].includes(fileExt)) {
            previewHTML = `
        <video src="${fileUrl}" controls></video>
        <p style="text-align: center;">${fileName}</p>
      `;
        } else if (['jpg', 'jpeg', 'png', 'gif', 'bmp'].includes(fileExt)) {
            previewHTML = `
        <img src="${fileUrl}" alt="Image preview" />
        <p style="text-align: center;">${fileName}</p>
      `;
        } else {
            let iconUrl = "https://img.icons8.com/ios-filled/100/file.png";

            if (fileExt === 'pdf') {
                iconUrl = "https://img.icons8.com/color/96/pdf.png";
            } else if (['doc', 'docx'].includes(fileExt)) {
                iconUrl = "https://img.icons8.com/color/96/ms-word.png";
            }

            previewHTML = `
        <a href="${fileUrl}" target="_blank" style="text-align: center; display: inline-block; text-decoration: none; color: black;">
          <img src="${iconUrl}" alt="${fileName}" style="max-width: 150px;">
          <p>${fileName}</p>
        </a>
      `;
        }

        previewContainer.innerHTML = previewHTML;
    };

    reader.readAsDataURL(file);
}