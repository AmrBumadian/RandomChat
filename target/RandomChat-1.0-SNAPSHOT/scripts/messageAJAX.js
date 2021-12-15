let status = "loading";

function sendMessage() {
    let messageRequest = new XMLHttpRequest();
    let typingBox = document.getElementById("message");
    let message = typingBox.value.trim();
    typingBox.value = "";
    document.getElementById("messages").innerHTML += messageDiv(message, "You");
    messageRequest.open("POST", "textchat/messaging");
    messageRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    messageRequest.send("message=" + message);
}

function connect() {
    let connectionRequest = new XMLHttpRequest();
    connectionRequest.onreadystatechange = function () {
        setConnected();
    }
    connectionRequest.open("Post", "textchat");
    connectionRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    connectionRequest.send("operation=connect");
}

function disconnect() {
    let connectionRequest = new XMLHttpRequest();
    connectionRequest.onreadystatechange = function () {
        setDisconnected("You");
    }
    connectionRequest.open("Post", "textchat");
    connectionRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    connectionRequest.send("operation=disconnect");
}

setInterval(pollMessages, 1000);

function pollMessages() {
    let messagesRequest = new XMLHttpRequest();
    messagesRequest.onreadystatechange = function () {
        pollingMessagesRequestAction(this);
    }
    messagesRequest.open("GET", "textchat/messaging");
    messagesRequest.setRequestHeader("accept", "text/plain");
    messagesRequest.send();
}

function pollingMessagesRequestAction(response) {
    if (response.readyState === 4) {
        if (response.status === 200) {
            console.log(response.responseText);
            let list = response.responseText.split("\n");
            list.forEach((message) => {
                if (message !== "") {
                    document.getElementById("messages").innerHTML += messageDiv(message, "Stranger");
                }
            });
        } else if (response.status === 101) {
            setDisconnected("stranger");
        }
    }
}
