<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="ua"/>
<fmt:setBundle basename="messages" var="msg" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Library</title>
</head>
<body>
<h2><fmt:message key="input.password" bundle="${msg}"/></h2>


<a href="${pageContext.request.contextPath}/login">Login</a>
</body>
</html>
