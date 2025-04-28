function updateContentMinHeight() {
    const header = document.getElementById('header');
    const footer = document.getElementById('footer');
    const contentWrapper = document.getElementById('content-wrapper');

    if (header && footer && contentWrapper) {
        const headerHeight = header.offsetHeight;
        const footerHeight = footer.offsetHeight;
        const viewportHeight = window.innerHeight;

        const contentMinHeight = viewportHeight - headerHeight - footerHeight;
        contentWrapper.style.minHeight = `${contentMinHeight}px`;
    }
}

window.addEventListener('load', function () {
    updateContentMinHeight();

    if (window.particlesJS) {
        particlesJS.load('particles-js', '/CoursesHubBackend/configs/particles-config.json', function () {
            console.log('Particles.js config loaded.');
        });
    } else {
        console.error('particlesJS is not defined.');
    }
});

let resizeTimer;
window.addEventListener('resize', function () {
    clearTimeout(resizeTimer);
    resizeTimer = setTimeout(updateContentMinHeight, 150);
});
