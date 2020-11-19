
var websocket = new WebSocket("ws://localhost:8080/aggieland/home");

websocket.onmessage = function(event) {
    onMessage(event);
};

websocket.onopen = function(event) {
    onOpen(event)
};

websocket.onerror = function(event) {
    onError(event)
};


function onOpen(event) {
    console.log("onOpen: Connected to Endpoint!");
    console.log("Event: " + event);
}

function onMessage(event) {
    console.log("OnMessage: Message Recieved From Server");
    console.log(event.data);
    document.getElementById("log").textContent += event.data + "\n";
}

function onError(event) {
    console.log("onError: Error");
}

function send() {
    console.log("send: Sent Message");
    websocket.send("sent");

}
