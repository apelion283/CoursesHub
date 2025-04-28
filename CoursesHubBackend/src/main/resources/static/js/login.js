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

document.addEventListener('DOMContentLoaded', function() {
    const loginButton = document.getElementById('login-button');
    const backButton = document.getElementById('back-button');
    const welcomeContainer = document.getElementById('welcome-container');
    const loginFormContainer = document.getElementById('login-form-container');

    window.addEventListener('load', function() {
        loginFormContainer.style.display = 'none';
    })

    loginButton.addEventListener('click', function() {
        welcomeContainer.style.display = 'none';
        loginFormContainer.style.display = 'block';
    });

    backButton.addEventListener('click', function() {
        loginFormContainer.style.display = 'none';
        welcomeContainer.style.display = 'block';
    });
});
