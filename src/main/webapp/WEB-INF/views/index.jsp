<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/0630_favicon_beige.ico">
    <title>index</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom.css"
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
                <h4 class="header center black-text bold">Welcome to the test!</h4>
                <br>
                <div class="row">
                    <div class="col s8 offset-s2">
                        <p>Hello boy,
                            <br>Welcome to coding test.
                            <br>To login, please write your secret key
                            <br>If you don't know this, please email: darkdakku@gmail.com</p>
                        <form action="${pageContext.request.contextPath}/instruction" method="post">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input name="secret_key" id="secret_key" type="password" class="validate">
                                    <label for="secret_key">secret key</label>
                                </div>
                            </div>
                            <div class="center">
                                <button type="submit" class="btn brown white-text">Submit</button>
                            </div>
                        </form>
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

