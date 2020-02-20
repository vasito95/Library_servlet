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
            <form class="book-form" action="${pageContext.request.contextPath}/admin/add-book" method="post">
                <h5 class="form-control-label"><fmt:message key="system.book.name" bundle="${msg}"/></h5>
                <input class="form-control"
                       type="text"
                       name="name"
                       required
                       title="Username should only contain lowercase letters. e.g. john"
                >

                <h5 class="form-control-label"><fmt:message key="system.book.authors" bundle="${msg}"/></h5>
                <div class="author-box">
                    <input class="form-control m-b-1"
                           id="author"
                           type="text"
                           name="author"
                           required
                    >
                </div>
                <h2 class="author-input text-center">+</h2>
                <h5 class="form-control-label"><fmt:message key="system.book.attribute" bundle="${msg}"/></h5>
                <input class="form-control m-b-1"
                       type="text"
                       name="attribute"
                       required
                >
                <button class="btn btn-success-outline p-l-3 p-r-3" type="submit" value="Add">
                    <fmt:message key="system.button.add" bundle="${msg}"/>
                </button>
            </form>
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
                        <td>${book.id}</td>
                        <td>${book.name}</td>
                        <td class="max-width-250">${book.authors}</td>
                        <td class="max-width-250">${book.attribute}</td>
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
