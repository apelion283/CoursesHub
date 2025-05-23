async function loadTemplate(url, targetElementId) {
    try {
        const res = await fetch(url);
        const html = await res.text();
        document.getElementById(targetElementId).innerHTML = html;
    } catch (error) {
        console.error("Error loading template:", error);
    }
}
