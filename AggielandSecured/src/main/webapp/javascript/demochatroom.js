var websocket = null;


$(document).ready(function(){


    // initialize our websocket connection to server.
    initWebsocket();


    // enable toggling of action menu
    $('#action_menu_btn').click(function(){
        $('.action_menu').toggle();
    });

    $('.input-group-append').click(function(){
        var input = $('#text').val();
        if (input != "") {
            var message = { messageType: 'MESSAGE', message: input };
            websocket.send(JSON.stringify(message));
            document.getElementById("text").value = "";
        } else {
            alert("Nothing to send :(");
        }

    });


});

function initWebsocket() {

    var hostLocation = document.location.host;
    websocket = new WebSocket("ws://" + hostLocation + "/AggielandSecured/demo");


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
        var username = '';

        while (username == '') {
            username = prompt("Please enter your name");
        }

        var message = { messageType: 'ENTER', message: username};
        websocket.send(JSON.stringify(message));
        //addNewUser(username);

    }

    function onMessage(event) {
        console.log(event);
        addMessage(event.data);
    }

    function onError(event) {
        console.log("onError: Error");
    }

}

function formatAMPM(date) {
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var ampm = hours >= 12 ? 'pm' : 'am';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    minutes = minutes < 10 ? '0'+minutes : minutes;
    var strTime = hours + ':' + minutes + ' ' + ampm;
    return strTime;
}


function addNewUser(name) {
    var contacts = $('.contacts');

    var outer = $('<li><div class="d-flex bd-highlight"><div class="img_cont"><img src="res/favicon.png" class="rounded-circle user_img"> <span class="online_icon"> </span> </div> <div class="user_info"><span>' +
        name + '</span> <p></p> </div></div></li>');

    contacts.append(outer);
}

function addMessage(message) {
    var users = $('#users');
    var userLength = users.children().length;
    var today = formatAMPM(new Date());

    var temp = $('<div class="d-flex justify-content-start mb-4"><div class="img_cont_msg"><img src="res/favicon.png" class="rounded-circle user_img_msg"> </div> <div class="msg_cotainer">' +
        message + '<span class="msg_time">' + today + '</span></div></div>');

    if(message.includes('-')) {
        temp = $('<div class="d-flex justify-content-end mb-4"><div class="img_cont_msg"><img src="res/favicon.png" class="rounded-circle user_img_msg"> </div> <div class="msg_cotainer_send">' +
            message + '<span class="msg_time">' + today + '</span></div></div>');
        addNewUser(message.substring(0, message.indexOf('-')));
    }
    if(message.includes('+')){
        return;
    }
    /*if (userLength % 2 != 0) {
        temp = $('<div class="d-flex justify-content-end mb-4"><div class="img_cont_msg"><img src="res/favicon.png" class="rounded-circle user_img_msg"> </div> <div class="msg_cotainer">' +
            message + '<span class="msg_time">8:40 AM, Today</span></div></div>');
    }
*/
    users.append(temp);


}