<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="./stylesheets/test.css" rel="stylesheet" type="text/css">
    <title>Title</title>
</head>
<body>

<c:forEach items="${users}" var="user" >
    <p>${user.firstName}</p>
</c:forEach>


<h1>
    ${requestScope.searchedUser.firstName}
</h1>



</body>
</html>
