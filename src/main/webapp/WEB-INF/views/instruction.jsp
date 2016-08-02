<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>instructino main page</title>
</head>
<body>
<h2>시험 안내</h2>
<p>
    1. 시험은 총 ?a문제로 이루어져 있으며. ....<br>
    2. 시험은 중간에 멈출수 없<br>
    3. 답안은 입력 데이터가 적을 떄<br>
    4. 시험이 끝난 ㄴ후 피드백을 불ㄹ<br>
    5. 블라블라<br>
    6. 지랄잼<br>
    7. 씨잼<br>
</p>
<a href="${pageContext.request.contextPath}/practice"><button>튜토리얼 및 연습테스트 시작</button></a>
<a href="${pageContext.request.contextPath}/test"><button>바로 코딩테스트 시작하기</button></a>
</body>
</html>
