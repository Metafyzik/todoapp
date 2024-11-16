let httpRequest;
let adjustedItemId;

const PUT = 'PUT';
const POST = 'POST';

function deleteItem(id, next_var) {
    console.log(next_var);

    const url = window.location.href+id; //TODO how to change and route address dynamically

    fetch (url, {method: "DELETE"})
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok " + response.statusText);
        } else {
            console.log("item deleted");
            location.reload();
        }
    })
}

function addItem() {
    const url = window.location.href; //change name "url" to something better

    const title = document.getElementById('title').value;
    const task = document.getElementById('task').value;

    const errorElement = document.getElementById("error");
    let messages = [];

    if (title === '' ) {
        messages.push("Title is required");
        console.log("title empt");
    }
    if (task === '') {
        messages.push("Task is required");
    }

    if (messages.length == 0) {
        const formData = {
            title: title,
            task: task
        };

        fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok " + response.statusText);
            } else {
                console.log("item added");
                location.reload();
            }
        })
    } else {
        errorElement.innerText = messages.join(',');
    }
}

function openModal() {
    document.getElementById("modal").style.display = "flex";
}

function initializeFormForPut(clickedItemId, itemIndex) {

        httpRequest = PUT;
        adjustedItemId = clickedItemId;

        const currTitleText = document.getElementById(itemIndex +"title").textContent;
        const currTaskText = document.getElementById(itemIndex +"task").textContent;

        document.getElementById('title').value = currTitleText;
        document.getElementById('task').value = currTaskText;

        openModal();
}

function initializeFormForPOST() {
        httpRequest = POST;
        openModal();
}



// Function to close the modal
function closeModal() {
    deleteFormText();
    deleteErrorMessages();
    document.getElementById("modal").style.display = "none";
}

// Close the modal if the user clicks outside of it
window.onclick = function(event) {
  var modal = document.getElementById("modal");
  if (event.target === modal) {
    closeModal();
  }
}

function deleteErrorMessages() {
    const errorElement = document.getElementById("error");
    errorElement.innerText ='';
}



function adjustItem(id) {

    const url = window.location.href; //change name "url" to something better

    //check of empty values
    const title = document.getElementById('title').value;
    const task = document.getElementById('task').value;

    //const currTitleText = document.getElementById(itemIndex +"title").textContent;
    //const currTaskText = document.getElementById(itemIndex +"task").textContent;

    //document.getElementById('title').value =currTitleText;
    //document.getElementById('task').value= currTaskText;

    //TODO add check if any of any value actually changed
    const errorElement = document.getElementById("error");
    let messages = [];


    if ( title === '' ) {
        messages.push("Title is required");
    } else if (task === ''){
        messages.push("Task is required");
    }

    if (messages.length ==0) {

        const formData = {
            id: id,
            title: title,
            task: task
        };

        fetch(url, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok " + response.statusText);
            } else {
                console.log("item adjusted");
                location.reload();
            }
        })
    } else {
        errorElement.innerText = messages.join(',');
    }
}

function deleteFormText() {
    document.getElementById('title').value ='';
    document.getElementById('task').value= '';
}

function formButtonClicked() {
    if (httpRequest == 'PUT') {
        adjustItem(adjustedItemId);
    } else if (httpRequest == 'POST') {
        addItem();
    }
}
