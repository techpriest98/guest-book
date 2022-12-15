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
    mainContent.style.padding = '16px';

    const loadFeedbacksPage = () => {
        const feedbackCardsList = document.createElement('div');
        const loadFeedbacks = async () => {
            const response = await fetch('http://localhost:8880/api/feedbacks');
            const feedbacks = await response.json();


            feedbacks.forEach(({authorName, feedback, feedbackDate, rating}, i) => {
                const feedbackCard = document.createElement('div');
                feedbackCard.style.backgroundColor = '#f7f8fa';
                feedbackCard.style.padding = '16px';
                feedbackCard.style.border = '1px solid black';
                if (i !== feedbacks.length - 1) {
                    feedbackCard.style.marginBottom = '16px';
                }

                const feedbackHeader = document.createElement('div');
                feedbackHeader.style.paddingBottom = '8px';
                const ratingLabel = document.createElement('div');
                ratingLabel.textContent = `Rating: ${rating}`;
                feedbackHeader.append(ratingLabel);

                const feedbackLabel = document.createElement('div');
                feedbackLabel.textContent = feedback;
                feedbackLabel.style.paddingBottom = '8px';

                const feedbackFooter = document.createElement('div');
                feedbackFooter.style.display = 'flex';
                feedbackFooter.style.justifyContent = 'space-between';
                const authorNameLabel = document.createElement('div');
                authorNameLabel.textContent = authorName;
                const feedbackDateLabel = document.createElement('div');
                feedbackDateLabel.textContent = feedbackDate
                    .split('.')[0]
                    .split('T')
                    .join(' ');
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
            feedbackDate: '',
            rating: 1
        }
        const submit = document.createElement('button');
        submit.textContent = "Add Guest";
        submit.onclick = async () => {
            addGuestState.feedbackDate = new Date().toISOString();
            await fetch('http://localhost:8880/api/feedback/add', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(addGuestState)
            });

            currentPage = PAGES.DASHBOARD;
            buildMainContent();
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
    }

    loadFeedbacksPage();
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

    rootNode.append(header);
    rootNode.append(mainContent);

});
