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

    <h2>${requestScope.message}</h2>


<form action="${pageContext.request.contextPath}/user/order-book" method="POST">
    <input class="form-control" type="text" name="name" placeholder="Input Name"/>
    <label for="dateTo">Date from today to:</label>
    <input id="dateTo" type="date"  name="dateTo">
    <input type="submit" class="btn btn-success" value="Place Order">
</form>
</body>
</html>
