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
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/materialize-custom.css"
          media="screen,projection"/>
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
                        <h4 class="grey-text">Report Page</h4>
                        <h5 class="center-align">Good job, ${applicantVo.name }!</h5>
                        <br>
                        <img class="center-block" height="320" width="386"
                             src="${pageContext.request.contextPath }/assets/image/fighting-racoon.jpg"/>
                    </div>
                </div>

            <div class="row">
                <div class="col s10 offset-s1">
                <h4>Summary</h4>
                <table class="striped centered">
                    <thead>
                        <tr>
                            <th>Problem</th>
                            <th>No.of right answers / Total no. of test cases</th>
                        </tr>
                    </thead>
                    <c:forEach items="${resultListOfList}" var="resultList" varStatus="status">
                        <c:set var="count" value="0" scope="page"/>
                        <tr>
                            <c:forEach items="${resultList}" var="resultVo">
                                <c:if test="${resultVo.correctness eq true}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                </c:if>
                            </c:forEach>
                            <td>Task ${status.index + 1}</td>
                            <td>${count} / ${resultList.size() }</td>
                        </tr>
                    </c:forEach>
                </table>
                </div>
            </div>
                <br>
                <br>

                <div class="row">
                    <div class="col s10 offset-s1">
                        <h4>Details</h4>
                        <br>
                <c:forEach items="${resultListOfList}" var="resultList" varStatus="status">
                            <h5 class="grey-text">Problem ${status.index + 1}</h5>
                            <a href="/result/${status.index + 1}" class="waves-effect waves-light btn">show source tree</a>
                            <table class="bordered centered">
                                <thead>
                                    <tr>
                                        <th>test_case number</th>
                                        <th>memory used</th>
                                        <th>running time</th>
                                        <th>correctness</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${resultList}" var="resultVo" varStatus="status2">
                                    <tr>
                                        <td>${status2.index +1 }</td>
                                        <td>${resultVo.usedMemory }</td>
                                        <td>${resultVo.runningTime }ms</td>
                                        <td>${resultVo.correctness }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <br>
                </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
</body>
</html>
