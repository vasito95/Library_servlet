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
        <jsp:include page="userheader.jsp"></jsp:include>
        <article class="content dashboard-page">
            <div class="col text-center text-danger">
                <c:if test="${not empty requestScope.message}">
                        <span class="text-danger">
                                ${requestScope.message}
                        </span>
                </c:if>
            </div>

            <div class="center-block">
                <form action="${pageContext.request.contextPath}/user/order-book" method="POST">
                    <input class="form-control-sm" required type="text" name="name"/>
                    <label for="dateTo">
                        <fmt:message key="system.date.from" bundle="${msg}"/>
                    </label>
                    <input id="dateTo" required type="date" min="${requestScope.minDate}" max="${requestScope.maxDate}"
                           name="dateTo">

                    <button type="submit" class="btn btn-success">
                        <fmt:message key="system.place.order" bundle="${msg}"/>
                    </button>
                </form>
            </div>
        </article>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/script.js"></script>
</body>
</html>
