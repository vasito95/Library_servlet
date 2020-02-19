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
        <article class="content dashboard-page center-block">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="system.book.name" bundle="${msg}"/></th>
                    <th scope="col"><fmt:message key="system.book.authors" bundle="${msg}"/></th>
                    <th scope="col"><fmt:message key="system.book.attribute" bundle="${msg}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <th>${book.id}</th>
                        <td>${book.name}</td>
                        <td>${book.authors}</td>
                        <td>${book.attribute}</td>
                        <td class="justify-around">
                            <form action="${pageContext.request.contextPath}/admin/edit-book" method="post">
                                <input type="hidden" name="bookId" value="${book.id}">
                                <button class="btn btn-success-outline" type="submit">
                                    <fmt:message key="system.button.edit" bundle="${msg}"/>
                                </button>
                            </form>
                            <form action="${pageContext.request.contextPath}/admin/edit-book/delete" method="post">
                                <input type="hidden" name="bookId" value="${book.id}">
                                <button class="btn btn-danger-outline" type="submit">
                                    <fmt:message key="system.button.delete" bundle="${msg}"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </article>
    </div>
</div>
</body>
</html>
