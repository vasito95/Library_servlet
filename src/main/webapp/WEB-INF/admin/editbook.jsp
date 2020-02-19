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
            <form class="book-form" action="${pageContext.request.contextPath}/admin/edit-book/accept" method="post">
                <input class="form-control m-b-1" type="text" readonly name="bookId" value="${book.id}">
                <h5 class="form-control-label"><fmt:message key="system.book.name" bundle="${msg}"/></h5>
                <input class="form-control m-b-1" type="text" name="name" value="${book.name}">

                <h5 class="form-control-label"><fmt:message key="system.book.authors" bundle="${msg}"/></h5>
                <div class="author-box">
                    <c:forEach items="${book.authors}" var="author">
                        <input class="form-control m-b-1" type="text" name="author" value="${author}">
                    </c:forEach>
                </div>
                <h2 class="author-input text-center">+</h2>
                <h5 class="form-control-label"><fmt:message key="system.book.attribute" bundle="${msg}"/></h5>
                <input class="form-control m-b-1" type="text" name="attribute" value="${book.attribute}">
                <button class="btn btn-success-outline p-l-3 p-r-3" type="submit" value="Add">
                    <fmt:message key="system.button.accept" bundle="${msg}"/>
                </button>
            </form>
            </table>
        </article>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/script.js"></script>
</body>
</html>
