<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<body>
반갑습니다 ${applicant.name}님
<h3>이스트소프트가 귀하를 코딩인터뷰에 초대하였습니다</h3>
<p>인증을 위해 이메일을 입력하세요</p>
<form action="${pageContext.request.contextPath}/instruction" method="post">
    <label>이메일</label><input name="email"/>
    <input type="hidden" name="ticket" value="${param.ticket}"/>
    <button type="submit">제출</button>
</form>

</body>
</html>
