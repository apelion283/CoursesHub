function updateContentMinHeight() {
    const header = document.getElementById('header');
    const footer = document.getElementById('footer');
    const content = document.getElementById('content-wrapper');

    if (header && footer && content) {
        content.style.minHeight = `${window.innerHeight - header.offsetHeight - footer.offsetHeight}px`;
    } else {
        console.error('Cannot update content min-height due to missing elements.');
    }
}

window.addEventListener('resize', () => {
    clearTimeout(window.resizeTimer);
    window.resizeTimer = setTimeout(updateContentMinHeight, 150);
});

document.addEventListener('DOMContentLoaded', () => {
    const loginBtn = document.getElementById('login-button');
    const backBtn = document.getElementById('back-button');
    const welcome = document.getElementById('welcome-container');
    const loginForm = document.getElementById('login-form-container');

    window.addEventListener('load', () => {
        loginForm.style.display = 'none';
        updateContentMinHeight();

        if (window.particlesJS) {
            particlesJS.load('particles-js', '/CoursesHubBackend/configs/particles-config.json', () =>
                console.log('Loaded Particles.js config!')
            );
        } else {
            console.error('particlesJS undefined!.');
        }
    });

    loginBtn?.addEventListener('click', () => {
        welcome.style.display = 'none';
        loginForm.style.display = 'block';
    });

    backBtn?.addEventListener('click', () => {
        loginForm.style.display = 'none';
        welcome.style.display = 'block';
    });
});
