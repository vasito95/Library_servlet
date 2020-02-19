<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header class="header">
    <div class="header-block header-block-nav">
        <ul class="nav-profile">
            <li class="profile">
                <a class="border" href="?sessionLocale=ua"><fmt:message key="lang.ua" bundle="${msg}"/></a>
            </li>
            <li class="profile">
                <a class="border" href="?sessionLocale=en"><fmt:message key="lang.eng" bundle="${msg}"/></a>
            </li>
            <li class="profile">
                <a class="border" href="${pageContext.request.contextPath}/admin"><fmt:message key="system.menu.admin" bundle="${msg}"/></a>
            </li>
            <li class="profile">
                <a class="border" href="${pageContext.request.contextPath}/admin/logout"><fmt:message key="system.button.logout" bundle="${msg}"/></a>
            </li>
        </ul>
    </div>
</header>

<aside class="sidebar">
    <div class="sidebar-container">
        <div class="sidebar-header">
            <div class="brand">
                <div class="logo">
                    <span class="l l1"></span>
                    <span class="l l2"></span>
                    <span class="l l3"></span>
                    <span class="l l4"></span>
                    <span class="l l5"></span>
                </div>
                Library
            </div>
        </div>
        <nav class="menu">
            <ul class="nav metismenu">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/add-book">
                        <fmt:message key="system.menu.add.book" bundle="${msg}"/>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/orders">
                        <fmt:message key="system.menu.confirm" bundle="${msg}"/>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/library">
                        <fmt:message key="system.menu.library" bundle="${msg}"/>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/edit-books">
                        <fmt:message key="system.menu.edit" bundle="${msg}"/>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/user">
                        <fmt:message key="system.menu.back.user" bundle="${msg}"/>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</aside>