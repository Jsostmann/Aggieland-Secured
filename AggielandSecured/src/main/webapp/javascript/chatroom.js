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

    var hostLocation = document.location.host;
    websocket = new WebSocket("ws://" + hostLocation + "/AggielandSecured/");


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
    var temp = $("<div class=\"d-flex justify-content-start mb-4\"><div class=\"img_cont_msg\"><img src=\"res/favicon.png\" class=\"rounded-circle user_img_msg\"> </div> <div class=\"msg_cotainer\">New User Joined<span class=\"msg_time\">8:40 AM, Today</span></div></div>")
    var users = $('.card-body.msg_card_body');
    users.append(temp);

}