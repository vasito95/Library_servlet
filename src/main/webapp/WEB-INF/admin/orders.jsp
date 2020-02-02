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
<jsp:include page="adminheader.jsp"></jsp:include>
<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Book Name</th>
        <th>Date to</th>
        <th>User</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.bookName}</td>
            <td>${order.dateTo}</td>
            <td>${order.userName}</td>
            <td>
                <form action="${pageContext.request.contextPath}/admin/orders/accept" method="post">
                    <input type="hidden" name="orderId" value="${order.id}">
                    <input type="submit" value="Accept">
                </form>

                <form action="${pageContext.request.contextPath}/admin/orders/decline" method="post">
                    <input type="hidden" name="orderId" value="${order.id}">
                    <input type="submit" value="Decline">
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
