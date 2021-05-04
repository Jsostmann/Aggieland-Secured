<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>ChatRooms</title>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="icon" href="res/favicon.png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
    <link rel="stylesheet" type="text/css" href="/AggielandSecured/stylesheets/chatroom.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
</head>

<body>
<div class="container-fluid h-100">
    <div class="row justify-content-center h-100">
        <div class="col-md-12 col-xl-8 chat">
            <div class="card mb-sm-3 mb-md-0 contacts_card">
                <div class="card-header chatrooms-header">
                    <h1>Chatrooms</h1>
                </div>
                <div class="card-body chatrooms-body">
                    <ui class="chatrooms">
                        <li class="active">
                            <div class="col-12">
                                <div class="chatroom-info">
                                    <h4>Keith Battle</h4>
                                    <button>Join</button>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="d-flex bd-highlight">
                                <div class="chatroom-info">
                                    <span>Jalen Moore</span>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="d-flex bd-highlight">
                                <div class="chatroom-info">
                                    <span>James Ostmann</span>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="d-flex bd-highlight">
                                <div class="chatroom-info">
                                    <span>Dummy user</span>
                                </div>
                            </div>
                        </li>
                    </ui>
                </div>
                <div class="card-footer"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/AggielandSecured/javascript/demochatroom.js"></script>
</body>
</html>

