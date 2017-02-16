<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/0630_favicon_beige.ico">
    <title>result</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom.css" media="screen,projection"/>
</head>

<body class="orange lighten-5 flexbody">
<jsp:include page="/WEB-INF/views/header_light.jsp"></jsp:include>
<div class="section no-pad-bot" id="index-banner">
    <br>
    <br>
    <div class="row ">
        <div class="col s6 offset-s3">
            <div class="card-panel white">
                <div class="row">
                    <div class="col s10 offset-s1">
                        <h3>Result</h3>
                        <h5 class="grey-text">Problem ${problemInfo.id}-${problemInfo.name}</h5>
                        <br>
                        <img class="center-block" height="320" width="386" src="${pageContext.request.contextPath }/assets/image/fighting-racoon.jpg"/>
                        <div class="center-align">Good job, ${authApplicant.name }!</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col s10 offset-s1">
                        <h5 class="grey-text">Mark result</h5>
                        <c:choose>
                            <c:when test="${empty sourceCode}">
                                <div>no records. go and work</div>
                            </c:when>
                            <c:otherwise>
                            <h6 class="grey-text">at ${sourceCode.createdAt }</h6>
                            <table class="bordered centered">
                                <thead>
                                <tr>
                                    <th>test_case</th>
                                    <th>memory used</th>
                                    <th>running time</th>
                                    <th>correctness</th>
                                </tr>
                                </thead>
                                    <c:forEach items="${resultList}" var="result" varStatus="status">
                                        <tbody>
                                        <tr>
                                            <td>${status.index +1 }</td>
                                            <td>${result.usedMemory }</td>
                                            <td>${result.runningTime }ms</td>
                                            <td>${result.correctness }</td>
                                        </tr>
                                        </tbody>
                                    </c:forEach>
                            </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="row">
                    <div class="col s10 offset-s1">
                        <h5 class="grey-text">Top records</h5>
                        <table class="bordered centered">
                            <thead>
                            <tr>
                                <th>test_case</th>
                                <th>memory used</th>
                                <th>running time</th>
                                <th>language</th>
                                <th>user</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
</body>
</html>
