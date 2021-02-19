<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${sessionScope.user.firstName}'s Page</title>
</head>
<body>
<h1 style="text-align: center">Welcome Back! ${sessionScope.user.firstName}</h1>

</body>
</html>
