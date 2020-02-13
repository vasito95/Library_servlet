<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="msg" scope="session"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="adminheader.jsp"></jsp:include>
<form action="${pageContext.request.contextPath}/admin/edit-book/">
    <input type="text" readonly name="id" value="${book.id}">
    <input type="text" name="name" value="${book.name}">
    <c:forEach items="${book.authors}" var="author">
        <input type="text" name="author" value="${author}">
    </c:forEach>
    <input type="text" name="attribute" value="${book.attribute}">
    <input type="submit" value="Отправить">
</form>
</table>
</body>
</html>
