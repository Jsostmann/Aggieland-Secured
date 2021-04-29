<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <!--  This file has been downloaded from bootdey.com    @bootdey on twitter -->
    <!--  All snippets are MIT license http://bootdey.com/license -->
    <title>${sessionScope.user.firstName}'s page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <link href="/AggielandSecured/stylesheets/profile.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <div id="firstRow" class="row">
        <div class="col-lg-4">
            <div class="profile-card-4 z-depth-3">
                <div class="card">
                    <div class="card-body text-center bg-primary rounded-top">
                        <div class="user-box">
                            <!--<img src="/AggielandSecured/res/favicon.png" alt="user avatar">-->
                            <!--<img src="data:image/jpg;base64,${sessionScope.user.profilePictureBase64}" width="100" height="100">-->
                            <img src="${sessionScope.user.profilePictureBase64}" width="100" height="100">
                        </div>
                        <h5 class="mb-1 text-white">${sessionScope.user.getFullName()}</h5>
                        <h6 class="text-light">Student</h6>
                    </div>
                    <div class="card-body">
                        <ul class="list-group shadow-none">
                            <li class="list-group-item">
                                <div class="list-details">
                                    <span>${sessionScope.user.major}</span>
                                    <small>Major</small>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="list-details">
                                    <span>${sessionScope.user.email}</span>
                                    <small>Email Address</small>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="list-details">
                                    <span>${sessionScope.user.dateCreated}</span>
                                    <small>Date Joined</small>
                                </div>
                            </li>
                        </ul>

                        <div class="row text-center mt-4">
                            <!-- <div class="col p-2">
                                 <h4 class="mb-1 line-height-5">2.2k</h4>
                                 <small class="mb-0 font-weight-bold">Followers</small>
                             </div>
                             -->
                            <div class="col p-2">
                                <a style="width: 100%;  margin-top: 10px; background-color: #002d74" href="logout"
                                   class="btn btn-primary">Logout</a>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div style="" class="col-lg-8">
            <div style=" height: 100%; padding: 2px;" class="card z-depth-3">
                <div style="background-color: whitesmoke; margin-top: 0px;" class="card-body">
                    <ul class="nav nav-pills nav-pills-primary nav-justified">

                        <li class="nav-item">
                            <a href="javascript:" data-target="#profile" data-toggle="pill"
                               class="nav-link active show"><i class="icon-user"></i> <span
                                    class="hidden-xs">Profile</span></a>
                        </li>
                        <li class="nav-item">
                            <a href="javascript:" data-target="#edit" data-toggle="pill" class="nav-link"><i
                                    class="icon-note"></i> <span class="hidden-xs">Edit</span></a>
                        </li>
                        <li class="nav-item">
                            <a data-bs-toggle="offcanvas" type="button" data-bs-target="#offcanvasRight2"
                               aria-controls="offcanvasRight" class="btn nav-link">Friends</a>
                            <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight2"
                                 aria-labelledby="offcanvasRightLabel">
                                <div class="offcanvas-header">
                                    <h5 id="offcanvasRightLabel2">Friends</h5>
                                    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"
                                            aria-label="Close"></button>
                                </div>
                                <div class="offcanvas-body">
                                    <c:if test="${requestScope.friends != null && requestScope.friends.size() == 0}">
                                        <h1>No Friends yet :(</h1>
                                    </c:if>
                                    <div class="row row-cols-1 row-cols-md-2 g-4">
                                        <c:forEach items="${requestScope.friends}" var="friend">
                                            <div class="col">
                                                <div class="card border-success friend-card">
                                                    <div class="card-body">
                                                        <h5 class="card-title">${friend.userName}</h5>
                                                    </div>
                                                    <div class="card-footer">
                                                        <div class="btn-toolbar justify-content-between" role="toolbar"
                                                             aria-label="Toolbar with button groups">
                                                            <div class="btn-group" role="group"
                                                                 aria-label="First group">
                                                                <a href="/AggielandSecured/profile?from-list=${friend.userName}" class="nav-link">Remove Friend</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <li class="nav-item">
                            <a data-bs-toggle="offcanvas" type="button" data-bs-target="#offcanvasRight"
                               aria-controls="offcanvasRight" class="btn nav-link">Requests<span
                                    data-bs-toggle="offcanvas" type="button" data-bs-target="#offcanvasRight"
                                    aria-controls="offcanvasRight" class="badge bg-primary">3</span>
                            </a>
                            <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight"
                                 aria-labelledby="offcanvasRightLabel">
                                <div class="offcanvas-header">
                                    <h5 id="offcanvasRightLabel">Requests</h5>
                                    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"
                                            aria-label="Close"></button>
                                </div>
                                <div class="offcanvas-body">
                                    <div class="row row-cols-1 row-cols-md-2 g-4">
                                        <div class="col">
                                            <div class="card h-100">
                                                <img src="..." class="card-img-top" alt="...">
                                                <div class="card-body">
                                                    <h5 class="card-title">Card title</h5>
                                                    <p class="card-text">This is a longer card with supporting text
                                                        below as a natural lead-in to additional content. This content
                                                        is a little bit longer.</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="card h-100">
                                                <img src="..." class="card-img-top" alt="...">
                                                <div class="card-body">
                                                    <h5 class="card-title">Card title</h5>
                                                    <p class="card-text">This is a short card.</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="card h-100">
                                                <img src="..." class="card-img-top" alt="...">
                                                <div class="card-body">
                                                    <h5 class="card-title">Card title</h5>
                                                    <p class="card-text">This is a longer card with supporting text
                                                        below as a natural lead-in to additional content.</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="card h-100">
                                                <img src="..." class="card-img-top" alt="...">
                                                <div class="card-body">
                                                    <h5 class="card-title">Card title</h5>
                                                    <p class="card-text">This is a longer card with supporting text
                                                        below as a natural lead-in to additional content. This content
                                                        is a little bit longer.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <h1>Jalen Moore</h1>
                                    <h1>Donald Battle</h1>
                                </div>
                            </div>
                        </li>
                    </ul>

                    <div style="margin-top: 89px;" class="tab-content p-3">
                        <div class="tab-pane active show" id="profile">
                            <div style="" class="row">
                                <h5 style="text-align: center; margin-bottom: 3px; width: 100%; " class="mb-3">User
                                    Profile</h5>
                                <div style="text-align: left;" class="col-md-12">
                                    <h6>About Me</h6>
                                    <p>${sessionScope.user.userInfo}</p>
                                </div>
                            </div>


                            <div style="align-content: center; height: 150px;" class="row">
                                <div style="text-align: center;" class="col-md-6">
                                    <a style="width: 100%;  margin-top: 10px; line-height: 30px; border: 3px solid black;"
                                       href="/AggielandSecured/chatroom" class="btn btn-primary customButton">Chat
                                        Room</a>
                                </div>
                                <div style="text-align: center;" class="col-md-6">
                                    <a style="width: 100%;  margin-top: 10px; line-height: 30px; border: 3px solid black;"
                                       href="/AggielandSecured/search" class="btn btn-primary customButton">Search</a>
                                </div>
                            </div>

                            <!--/row-->
                        </div>
                        <div class="tab-pane" id="edit">
                            <form action="profile" method="post" enctype="multipart/form-data">
                                <div class="form-group row">
                                    <label class="col-lg-3 col-form-label form-control-label">First name</label>
                                    <div class="col-lg-9">
                                        <input class="form-control" type="text" name="firstName"
                                               placeholder="First Name">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-3 col-form-label form-control-label">Last name</label>
                                    <div class="col-lg-9">
                                        <input class="form-control" type="text" name="lastName" placeholder="Last Name">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-3 col-form-label form-control-label">Email</label>
                                    <div class="col-lg-9">
                                        <input class="form-control" type="email" name="email" placeholder="New Email">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-3 col-form-label form-control-label">Change profile</label>
                                    <div class="col-lg-9">
                                        <input class="form-control" type="file" name="profilePicture">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-3 col-form-label form-control-label">Password</label>
                                    <div class="col-lg-9">
                                        <input class="form-control" type="password" name="password"
                                               placeholder="New Password">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-3 col-form-label form-control-label">Confirm Password</label>
                                    <div class="col-lg-9">
                                        <input class="form-control" type="password" name="confirmPassword"
                                               placeholder="Confirm New Password">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-3 col-form-label form-control-label">Major</label>
                                    <div class="col-lg-9">
                                        <input class="form-control" type="text" name="major" placeholder="New Major">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-3 col-form-label form-control-label">User Info</label>
                                    <div class="col-lg-9">
                                        <input class="form-control" type="text" name="userInfo"
                                               placeholder="Enter some cool stuff about yourself..">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-3 col-form-label form-control-label"></label>
                                    <div class="col-lg-9">
                                        <input type="reset" class="btn btn-primary customButton" value="Cancel">
                                        <input type="submit" class="btn btn-primary customButton" value="Save Changes">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>