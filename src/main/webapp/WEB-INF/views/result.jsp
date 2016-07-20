<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result Page</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
    ${applicantVo.name } 님의 성적<br>
    <img height="320" width="386" src="${pageContext.request.contextPath }/assets/image/힘내라너구리.jpg" />
    <br>고생했다<br>


    <table border="1px">
        <thead>요약</thead>
        <tr>
            <td>문제번호</td>
            <td>맞은/총 문제</td>
        </tr>
        <c:forEach items="${resultListOfList}" var="resultList" varStatus="status">
            <c:set var="count" value="0" scope="page" />
            <tr>
                <c:forEach items="${resultList}" var="resultVo">
                    <c:if test="${resultVo.correctness eq true}">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:if>
                </c:forEach>
                <td>문제 ${status.index + 1}</td>
                <td>${count}/${resultList.size() }</td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <br>
    <br>
    <br>

    <c:forEach items="${resultListOfList}" var="resultList" varStatus="status">
        <table  border="1px">
            <thead>문제 ${status.index + 1}</thead>
            <tr>
                <td>test_case 번호</td>
                <td>메모리사용량</td>
                <td>running time</td>
                <td>정답여부</td>
            </tr>
            <c:forEach items="${resultList}" var="resultVo" varStatus="status2">
                <tr>
                    <td>${status2.index +1 }</td>
                    <td>${resultVo.usedMemory }</td>
                    <td>${resultVo.runningTime }ms</td>
                    <td>${resultVo.correctness }</td>
                </tr>
            </c:forEach>
        </table><br>
    </c:forEach>
</body>
</html>
