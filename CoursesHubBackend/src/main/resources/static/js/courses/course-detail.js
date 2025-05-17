let chapterIndex = 0;

document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("chaptersContainer");
    const templateUrl = container.getAttribute("data-template-url");

    chapterIndex = container.querySelectorAll(".chapter-block").length;
    loadTemplate(templateUrl, "templateHolder");
    initFormEventListeners();

    for (let i = 0; i < chapterIndex; i++) {
        const editorId = `chapter-${i}-description-editor`;
        const textareaId = `chapter-${i}-description`;
        createQuillEditor(editorId, textareaId, `Enter chapter ${i + 1} description...`);
    }

    createQuillEditor('editor', 'course-description', 'Enter course description here...');
    document.querySelector('form').onsubmit = function () {
        for (const [textareaId, quill] of Object.entries(quillEditors)) {
            const textarea = document.getElementById(textareaId);
            if (textarea) {
                textarea.value = quill.root.innerHTML;
            }
        }
    };

});

function initFormEventListeners() {
    document.querySelector("#addChapterBtn").addEventListener("click", function() {
        addChapter();
    });
}

function addChapter() {
    const template = document.getElementById("chapterTemplate");
    if (!template) {
        console.error("Template not found");
        return;
    }

    const clone = document.importNode(template.content, true);
    const chapterBlock = clone.querySelector(".chapter-block");
    chapterBlock.dataset.index = chapterIndex;

    const chapterOrderInput = chapterBlock.querySelector('input[name$=".chapterOrder"]');
    if (chapterOrderInput) {
        chapterOrderInput.value = chapterIndex + 1;
    }

    const indexText = chapterBlock.querySelector(".chapterIndexText");
    if (indexText) {
        indexText.textContent = chapterIndex + 1;
    }

    const allNamedFields = chapterBlock.querySelectorAll("[name]");
    allNamedFields.forEach(field => {
        field.name = field.name.replace(/\[__chapterIndex__\]/g, `[${chapterIndex}]`).replace(/__chapterIndex__/g, `${chapterIndex}`);
    });

    const allIdFields = chapterBlock.querySelectorAll("[id]");
    allIdFields.forEach(field => {
        field.id = field.id.replace(/__chapterIndex__/g, `${chapterIndex}`);
    });

    const container = document.getElementById("chaptersContainer");

    container.appendChild(chapterBlock);

    const editorId = `chapter-${chapterIndex}-description-editor`;
    const textareaId = `chapter-${chapterIndex}-description`;
    createQuillEditor(editorId, textareaId, `Enter chapter ${chapterIndex + 1} description...`);

    chapterIndex++;
    updateChapterEditors();
}

function addLecture(button) {
    const chapterBlock = button.closest(".chapter-block");
    const indexOfChapterToAddLecture = chapterBlock.dataset.index;
    const lecturesContainer = chapterBlock.querySelector(".lecturesContainer");
    console.log(lecturesContainer);

    const lectureIndexInChapter = lecturesContainer.querySelectorAll(".lecture-block").length;
    console.log(lectureIndexInChapter);

    const template = document.getElementById("lectureTemplate");
    const clone = document.importNode(template.content, true);

    const fields = clone.querySelectorAll("[name]");
    fields.forEach(field => {
        field.name = field.name
            .replace("__chapterIndex__", indexOfChapterToAddLecture)
            .replace("__lectureIndex__", lectureIndexInChapter);
    });

    const lectureIndexText = clone.querySelector(".lectureIndexText");
    if (lectureIndexText) {
        lectureIndexText.textContent = lectureIndexInChapter + 1;
    }
    const lectureOrderInput = clone.querySelector('input[name$=".lectureOrder"]');
    if (lectureOrderInput) {
        lectureOrderInput.value = lectureIndexInChapter + 1;
    }

    const fileInput = clone.querySelector('input[type="file"]');
    const fileInputs = clone.querySelectorAll('input[type="file"]');
    fileInputs.forEach(input => {
        input.addEventListener("change", handleFileChange);
    });


    lecturesContainer.appendChild(clone);
}

function addResource(button) {
    const chapterBlock = button.closest(".chapter-block");
    const indexOfChapterToAddResource = chapterBlock.dataset.index;
    const resourcesContainer = chapterBlock.querySelector(".resourcesContainer");

    const resourceIndexInChapter = resourcesContainer.querySelectorAll(".resource-block").length;

    const template = document.getElementById("resourceTemplate");
    const clone = document.importNode(template.content, true);

    const fields = clone.querySelectorAll("[name]");
    fields.forEach(field => {
        field.name = field.name
            .replace("__chapterIndex__", indexOfChapterToAddResource)
            .replace("__resourceIndex__", resourceIndexInChapter);
    });

    const resourceIndexText = clone.querySelector(".resourceIndexText");
    if (resourceIndexText) {
        resourceIndexText.textContent = resourceIndexInChapter + 1;
    }

    const fileInput = clone.querySelector('input[type="file"]');
    const fileInputs = clone.querySelectorAll('input[type="file"]');
    fileInputs.forEach(input => {
        input.addEventListener("change", handleFileChange);
    });

    console.log(fileInput);
   resourcesContainer.appendChild(clone);
}

