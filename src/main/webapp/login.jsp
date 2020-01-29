<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>

<h1>Please login</h1>
<form  method="post" action="${pageContext.request.contextPath}/login">
    <h3></h3>
    <input type="text" name="email">
    <input type="password" name="password">
    <input class="button" type="submit" value="Войти">
</form>
<a href="${pageContext.request.contextPath}/registration">Registration</a>
</body>
</html>
