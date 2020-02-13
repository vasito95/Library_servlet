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
<a href="?sessionLocale=en">En</a>
<a href="?sessionLocale=ua">Ua</a>
<jsp:include page="userheader.jsp"></jsp:include>

<form action="${pageContext.request.contextPath}/user/all-books" method="get">
    <input type="text" name="search">
    <select name="field">
        <option selected value="name">Name</option>
        <option value="author">Author</option>
        <option value="attribute">Attribute</option>
    </select>
    <button type="submit">Search</button>
</form>
<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Book Name</th>
        <th>Is in use</th>
        <th>Authors</th>
        <th>Attributes</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.isInUse}</td>
            <td>${book.authors}</td>
            <td>${book.attribute}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
