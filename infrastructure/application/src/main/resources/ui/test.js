const render = (root, responseBody) => {
    const elements = responseBody.map(({id, authorName, feedback, feedbackDate, rating}) => {
        const container = document.createElement('div');
        container.style.display = 'flex';
        container.style.width = '600px';
        container.style.justifyContent = 'space-around';

        const label1 = document.createElement('div');
        label1.textContent = id;


        const label2 = document.createElement('div');
        label2.textContent = authorName;

        const label3 = document.createElement('div');
        label3.textContent = feedback;

        const label4 = document.createElement('div');
        label4.textContent = feedbackDate;

        const label5 = document.createElement('div');
        label5.textContent = rating;

        container.append(label1, label2, label3, label4, label5);

        return container;
    });

    elements.forEach(element => root.append(element));
};

document.addEventListener('DOMContentLoaded',async () => {
    const rootNode = document.querySelector('#root');

    fetch('http://localhost:8880/api/feedbacks').then(response => {
        if (response.status === 200) {
            return response.json()
        }
    }).then(body => render(root, body));

    fetch('http://localhost:8880/api/feedbacks').then(response => {
        if (response.status === 200) {
            return response.json().then(body => render(root, body))
        }
    });

    const response = await fetch('http://localhost:8880/api/feedbacks');

    if (response.status === 200) {
        const responseBody = await response.json();

        render(rootNode, responseBody);
    }
});
