<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="author" content="colorlib.com">
    <title>User Search</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,600,700" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/themes/dark.css">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="/AggielandSecured/stylesheets/search.css" rel="stylesheet"/>
</head>
<body>
<div class="s010">
    <form action="search" method="post">
        <div class="inner-form">
            <div class="basic-search">
                <div class="input-field">
                    <input id="search" type="text" placeholder="Student's First Name" name="search"/>
                    <div class="icon-wrap">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path
                                    d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z">
                            </path>
                        </svg>
                    </div>
                </div>
            </div>
            <div class="advance-search">
                <span class="desc">ADVANCED SEARCH</span>
                <div class="row">
                    <div class="input-field">
                        <div class="input-select">
                            <select class="regular" data-trigger="" name="major">
                                <option placeholder="" value="">Major</option>
                                <option>Computer Science</option>
                                <option>Computer Engineering</option>
                                <option>Electrical Engineering</option>
                                <option>Chemical Engineering</option>
                                <option>Industrial Engineering</option>
                                <option>Aeronautical Engineering</option>
                                <option>Chemistry</option>
                                <option>Mathematics</option>
                                <option>Business</option>
                                <option>English</option>
                                <option>African American History</option>
                            </select>
                        </div>
                    </div>
                    <%--
                    <div class="input-field">
                        <div class="input-select">
                            <select class="dont-sort" data-trigger="" name="classification">
                                <option placeholder="" value="">Classification</option>
                                <option>Freshman</option>
                                <option>Sophmore</option>
                                <option>Junior</option>
                                <option>Senior</option>
                            </select>
                        </div>
                    </div>
                    --%>
                    <div class="checkbox">
                        <span id="t">User Name</span>
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-danger">
                                <input type="checkbox" data-trigger="" name="username">
                                <span class="glyphicon glyphicon-ok"></span>
                            </label>
                        </div>
                    </div>

                </div>
                <div class="row third">
                    <div class="input-field">
                        <div class="result-count">
                            <c:if test = "${requestScope.get('results') != null}">
                                <span>${requestScope.get('results').size()} </span>results
                            </c:if>
                        </div>
                        <div class="group-btn">
                            <button type="reset" class="btn-delete" id="delete">RESET</button>
                            <button type="submit" class="btn-search">SEARCH</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>


    <!-- DYNAMIC RESULTS -->
    <div id="results" class="row">

        <c:forEach items="${requestScope.get('results')}" var="user" >

        <div class="col-xs-12 col-sm-6 col-md-6">
            <div style="background-color: #002d74; border: none;" class="well well-sm">
                <div class="row userBox">
                    <div class="col-12">
                      <h4><a href="/AggielandSecured/UserProfile/${user.userName}">${user.firstName} ${user.lastName}</a></h4>
                        <p>
                            <i class="glyphicon glyphicon glyphicon-user"></i> ${user.userName}
                            <br/>
                            <i class="glyphicon glyphicon-envelope"></i> ${user.email}
                            <br/>
                            <i class="fa fa-graduation-cap"></i> ${user.major}
                            <br/>
                            <%--<i class="fa fa-calculator"></i> ${user.classification}--%>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        </c:forEach>
    </div>

    <!-- END DYNAMIC RESULTS -->

</div>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/choices.js/3.0.4/choices.js"></script>
<script src="/AggielandSecured/javascript/search.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
