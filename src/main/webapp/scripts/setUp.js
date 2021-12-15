addListeners();
function EscAndEnterCallBack(event) {
    if (event.key === "Escape") {
        console.log("esc");
        disconnect();
    } else if (event.key === "Enter") {
        sendMessage();
    }
}

function addListeners() {
    document.addEventListener("keydown", EscAndEnterCallBack);
}

function removeListeners() {
    document.removeEventListener("keydown", EscAndEnterCallBack);
}

let messageDiv = (message, from) => {
    return "<div class=\"message\"><span>" + from + ":</span><p>" + message + "</p></div>";
}

function setConnected() {
    let connectButton = document.getElementById("new");
    connectButton.name = "stop";
    connectButton.value = "stop";
    connectButton.setAttribute("onclick", "disconnect()");
    connectButton.id = "stop";
    status = "connected";

    document.getElementById("message").disabled = "false";
    document.getElementById("send").disabled = "false";
    addListeners();
}

function setDisconnected(from) {
    console.log("disconnecting");
    let disconnectButton = document.getElementById("stop");
    disconnectButton.name = "new";
    disconnectButton.value = "new";
    disconnectButton.setAttribute("onclick", "connect()");
    disconnectButton.id = "new";
    status = "disconnected";

    let disconnectingMessage = ((from === "You") ? "You have disconnected." : "Stranger has disconnected.");

    document.getElementById("messages").innerHTML += "<div>" + disconnectingMessage + "</div>";
    document.getElementById("message").disabled = "true";
    document.getElementById("send").disabled = "true";
    removeListeners();
}