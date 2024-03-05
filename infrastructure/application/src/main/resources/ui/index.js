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

const FormItem = (labelText, children) => {
    const component = document.createElement('div');
    component.className = 'form-item';

    const label = document.createElement('div');
    label.textContent = labelText;

    component.append(label, ...children);

    return component;
}

const Input = ({width, value, onChange}) => {
    const input = document.createElement('input');
    input.value = value;
    input.style.width = `${width}px`;
    input.onchange = e => {
        onChange(e.currentTarget.value);
    }

    return input;
}

const TextArea = ({width, height, value, onChange}) => {
    const textArea = document.createElement('textarea');
    textArea.value = value;
    textArea.style.width = `${width}px`;
    textArea.style.maxWidth = `${width}px`;
    textArea.style.height = `${height}px`;
    textArea.style.maxHeight =`${height}px`;
    textArea.onchange = e => {
        onChange(e.currentTarget.value);
    }

    return textArea;
}

/* Modules */

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

const AddFeedbackPage = (onSuccess) => {
    const addFeedbackPage = document.createElement('div');
    const addGuestState = {
        authorName: '',
        feedback: ''
    }

    addFeedbackPage.append(
        FormItem('Author name:', [
            Input({
                width: 400,
                value: addGuestState.authorName,
                onChange: value => addGuestState.authorName = value
            })
        ]),
        FormItem('Feedback:', [
            TextArea({
                width: 400,
                height: 200,
                value: addGuestState.feedback,
                onChange: value => addGuestState.feedback = value
            })
        ]),
        Button('Publish feedback', () => {
            const {authorName, feedback} = addGuestState;
            alert(`${authorName}, ${feedback}`);
            onSuccess();
        })
    );

    return addFeedbackPage;
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
            mainContentContainer.append(
                AddFeedbackPage(() => buildMainContent(
                    PAGES.DASHBOARD,
                    mainContentContainer
                ))
            );
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
