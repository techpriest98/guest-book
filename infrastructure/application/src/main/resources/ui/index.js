const RootNode = (children) => {
    const rootNode = document.querySelector('#root');

    rootNode.append(...children);
    return rootNode;
}

document.addEventListener('DOMContentLoaded',() => {
    const mainContent = document.createElement('div');
    mainContent.className = 'main-content';

    RootNode([
        mainContent
    ]);
});
