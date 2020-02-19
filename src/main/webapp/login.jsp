<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="msg" scope="session"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/static/css/theme.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/vendor.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="auth">
    <div class="auth-container">
        <div class="card">
            <header class="auth-header">
                <h1 class="auth-title">
                    <div class="logo">
                        <span class="l l1"></span>
                        <span class="l l2"></span>
                        <span class="l l3"></span>
                        <span class="l l4"></span>
                        <span class="l l5"></span>
                    </div>
                    Library
                </h1>
                <div class="col-sm text-center">
                    <a href="?sessionLocale=en"><fmt:message key="lang.eng" bundle="${msg}"/></a>
                    <a href="?sessionLocale=ua"><fmt:message key="lang.ua" bundle="${msg}"/></a>
                </div>
            </header>
            <div class="auth-content">
                <p class="text-xs-center text-muted"><fmt:message key="login.label.signin" bundle="${msg}"/></p>
                <div class="container">
                    <div class="row text-center">
                        <c:if test="${not empty requestScope.message}">
                        <span class="text-danger">
                                ${requestScope.message}
                        </span>
                        </c:if>
                    </div>
                </div>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <label for="email"><fmt:message key="input.email" bundle="${msg}"/></label>
                    <input type="text"
                           class="text-muted form-control underlined"
                           id="email"
                           name="email"
                           value="admin1@gmail.com"
                    >
                    <label for="password"><fmt:message key="input.password" bundle="${msg}"/></label>
                    <input
                            type="password"
                            class="text-muted form-control underlined"
                            id="password"
                            name="password"
                            value="8520"
                    >
                    <div class="form-group">
                        <button
                                type="submit"
                                class="btn btn-block btn-primary"
                        >
                            <fmt:message key="login.button.signin" bundle="${msg}"/>
                        </button>
                    </div>
                </form>

                <div class="form-group text-center">
                    <p class="text-muted text-xs-center">
                        <fmt:message key="login.label.noaccount" bundle="${msg}"/>
                    </p>
                    <a href="${pageContext.request.contextPath}/registration">
                        <fmt:message key="login.label.register" bundle="${msg}"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
