var websocket = null;


$(document).ready(function(){

    // initialize our websocket connection to server.
    initWebsocket();

    // enable toggling of action menu
    $('#action_menu_btn').click(function(){
        $('.action_menu').toggle();
    });


});

function initWebsocket() {

    websocket = new WebSocket("ws://localhost:8080/aggielandsecured/");


    websocket.onmessage = function(event) {
        onMessage(event);
    };

    websocket.onopen = function(event) {
        onOpen(event);
    };

    websocket.onerror = function(event) {
        onError(event);
    };


    function onOpen(event) {
        addUserToScreen();
        console.log("onOpen: Connected to Endpoint!");
        console.log("Event: " + event);
    }

    function onMessage(event) {
        addUserToScreen();
        console.log("OnMessage: Message Received From Server");
        console.log(event.data);
    }

    function onError(event) {
        console.log("onError: Error");
    }

    function send() {
        console.log("Send: Sent Message");
        websocket.send("sent");

    }

}

function addUserToScreen() {
    //var user = $("<div>");
      //  user.addClass("d-flex justify-content-start mb-4s");

    var user = $("<div>");
    user.css({
        "background-color": "yellow",
        "width": "50px",
        "height": "50px"
    });

    var users = $('.card-body.msg_card_body');
    users.append(user);

}