function removeChapter(button) {
    Swal.fire({
        title: 'Do you really want to delete this chapter?',
        text: "All the chapter's lessons and resources will be deleted! This action cannot be undone!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'Cancel'
    }).then((result) => {
        if (result.isConfirmed) {
            const chapterBlock = button.closest(".chapter-block");
            const indexOfChapterToRemove = chapterBlock.dataset.index;

            for (const [textareaId, quill] of Object.entries(quillEditors)) {
                const textarea = document.getElementById(textareaId);
                if (textarea) {
                    textarea.value = quill.root.innerHTML;
                }
            }

            const editorId = `chapter-${indexOfChapterToRemove}-description-editor`;
            const textareaId = `chapter-${indexOfChapterToRemove}-description`;

            if (quillEditors[textareaId]) {
                const quill = quillEditors[textareaId];

                const editorContainer = document.getElementById(editorId);
                if (editorContainer) {
                    editorContainer.parentElement.removeChild(editorContainer);
                }

                const toolbarContainer = document.querySelector(`#${editorId} + .ql-toolbar`);
                if (toolbarContainer) {
                    toolbarContainer.parentElement.removeChild(toolbarContainer);
                }

                delete quillEditors[textareaId];
            }

            chapterBlock.remove();

            const allChapters = document.querySelectorAll(".chapter-block");

            allChapters.forEach((chapter, index) => {
                chapter.dataset.index = index;

                const chapterIndexText = chapter.querySelector("h5 span");
                if (chapterIndexText) {
                    chapterIndexText.textContent = index + 1;
                }

                const chapterOrderInput = chapter.querySelector('input[name$=".chapterOrder"]');
                if (chapterOrderInput) {
                    chapterOrderInput.value = index + 1;
                }

                const allNamedFields = chapter.querySelectorAll("[name]");
                allNamedFields.forEach(field => {
                    field.name = field.name.replace(/\[chapterList\]\[\d+\]/g, `[chapterList][${index}]`);
                });

                const allIdFields = chapter.querySelectorAll("[id]");
                allIdFields.forEach(field => {
                    field.id = field.id.replace(/chapter-\d+/, `chapter-${index}`);
                });
            });
            chapterIndex = allChapters.length;
            updateChapterEditors();

            Swal.fire(
                'Deleted!',
                'The chapter will be deleted permanently when you click save changes button.',
                'success'
            );
        }
    });
}

function removeLecture(button) {
    Swal.fire({
        title: 'Do you really want to delete this lecture?',
        text: "This action cannot be undone!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'Cancel'
    }).then((result) => {
        if (result.isConfirmed) {
            const lectureBlock = button.closest(".lecture-block");
            const lecturesContainer = lectureBlock.parentElement;

            lectureBlock.remove();

            const remainingLectures = lecturesContainer.querySelectorAll(".lecture-block");

            remainingLectures.forEach((lecture, index) => {
                const headerSpan = lecture.querySelector("h6 > span");
                if (headerSpan) {
                    headerSpan.textContent = index + 1;
                }

                const inputs = lecture.querySelectorAll("input, textarea");
                inputs.forEach(input => {
                    let name = input.getAttribute("name");
                    if (name) {
                        const newName = name.replace(/\lectureList\[\d+\]/, `lectureList[${index}]`);
                        input.setAttribute("name", newName);
                    }
                });

                const orderInput = lecture.querySelector('input[name*="lectureOrder"]');
                if (orderInput) {
                    orderInput.value = index + 1;
                }
            });

            Swal.fire(
                'Deleted!',
                'The lecture will be deleted permanently when you click save changes button.',
                'success'
            );
        }
    });
}


function removeResource(button) {
    Swal.fire({
        title: 'Do you really want to delete this resource?',
        text: "This action cannot be undone!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'Cancel'
    }).then((result) => {
        if (result.isConfirmed) {
            const resourceBlock = button.closest(".resource-block");
            const resourcesContainer = resourceBlock.parentElement;

            resourceBlock.remove();

            const remainingResources = resourcesContainer.querySelectorAll(".resource-block");

            remainingResources.forEach((resource, index) => {
                const headerSpan = resource.querySelector("h6 > span");
                if (headerSpan) {
                    headerSpan.textContent = index + 1;
                }

                const inputs = resource.querySelectorAll("input, textarea");
                inputs.forEach(input => {
                    let name = input.getAttribute("name");
                    if (name) {
                        const newName = name.replace(/resourceList\[\d+]/g, `resourceList[${index}]`);
                        input.setAttribute("name", newName);
                    }

                    let id = input.getAttribute("id");
                    if (id) {
                        const newId = id.replace(/resourceList_\d+/, `resourceList_${index}`);
                        input.setAttribute("id", newId);
                    }
                });

            });

            Swal.fire(
                'Deleted!',
                'The resource will be deleted permanently when you click save changes button.',
                'success'
            );
        }
    });
}
