<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>index.jsp</h2>


<h3>이스트소프트가 귀하를 코딩인터뷰에 초대하였습니다</h3>
<p>인터뷰에 필요한 정보를 입렿가세요</p>
<form>
    <label>성</label><input/>
    <label>이름</label><input/>
    <label>학교</label><input/>
    <label>학과</label><input/>
    <button><a href="/instruction">제출</a></button>
</form>
</body>
</html>
