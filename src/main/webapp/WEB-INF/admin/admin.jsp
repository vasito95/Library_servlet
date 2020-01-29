<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin</title>
</head>
<body>
<h1>Hello from admin</h1>
<a href="${pageContext.request.contextPath}/admin/library">Library</a>
<a href="${pageContext.request.contextPath}/admin/orders">Accept Orders</a>
<a href="${pageContext.request.contextPath}/admin/add-book">Library</a>

<h1>${pageContext.request.contextPath}</h1>
<h1>${sessionScope.role}</h1>
</body>
</html>
