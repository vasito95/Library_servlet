<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="msg" scope="session"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Login</title>
</head>
<body>
<a href="?sessionLocale=en">En</a>
<a href="?sessionLocale=ua">Ua</a>
<h1>Please login</h1>
<form  method="post" action="${pageContext.request.contextPath}/login">

    <h2><fmt:message key="input.username" bundle="${msg}"/></h2>
    <input type="text" name="email">
    <h2><fmt:message key="input.password" bundle="${msg}"/></h2>
    <input type="password" name="password">
    <input class="button" type="submit" value="Войти">
</form>
<a href="${pageContext.request.contextPath}/registration">Registration</a>
</body>
</html>
