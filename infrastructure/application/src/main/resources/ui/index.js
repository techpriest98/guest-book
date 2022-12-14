document.addEventListener('DOMContentLoaded',() => {
    const PAGES = {
        DASHBOARD: 'DASHBOARD',
        ADD_GUEST_FORM: 'ADD_GUEST_FORM'
    }

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
    header.append(headerTitle);

    const addButton = document.createElement('button');
    addButton.textContent = 'Add new guest';
    addButton.onclick = () => {
        currentPage = PAGES.ADD_GUEST_FORM;
        buildMainContent();
    }
    header.append(addButton);

    const mainContent = document.createElement('div');
    const buildMainContent = () => {
        while (mainContent.firstChild) {
            mainContent.removeChild(mainContent.lastChild);
        }

        switch (currentPage) {
            case PAGES.DASHBOARD:
                const text = document.createElement('p');
                text.textContent = PAGES.DASHBOARD;
                mainContent.append(text);
            break;

            case PAGES.ADD_GUEST_FORM:
                const addGuestState = {
                    authorName: '',
                    feedback: '',
                    feedbackDate: '',
                    rating: 1
                }
                const submit = document.createElement('button');
                submit.textContent = "Add Guest";
                submit.onclick = async () => {
                    addGuestState.feedbackDate = new Date().toISOString().split('.')[0];
                    await fetch('http://localhost:8880/api/feedback/add', {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(addGuestState)
                    })
                };

                const authorBlock = document.createElement('div');
                    const authorNameLabel = document.createElement('div');
                    authorNameLabel.textContent = 'Author name:';
                    const authorNameInput = document.createElement('input');
                    authorBlock.append(authorNameLabel, authorNameInput);
                    authorNameInput.onchange = e => {
                        addGuestState.authorName = e.currentTarget.value;
                    }

                const feedbackBlock = document.createElement('div');
                    const feedbackLabel = document.createElement('div');
                    feedbackLabel.textContent = 'Feedback:';
                    const feedbackArea = document.createElement('textarea');
                    feedbackArea.onchange = e => {
                        addGuestState.feedback = e.currentTarget.value;
                    }
                    feedbackBlock.append(feedbackLabel, feedbackArea);

                const ratingBlock = document.createElement('div');
                    const ratingLabel = document.createElement('div');
                    ratingLabel.textContent = 'Rating:';
                    const ratingInput = document.createElement('input');
                    ratingInput.type = 'number';
                    ratingInput.value = addGuestState.rating;
                    ratingInput.min = 1;
                    ratingInput.max = 5;
                    ratingInput.onchange = e => {
                        addGuestState.rating = e.currentTarget.value;
                    }
                    ratingBlock.append(ratingLabel, ratingInput);

                mainContent.append(authorBlock, feedbackBlock, ratingBlock, submit);
            break;
        }
    };

    rootNode.append(header);
    rootNode.append(mainContent);

});
