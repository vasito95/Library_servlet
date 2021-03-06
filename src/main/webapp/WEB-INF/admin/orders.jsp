<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="msg" scope="session"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Library</title>
    <link href="${pageContext.request.contextPath}/static/css/theme.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/vendor.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="main-wrapper">
    <div class="app">

        <jsp:include page="adminheader.jsp"></jsp:include>

        <article class="content dashboard-page">
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th scope="col"><fmt:message key="system.book.name" bundle="${msg}"/></th>
                    <th scope="col"><fmt:message key="input.username" bundle="${msg}"/></th>
                    <th scope="col"><fmt:message key="system.date" bundle="${msg}"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td scope="row">${order.id}</td>
                        <td>${order.bookName}</td>
                        <td>${order.userName}</td>
                        <td>${order.dateTo}</td>
                        <td class="justify-around">
                            <form action="${pageContext.request.contextPath}/admin/orders/accept" method="post">
                                <input type="hidden" value="${order.id}" name="orderId">
                                <button class="btn btn-success-outline" type="submit">
                                    <fmt:message key="system.button.accept" bundle="${msg}"/>
                                </button>
                            </form>
                            <form action="${pageContext.request.contextPath}/admin/orders/decline" method="post">
                                <input type="hidden" value="${order.id}" name="orderId">
                                <button class="btn btn-danger-outline " type="submit">
                                    <fmt:message key="system.button.decline" bundle="${msg}"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <h4>Page</h4>
            <c:forEach begin="1" end="${requestScope.numberOfPages}" var="pageNumber">
                <a class="btn btn-info-outline"
                   href="${pageContext.request.contextPath}/admin/orders?page=${pageNumber-1}&numberOfItems=${requestScope.numberOfOrders}">${pageNumber}</a>
            </c:forEach>
            <br>
            <c:if test="${requestScope.numberOfPages gt 0}">
                <h4>Number of items per page</h4>
                <a class="btn btn-warning-outline"
                   href="${pageContext.request.contextPath}/admin/orders?page=0&numberOfItems=3">3</a>
                <a class="btn btn-warning-outline"
                   href="${pageContext.request.contextPath}/admin/orders?page=0&numberOfItems=5">5</a>
                <a class="btn btn-warning-outline"
                   href="${pageContext.request.contextPath}/admin/orders?page=0&numberOfItems=8">8</a>
            </c:if>
        </article>
    </div>
</div>
</body>
</html>
