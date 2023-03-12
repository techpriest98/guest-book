const PAGES = {
    DASHBOARD: 'DASHBOARD',
    ADD_GUEST_FORM: 'ADD_GUEST_FORM'
}

const hidePortals = () => {
    const portalHolder = document.querySelector('#portal-holder');

    while (portalHolder.firstChild) {
        portalHolder.removeChild(portalHolder.lastChild);
    }
}

/* Components */
const getFormItem = (labelText, children) => {
    const component = document.createElement('div');
    component.style.marginBottom = '16px';

    const label = document.createElement('div');
    label.textContent = labelText;

    component.append(label, children);

    return component;
}

const getTextArea = ({width, height, value, onChange}) => {
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

const getInput = ({width, value, onChange}) => {
    const input = document.createElement('input');
    input.value = value;
    input.style.width = `${width}px`;
    input.onchange = e => {
        onChange(e.currentTarget.value);
    }

    return input;
}

const getNumberInput = ({value, onChange}) => {
    const input  = document.createElement('input');
    input.type = 'number';
    input.value = value;
    input.min = 1;
    input.max = 5;
    input.onchange = e => {
        onChange(e.currentTarget.value);
    }

    return input
}

const getOverlay = (children) => {
    const overlay = document.createElement('div');
    overlay.style.position = 'absolute';
    overlay.style.display = 'flex';
    overlay.style.justifyContent = 'center';
    overlay.style.alignItems = 'center';
    overlay.style.width = '100%';
    overlay.style.height = '100%';
    overlay.style.top = 0;
    overlay.style.left = 0;
    overlay.style.backgroundColor = 'rgba(0, 0, 0, 0.5)';

    overlay.append(children);

    return overlay;
}

const getButton = (caption, onClick) => {
    const button = document.createElement('button');
        button.textContent = caption;
        button.onclick = onClick;

    return button;
}

const getErrorMessage = (message) => {
    const error = document.createElement('div');
        error.style.display = 'flex';
        error.style.padding = '16px';
        error.style.justifyContent = 'space-between';
        error.style.width = '600px';
        error.style.height = '60px';
        error.style.border = '2px solid #6b0a09';
        error.style.backgroundColor = '#CD8A7FFF';

        const label = document.createElement('span');
            label.style.color = '#6b0a09';
            label.textContent = message;

        const closeButton = document.createElement('button');
            closeButton.textContent = 'x';
            closeButton.style.height = '25px';
            closeButton.style.backgroundColor = 'transparent';
            closeButton.onclick = hidePortals;
    error.append(label, closeButton);

    return getOverlay(error);
}



document.addEventListener('DOMContentLoaded',() => {
    let currentPage = PAGES.DASHBOARD;

    const rootNode = document.querySelector('#root');

    const header = document.createElement('div');
        header.style.display = 'flex';
        header.style.justifyContent =  'space-between';
        header.style.backgroundColor = '#334152';
        header.style.padding = '16px';

        const headerTitle = document.createElement('h2');
            headerTitle.textContent = 'Guest Book';
            headerTitle.style.color = '#ffffff';
            headerTitle.style.margin = 0;
            headerTitle.onclick = () => {
                currentPage = PAGES.DASHBOARD;
                buildMainContent();
            }

        const addButton = getButton('Add New Feedback', () => {
            currentPage = PAGES.ADD_GUEST_FORM;
            buildMainContent();
        });
    header.append(headerTitle, addButton);

    const mainContent = document.createElement('div');
        mainContent.style.padding = '16px';

    const loadFeedbacksPage = () => {
        const feedbackCardsList = document.createElement('div');
        const loadFeedbacks = async () => {
            const response = await fetch('http://localhost:8880/api/feedbacks');
            const feedbacks = await response.json();

            feedbacks.forEach(({id, authorName, feedback, feedbackDate, rating}, i) => {
                const feedbackCardState = {
                    rating,
                    feedback
                }

                const feedbackCard = document.createElement('div');
                    feedbackCard.style.backgroundColor = '#f7f8fa';
                    feedbackCard.style.padding = '16px';
                    feedbackCard.style.border = '1px solid black';

                    if (i !== feedbacks.length - 1) {
                        feedbackCard.style.marginBottom = '16px';
                    }

                    const feedbackHeader = document.createElement('div');
                        feedbackHeader.style.paddingBottom = '8px';
                        feedbackHeader.style.display = 'flex';
                        feedbackHeader.style.justifyContent = 'space-between';

                        const ratingLabel = document.createElement('div');
                            ratingLabel.textContent = `Rating: ${feedbackCardState.rating}`;

                        const actionCorner = document.createElement('div');
                            actionCorner.style.display = 'flex';
                            actionCorner.style.justifyContent = 'space-between';

                            const editButton = getButton('Edit', () => {
                                showEditFeedbackModal({id, feedback, rating});
                            });

                            const deleteButton = getButton('Delete', async () => {
                                await fetch(`http://localhost:8880/api/feedbacks/${id}`, {
                                    method: 'DELETE'
                                });
                                buildMainContent();
                            });
                        actionCorner.append(editButton, deleteButton);
                    feedbackHeader.append(ratingLabel, actionCorner);

                    const feedbackLabel = document.createElement('div');
                        feedbackLabel.textContent = feedback;
                        feedbackLabel.style.paddingBottom = '8px';

                    const feedbackFooter = document.createElement('div');
                        feedbackFooter.style.display = 'flex';
                        feedbackFooter.style.justifyContent = 'space-between';

                        const authorNameLabel = document.createElement('div');
                            authorNameLabel.textContent = authorName;

                        const feedbackDateLabel = document.createElement('div');
                            feedbackDateLabel.textContent = feedbackDate.split('.')[0].split('T').join(' ');
                    feedbackFooter.append(authorNameLabel, feedbackDateLabel);
                feedbackCard.append(feedbackHeader, feedbackLabel, feedbackFooter)

                feedbackCardsList.append(feedbackCard);
            });
        }
        loadFeedbacks().then(() => mainContent.append(feedbackCardsList));
    }

    const loadAddFeedbackPage = () => {
        const addGuestState = {
            authorName: '',
            feedback: '',
            feedbackDate: new Date().toISOString(),
            rating: 1
        }

        const authorBlock = getFormItem('Author name:', getInput({
            width: 400,
            value: addGuestState.authorName,
            onChange: value => addGuestState.authorName = value
        }));

        const feedbackBlock = getFormItem('Feedback:', getTextArea({
            width: 400,
            height: 200,
            value: addGuestState.feedback,
            onChange: value => addGuestState.feedback = value
        }));

        const ratingBlock = getFormItem('Rating:', getNumberInput({
            value: addGuestState.rating,
            onChange: value => addGuestState.rating = value
        }));

        const submit = getButton('Publish feedback', async () => {
            await fetch('http://localhost:8880/api/feedbacks', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(addGuestState)
            });

            currentPage = PAGES.DASHBOARD;
            buildMainContent();
        });

        mainContent.append(authorBlock, feedbackBlock, ratingBlock, submit);
    }

    const buildMainContent = () => {
        while (mainContent.firstChild) {
            mainContent.removeChild(mainContent.lastChild);
        }

        switch (currentPage) {
            case PAGES.DASHBOARD:
                loadFeedbacksPage();
                break;

            case PAGES.ADD_GUEST_FORM:
                loadAddFeedbackPage();
                break;
        }
    };

    const showEditFeedbackModal = ({id, feedback, rating}) => {
        const feedbackState = {
            feedback,
            rating
        }

        const portalHolder = document.querySelector('#portal-holder');

        const modal = document.createElement('div');
            modal.style.display = 'flex';
            modal.style.flexDirection = 'column';
            modal.style.width = '600px';
            modal.style.backgroundColor = 'white';
            modal.style.height = '400px';

            const modalHeader = document.createElement('div');
                modalHeader.style.padding = '8px';
                modalHeader.style.display = 'flex';
                modalHeader.style.justifyContent = 'space-between';
                modalHeader.style.alignItems = 'center';
                modalHeader.style.borderBottom = '1px solid black';

                const modalCaption = document.createElement('strong');
                    modalCaption.textContent = 'Edit Feedback';

                const closeButton = getButton('x', hidePortals);

            modalHeader.append(modalCaption, closeButton);

            const modalBody = document.createElement('div');
                modalBody.style.display = 'flex';
                modalBody.style.padding = '8px';
                modalBody.style.flexDirection = 'column';
                modalBody.style.borderBottom = '1px solid black';

                const feedbackBlock = getFormItem('Feedback:', getTextArea({
                    width: 578,
                    height: 214,
                    value: feedbackState.feedback,
                    onChange: value => feedbackState.feedback = value
                }));

                const ratingBlock = getFormItem('Rating:', getNumberInput({
                    value: feedbackState.rating,
                    onChange: value => feedbackState.rating = value
                }));

            modalBody.append(feedbackBlock, ratingBlock);

            const modalFooter = document.createElement('div');
                modalFooter.style.padding = '8px';
                modalFooter.style.display = 'flex';
                modalFooter.style.alignItems = 'center';

                const saveButton = getButton('Save', async () => {
                    hidePortals();

                    const response = await fetch(`http://localhost:8880/api/feedbacks/${id}`, {
                        method: 'PUT',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(feedbackState)
                    });

                    if (response.status === 200) {
                        buildMainContent();
                    } else {
                        const error = await response.json();
                        portalHolder.append(getErrorMessage(error.error));
                    }
                });

                const cancelButton = getButton('Cancel', hidePortals);
            modalFooter.append(saveButton, cancelButton);
        modal.append(modalHeader, modalBody, modalFooter);

        portalHolder.append(getOverlay(modal));
    }

    loadFeedbacksPage();

    rootNode.append(header);
    rootNode.append(mainContent);
});
