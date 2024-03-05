const hidePortals = () => {
    const portalHolder = document.querySelector('#portal-holder');

    while (portalHolder.firstChild) {
        portalHolder.removeChild(portalHolder.lastChild);
    }
}

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

const NumberInput = ({value, onChange}) => {
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

const Overlay = (children) => {
    const overlay = document.createElement('div');
    overlay.className = 'overlay';

    overlay.append(children);

    return overlay;
}

const ErrorMessage = (message) => {
    const error = document.createElement('div');
    error.className = 'error-message';

    const label = document.createElement('span');
    label.className = 'error-message__label';
    label.textContent = message;

    const closeButton = document.createElement('button');
    closeButton.className = 'error-message__close-button';
    closeButton.textContent = 'x';
    closeButton.onclick = hidePortals;

    error.append(label, closeButton);

    return error;
}

const HorizontalGroup = (children, width) => {
    const horizontalGroup = document.createElement('div');
    horizontalGroup.className = 'horizontal-group';

    if (width) {
        horizontalGroup.style.width = `${width}px`;
    }

    horizontalGroup.append(...children)

    return horizontalGroup;
}

/* Modal */
const Modal = ({width, height, caption, onClose, body, footer}) => {
    const modal = document.createElement('div');
    modal.className = 'modal'
    modal.style.width = `${width}px`;
    modal.style.height = `${height}px`;

    const modalHeader = document.createElement('div');
    modalHeader.className = 'modal__header';

    const modalCaption = document.createElement('strong');
    modalCaption.textContent = caption;

    const closeButton = Button('x', onClose);

    modalHeader.append(modalCaption, closeButton);

    const modalBody = document.createElement('div');
    modalBody.className = 'modal__body';
    modalBody.append(...body);

    const modalFooter = document.createElement('div');
    modalFooter.className = 'modal__footer';
    modalFooter.append(footer);

    modal.append(modalHeader, modalBody, modalFooter);

    return modal;
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

/* Feedback card */
const RatingLabel = rating => {
    const label = document.createElement('div');
    label.textContent = `Rating: ${Array.from({length: rating}, () => '*') .join(' ')}`;

    return label;
}

const FeedbackHeader = (rating, onEdit, onDelete) => {
    const feedbackHeader = document.createElement('div');
    feedbackHeader.className = 'feedback-card__child';

    feedbackHeader.append(
        HorizontalGroup([
            RatingLabel(rating),
            HorizontalGroup([
                Button('Edit', onEdit),
                Button('Delete', onDelete)
            ], 100)
        ])
    );

    return feedbackHeader;
}

const FeedbackLabel = (feedback) => {
    const feedbackLabel = document.createElement('div');
    feedbackLabel.textContent = feedback;
    feedbackLabel.className = 'feedback-card__child';

    return feedbackLabel;
}

const FeedbackFooter = (authorName, feedbackDate) => {
    const authorNameLabel = document.createElement('div');
    authorNameLabel.textContent = authorName;

    const feedbackDateLabel = document.createElement('div');
    feedbackDateLabel.textContent = new Date(feedbackDate)
        .toLocaleString('en-US', {timeZone: 'UTC'});

    return HorizontalGroup([
        authorNameLabel,
        feedbackDateLabel
    ]);
}

const FeedbackCard = ({rating, feedback, authorName, feedbackDate, onEdit, onDelete}) => {
    const feedbackCard = document.createElement('div');
    feedbackCard.className = 'feedback-card';

    feedbackCard.append(
        FeedbackHeader(rating, onEdit, onDelete),
        FeedbackLabel(feedback),
        FeedbackFooter(authorName, feedbackDate)
    )

    return feedbackCard;
}

const AddFeedbackPage = (onSuccess) => {
    const addFeedbackPage = document.createElement('div');
    const addGuestState = {
        authorName: '',
        feedback: '',
        rating: 1
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
        FormItem('Rating:', [
            NumberInput({
                value: addGuestState.rating,
                onChange: value => addGuestState.rating = value
            })
        ]),
        Button('Publish feedback', async () => {
            const response = await fetch('http://localhost:8880/api/feedbacks', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(addGuestState)
            });

            if (response.status === 200) {
                onSuccess();
            } else {
                const errorResponse = await response.json();

                const portalHolder = document.querySelector('#portal-holder');
                portalHolder.append(
                    Overlay(ErrorMessage(errorResponse.error))
                );
            }
        })
    );

    return addFeedbackPage;
}

const FeedbacksPage = (onLoad, onEdit, onRefresh) => {
    const feedbackCardsList = document.createElement('div');
    const loadFeedbacks = async () => {
        const response = await fetch('http://localhost:8880/api/feedbacks');
        const feedbacks = await response.json();

        feedbacks.forEach(({id, authorName, feedback, feedbackDate, rating}) => {
            feedbackCardsList.append(
                FeedbackCard({
                    feedback,
                    authorName,
                    feedbackDate,
                    rating,
                    onEdit: () => {
                        onEdit({id, feedback, rating, onSuccess: onRefresh});
                    },
                    onDelete: async () => {
                        const response = await fetch(`http://localhost:8880/api/feedbacks/${id}`, {
                            method: 'DELETE'
                        });

                        if (response.status === 200) {
                            onRefresh();
                        } else {
                            const error = await response.json();

                            const portalHolder = document.querySelector('#portal-holder');
                            portalHolder.append(
                                Overlay(ErrorMessage(error.error))
                            );
                        }
                    },
                })
            );
        });
    }

    loadFeedbacks().then(() => onLoad(feedbackCardsList));
}

const EditFeedbackModal = ({id, feedback, rating, onSuccess}) => {
    const feedbackState = {
        feedback,
        rating
    }

    const portalHolder = document.querySelector('#portal-holder');

    portalHolder.append(
        Overlay(
            Modal({
                width: 600,
                height: 400,
                caption: 'Edit Feedback',
                onClose: hidePortals,
                body: [
                    FormItem('Feedback:', [
                        TextArea({
                            width: 578,
                            height: 214,
                            value: feedbackState.feedback,
                            onChange: value => feedbackState.feedback = value
                        })
                    ]),
                    FormItem('Rating:', [
                        NumberInput({
                            value: feedbackState.rating,
                            onChange: value => feedbackState.rating = value
                        })
                    ])
                ],
                footer: HorizontalGroup([
                    Button('Save', async () => {
                        hidePortals();

                        const response = await fetch(`http://localhost:8880/api/feedbacks/${id}`, {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(feedbackState)
                        });

                        if (response.status === 200) {
                            onSuccess()
                        } else {
                            const error = await response.json();
                            portalHolder.append(
                                Overlay(ErrorMessage(error.error))
                            );
                        }
                    }),
                    Button('Cancel', hidePortals)
                ], 120)
            })
        )
    );
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
            FeedbacksPage(
                list => mainContentContainer.append(list),
                EditFeedbackModal,
                () => buildMainContent(PAGES.DASHBOARD, mainContentContainer)
            );
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
