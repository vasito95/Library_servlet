<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="msg" scope="session"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Registration</title>
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
                <p class="text-xs-center text-muted">
                    <fmt:message key="login.label.register" bundle="${msg}"/>
                </p>
                <c:if test="${not empty requestScope.message}">
                        <span class="text-danger">
                    <fmt:message key="${requestScope.message}" bundle="${msg}"/>
                        </span>
                </c:if>
                <form action="${pageContext.request.contextPath}/registration" method="post">
                    <div>
                        <label for="username">
                            <fmt:message key="input.username" bundle="${msg}"/>
                        </label>
                        <input
                                class="text-muted form-control underlined"
                                type="text"
                                id="username"
                                name="username"
                        />
                        <span class="text-danger clearfix">

                        </span>
                    </div>
                    <div>
                        <label for="email">
                            <fmt:message key="input.email" bundle="${msg}"/>
                        </label>
                        <input
                                class="text-muted form-control underlined"
                                id="email"
                                type="text"
                                name="email"
                        />
                        <span class="text-danger clearfix">

                        </span>
                    </div>
                    <div>
                        <label for="phone">
                            <fmt:message key="input.phone.number" bundle="${msg}"/>
                        </label>
                        <input
                                class="text-muted form-control underlined"
                                type="text"
                                id="phone"
                                name="phoneNumber"
                        />
                        <span class="text-danger clearfix">

                        </span>
                    </div>
                    <div>
                        <label for="password">
                            <fmt:message key="input.password" bundle="${msg}"/>
                        </label>
                        <input
                                class="text-muted form-control underlined"
                                id="password"
                                type="password"
                                name="password"
                        />
                        <span class="text-danger clearfix">

                        </span>
                    </div>
                    <input type="hidden" name="_csrf"/>
                    <div class="form-group">
                        <button
                                type="submit"
                                class="btn btn-block btn-primary"
                        >
                            <fmt:message key="register.button.register" bundle="${msg}"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>