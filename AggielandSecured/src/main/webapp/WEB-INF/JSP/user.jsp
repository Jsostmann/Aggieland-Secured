<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Profile Page</title>
</head>
<body>
<c:if test = "${sessionScope.user.userName.equals(requestScope.get('results'))}">
    <span>${requestScope.get('results').size()} </span>results
</c:if>

</body>
</html>
