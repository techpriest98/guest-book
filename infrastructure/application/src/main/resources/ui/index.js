const RootNode = (children) => {
    const rootNode = document.querySelector('#root');

    rootNode.append(...children);
    return rootNode;
}

const Button = (caption, onClick) => {
    const button = document.createElement('button');
    button.textContent = caption;
    button.onclick = onClick;

    return button;
}

/* Main Header */
const Header = children => {
    const header = document.createElement('div');
    header.className = 'header';

    header.append(...children);
    return header;
};

const HeaderTitle = onClick => {
    const headerTitle = document.createElement('h2');
    headerTitle.textContent = 'Guest Book';
    headerTitle.className = 'header__title';
    headerTitle.onclick = onClick;

    return headerTitle;
}

const PAGES = {
    DASHBOARD: 'DASHBOARD',
    ADD_GUEST_FORM: 'ADD_GUEST_FORM'
}

const buildMainContent = (currentPage, mainContentContainer) => {
    while (mainContentContainer.firstChild) {
        mainContentContainer.removeChild(mainContentContainer.lastChild);
    }

    switch (currentPage) {
        case PAGES.DASHBOARD:
            mainContentContainer.append("Feedbacks dashboard");
            break;

        case PAGES.ADD_GUEST_FORM:
            mainContentContainer.append("Add feedback form");
            break;
    }
};

document.addEventListener('DOMContentLoaded',() => {
    const mainContent = document.createElement('div');
    mainContent.className = 'main-content';

    RootNode([
        Header([
            HeaderTitle(() => {
                buildMainContent(PAGES.DASHBOARD, mainContent);

            }),
            Button('Add New Feedback', () => {
                buildMainContent(PAGES.ADD_GUEST_FORM, mainContent);
            })
        ]),
        mainContent
    ]);

    buildMainContent(PAGES.DASHBOARD, mainContent);
});
