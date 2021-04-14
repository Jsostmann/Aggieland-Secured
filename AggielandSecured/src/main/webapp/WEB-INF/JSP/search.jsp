<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search Page</title>
</head>
<body>
<h1>Search Page</h1>

<form action="search" method="post">
    <label>Student:</label>
    <input type="text" name="studentName"> <input type="submit">


<label>Choose a car:</label>
<select name="filter">
    <option>Computer Science</option>
    <option>Math</option>
    <option>English</option>
</select>
</form>

    <c:if test = "${requestScope.get('filter') != null}">
        <h1>Results:</h1>

    </c:if>

</body>
</html>
