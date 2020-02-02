<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="msg" scope="session"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Library</title>
</head>
<body>
<jsp:include page="adminheader.jsp"></jsp:include>

<form action="${pageContext.request.contextPath}/admin/add-book" method="post">
    <input class="form-control" type="text" name="name" placeholder="Input book name" required>
    <h3>Authors</h3>
    <input class="form-control" type="text" name="author" placeholder="Input author">
    <input class="form-control" type="text" name="author" placeholder="Input author">
    <input class="form-control" type="text" name="author" placeholder="Input author">
    <h3>Attributes</h3>
    <input class="form-control" type="text" name="attribute" placeholder="Input attribute">
    <input type="submit" value="Add"/>
</form>
</body>
</html>
