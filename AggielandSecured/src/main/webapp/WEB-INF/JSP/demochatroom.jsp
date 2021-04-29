<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
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
</head>

<body>
<div class="container-fluid h-100">
    <div class="row justify-content-center h-100">
        <div class="col-md-4 col-xl-3 chat"><div class="card mb-sm-3 mb-md-0 contacts_card">
            <div class="card-header">
                <%--
                <div class="input-group">
                    <input type="text" placeholder="Search..." name="" class="form-control search">
                    <div class="input-group-prepend">
                        <span class="input-group-text search_btn"><i class="fas fa-search"></i></span>
                    </div>
                    <div class="input-group-prepend">
                        <span class="input-group-text add_btn"><i class="fas fa-plus-circle"></i></span>
                    </div>
                </div>
                --%>
            </div>
            <div class="card-body contacts_body">
                <ui class="contacts">
                    <li class="active">
                        <div class="d-flex bd-highlight">
                            <div class="img_cont">
                                <img src="res/favicon.png" class="rounded-circle user_img">
                                <span class="online_icon"></span>
                            </div>
                            <div class="user_info">
                                <span>Keith Battle</span>
                                <p>Keith is online</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="d-flex bd-highlight">
                            <div class="img_cont">
                                <img src="res/favicon.png" class="rounded-circle user_img">
                                <span class="online_icon"></span>
                            </div>
                            <div class="user_info">
                                <span>Jalen Moore</span>
                                <p>Jalen is online</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="d-flex bd-highlight">
                            <div class="img_cont">
                                <img src="res/favicon.png" class="rounded-circle user_img">
                                <span class="online_icon"></span>
                            </div>
                            <div class="user_info">
                                <span>James Ostmann</span>
                                <p>James is online</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="d-flex bd-highlight">
                            <div class="img_cont">
                                <img src="res/favicon.png" class="rounded-circle user_img">
                                <span class="online_icon offline"></span>
                            </div>
                            <div class="user_info">
                                <span>Dummy user</span>
                                <p>Dummy user left 50 mins ago</p>
                            </div>
                        </div>
                    </li>
                </ui>
            </div>
            <div class="card-footer"></div>
        </div></div>
        <div class="col-md-8 col-xl-6 chat">
            <div class="card">
                <div class="card-header msg_head">
                    <div class="d-flex bd-highlight">
                        <div class="img_cont">
                            <img src="res/favicon.png" class="rounded-circle user_img">
                            <span class="online_icon"></span>
                        </div>
                        <div class="user_info">
                            <span>DEMO CHAT 2</span>
                            <!--<span>Chat with Jalen</span>-->
                            <p>5 Messages</p>
                        </div>
                        <%--
                        <div class="video_cam">
                            <span><i class="fas fa-video"></i></span>
                            <span><i class="fas fa-phone"></i></span>
                        </div>
                        --%>
                    </div>
                    <%--
                    <span id="action_menu_btn"><i class="fas fa-ellipsis-v"></i></span>
                    <div class="action_menu">
                        <ul>
                            <li><i class="fas fa-user-circle"></i> View profile</li>
                            <li><i class="fas fa-users"></i> Add to close friends</li>
                            <li><i class="fas fa-plus"></i> Add to group</li>
                            <li><i class="fas fa-ban"></i> Block</li>
                        </ul>
                    </div>
                    --%>
                </div>
                <div id="users" class="card-body msg_card_body">
                    <%--
                    <div class="d-flex justify-content-start mb-4">
                        <div class="img_cont_msg">
                            <img src="res/favicon.png" class="rounded-circle user_img_msg">
                        </div>
                        <div class="msg_cotainer">
                            Hi, how are you keith
                            <span class="msg_time">8:40 AM, Today</span>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end mb-4">
                        <div class="msg_cotainer_send">
                            Hi, Jalen! i am good thanks how about you?
                            <span class="msg_time_send">8:55 AM, Today</span>
                        </div>
                        <div class="img_cont_msg">
                            <img src="res/favicon.png" class="rounded-circle user_img_msg">
                        </div>
                    </div>
                    <div class="d-flex justify-content-start mb-4">
                        <div class="img_cont_msg">
                            <img src="res/favicon.png" class="rounded-circle user_img_msg">
                        </div>
                        <div class="msg_cotainer">
                            I am good too, thank you for asking!
                            <span class="msg_time">9:00 AM, Today</span>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end mb-4">
                        <div class="msg_cotainer_send">
                            You are welcome
                            <span class="msg_time_send">9:05 AM, Today</span>
                        </div>
                        <div class="img_cont_msg">
                            <img src="res/favicon.png" class="rounded-circle user_img_msg">
                        </div>
                    </div>
                    <div class="d-flex justify-content-start mb-4">
                        <div class="img_cont_msg">
                            <img src="res/favicon.png" class="rounded-circle user_img_msg">
                        </div>
                        <div class="msg_cotainer">
                            Bye Friend!
                            <span class="msg_time">9:07 AM, Today</span>
                        </div>
                    </div>
                    --%>
                </div>

                <div class="card-footer">
                    <div class="input-group">
                        <div class="input-group-append">
                            <span class="input-group-text attach_btn"><i class="fas fa-paperclip"></i></span>
                        </div>
                        <textarea id="text" name="" class="form-control type_msg" placeholder="Type your message..."></textarea>
                        <div class="input-group-append">
                            <span class="input-group-text send_btn"><i class="fas fa-location-arrow"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/AggielandSecured/javascript/demochatroom.js"></script>
</body>
</html>

