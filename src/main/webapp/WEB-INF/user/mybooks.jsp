<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="msg" scope="session"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="userheader.jsp"></jsp:include>
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
            <td>
                <form action="${pageContext.request.contextPath}/user/my-books" method="post">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <input type="submit" value="Return">
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
