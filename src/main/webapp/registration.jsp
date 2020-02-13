<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en" scope="session"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>
<h1>${error}</h1>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <p>Username</p>
    <input type="text" name="username">
    <p>Email</p>
    <input type="text" name="email">
    <p>PgoneNumber</p>
    <input type="text" name="phoneNumber">
    <p>Password</p>
    <input type="password" name="password">
    <button type="submit" value="Register">Register</button>
</form>

<a href="${pageContext.request.contextPath}/login">Login</a>
</body>
</html>
