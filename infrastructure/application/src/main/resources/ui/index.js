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

document.addEventListener('DOMContentLoaded',() => {
    const mainContent = document.createElement('div');
    mainContent.className = 'main-content';

    RootNode([
        Header([
            HeaderTitle(() => {
                alert('Redirect to feedback list');
            }),
            Button('Add New Feedback', () => {
                alert('Redirect to add feedback form');
            })
        ]),
        mainContent
    ]);
});
