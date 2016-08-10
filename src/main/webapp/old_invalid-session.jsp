<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    alert('너는 지금 인증을 받지 않고 @Auth에 접근한거다');
    alert('세션이 유효하지 않습니다');
    location.href = '/';
</script>
</body>
</html>
