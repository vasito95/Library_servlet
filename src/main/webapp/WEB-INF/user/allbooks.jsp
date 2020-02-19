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

        <article class="content dashboard-page center-block">
            <form method="get" action="${pageContext.request.contextPath}/user/all-books">
                <input class="form-control-sm" type="text" name="search">
                <select class="form-control-sm" name="field">
                    <option selected value="name">
                        <fmt:message key="system.book.name" bundle="${msg}"/>
                    </option>
                    <option value="author">
                        <fmt:message key="system.book.author" bundle="${msg}"/>
                    </option>
                    <option value="attribute">
                        <fmt:message key="system.book.attribute" bundle="${msg}"/>
                    </option>
                </select>
                <button class="button" type="submit">
                    <fmt:message key="system.button.filter" bundle="${msg}"/>
                </button>
            </form>
            <div>
                <fmt:message key="system.book.list" bundle="${msg}"/>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="system.book.name" bundle="${msg}"/></th>
                    <th scope="col"><fmt:message key="system.book.authors" bundle="${msg}"/></th>
                    <th scope="col"><fmt:message key="system.book.attribute" bundle="${msg}"/></th>
                    <th scope="col"><fmt:message key="system.book.isinuse" bundle="${msg}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.name}</td>
                        <td>${book.authors}</td>
                        <td>${book.attribute}</td>
                        <td>${book.isInUse}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </article>

    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/script.js"></script>
</body>
</html>