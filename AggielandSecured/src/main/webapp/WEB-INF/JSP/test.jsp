<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name='viewport' content='minimum-scale=1.0, initial-scale=1.0,
	width=device-width, maximum-scale=1.0, user-scalable=no'/>
    <title>DEMO CHATROOM</title>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="icon" href="res/favicon.png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
    <link rel="stylesheet" type="text/css" href="/AggielandSecured/stylesheets/chatroom.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
    <script src="/AggielandSecured/javascript/test.js"></script>
    <script type="text/javascript">

        var hostLocation = document.location.host;
        var wsUri = "ws://" + hostLocation + "/AggielandSecured/demo";
        var proxy = CreateProxy(wsUri);

        document.addEventListener("DOMContentLoaded", function(event) {
            console.log(document.getElementById('loginPanel'));
            proxy.initiate({
                loginPanel: document.getElementById('loginPanel'),
                msgPanel: document.getElementById('msgPanel'),
                txtMsg: document.getElementById('txtMsg'),
                txtLogin: document.getElementById('txtLogin'),
                msgContainer: document.getElementById('msgContainer')
            });
        });

    </script>
</head>
<body>
<div id="container">
    <div id="loginPanel">
        <div id="infoLabel">Type a name to join the room</div>
        <div style="padding: 10px;">
            <input id="txtLogin" type="text" class="loginInput"
                   onkeyup="proxy.login_keyup(event)" />
            <button type="button" class="loginInput" onclick="proxy.login()">Login</button>
        </div>
    </div>
    <div id="msgPanel" style="display: none">
        <div id="msgContainer" style="overflow: auto;"></div>
        <div id="msgController">
			<textarea id="txtMsg"
                      title="Enter to send message"
                      onkeyup="proxy.sendMessage_keyup(event)"
                      style="height: 20px; width: 100%"></textarea>
            <button style="height: 30px; width: 100px" type="button"
                    onclick="proxy.logout()">Logout</button>
        </div>
    </div>
</div>
</body>
</html>