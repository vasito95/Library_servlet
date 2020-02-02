<%--
  Created by IntelliJ IDEA.
  User: Vasyl Brusak
  Date: 2020-01-29
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/user/all-books">All books</a>
<a href="${pageContext.request.contextPath}/user/my-books">My books</a>
<a href="${pageContext.request.contextPath}/user/order-book">Order book</a>
<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Book Name</th>
        <th>In use by</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.inUseBy}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
