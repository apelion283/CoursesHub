const toolbarOptions = [['bold', 'italic', 'underline', 'strike'],        // toggled buttons
    ['blockquote', 'code-block'],

    [{'header': 1}, {'header': 2}],               // custom button values
    [{'list': 'ordered'}, {'list': 'bullet'}, {'list': 'check'}], [{'script': 'sub'}, {'script': 'super'}],      // superscript/subscript
    [{'indent': '-1'}, {'indent': '+1'}],          // outdent/indent
    [{'direction': 'rtl'}],                         // text direction

    [{'size': ['small', false, 'large', 'huge']}],  // custom dropdown
    [{'header': [1, 2, 3, 4, 5, 6, false]}],

    [{'color': []}, {'background': []}],          // dropdown with defaults from theme
    [{'font': []}], [{'align': []}],

    ['clean']                                         // remove formatting button
];

const quillEditors = {};

function createQuillEditor(editorId, textareaId, placeholderText) {
    const editorContainer = document.getElementById(editorId);
    const textarea = document.getElementById(textareaId);

    if (!editorContainer || !textarea) {
        console.warn(`Không tìm thấy editor: #${editorId} hoặc textarea: #${textareaId}`);
        return;
    }

    const quill = new Quill(`#${editorId}`, {
        theme: 'snow', placeholder: placeholderText || 'Enter text here...', modules: {
            toolbar: toolbarOptions
        }
    });

    if (textarea.value) {
        quill.root.innerHTML = textarea.value;
    }

    quillEditors[textareaId] = quill;
}

function updateChapterEditors() {
    const allChapters = document.querySelectorAll(".chapter-block");

    allChapters.forEach((chapter, index) => {
        const newEditorId = `chapter-${index}-description-editor`;
        const newTextareaId = `chapter-${index}-description`;
        const textarea = document.getElementById(newTextareaId);
        const previousContent = textarea?.value || '';

        const oldToolbar = chapter.querySelector(".ql-toolbar");
        if (oldToolbar) oldToolbar.remove();

        const oldEditor = chapter.querySelector(".ql-editor");
        if (oldEditor) {
            const container = chapter.querySelector(`#${newEditorId}`);
            if (container) container.innerHTML = "";
        }

        delete quillEditors[newTextareaId];

        createQuillEditor(newEditorId, newTextareaId, `Enter chapter ${index + 1} description...`);

        const newQuill = quillEditors[newTextareaId];
        if (newQuill) {
            newQuill.root.innerHTML = previousContent;
        }
    });
}
