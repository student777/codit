<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/0630_favicon_beige.ico">
</head>
<body>
<script>
    alert('expired session');
    location.href = '/';
</script>
</body>
</html>
