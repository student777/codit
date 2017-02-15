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
    <title>test page</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize.min.css"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom-ide.css" media="screen,projection"/>
</head>
<body>
<div id="wrapper" class="row">
    <div id="header" class="center grey lighten-2">
        <a href="/"><img id="logo" src="${pageContext.request.contextPath}/assets/image/0629_LOGO_FINAL_brown.gif"></a>
    </div>
    <div id="navbar" class="col s4 grey darken-2">
        <div class="row">
            <div id="task1" class="col s12 grey darken-2 white-text">
                <h3>${problemInfoVo.name}</h3>
                <c:set var="newline" value="<%= \"\n\" %>"/>
                <p>${fn:replace(problemInfoVo.description, newline, '<br>')}</p>
            </div>
        </div>
    </div>

    <div id="right" class="col s8">
        <div class="row">
            <div id="workboard" class="col s12 grey darken-3">
                <div class="row">
                    <div class="col s12 grey darken-3">
                        <div id="top-bar" class="row">
                            <div id="div-timeleft" class="col s3 no-padding btn grey darken-3 z-depth-0 inline timer" data-minutes-left="${totalTime}">
                                <span>Time Left: </span>
                            </div>
                            <div class="col s3 no-padding">
                                <div class="btn grey darken-3 z-depth-0 inline right">SELECT LANGUAGE:</div>
                            </div>
                            <div class="input-field col s2 no-padding">
                                <select id="select-language" name="language" onchange="select_editor(this.value);">
                                    <option value="1">C</option>
                                    <option value="2">JAVA</option>
                                    <option value="3">PYTHON</option>
                                </select>
                            </div>
                            <div class="col s2 no-padding right">
                                <button id="btn-help" class="btn grey darken-1" onclick="help()">Help</button>
                            </div>
                        </div>
                    </div>

                    <div id="editor" class="col s12 grey darken-4"></div>

                    <div class="col s12 grey darken-3">
                        <div class="row">
                            <div id="select-testcase" class="input-field col s2 no-padding left">
                                <form>
                                    <select name="test_cases">
                                        <c:forEach items="${testcaseList}" var="testcase">
                                            <option value="${testcase.id}">${testcase.input}</option>
                                        </c:forEach>
                                    </select>
                                </form>
                            </div>
                            <div class="col s3 no-padding center">
                                <button id="run-code" onclick="run_code()" class="btn grey darken-1">SAVE & RUN(ctrl+R)</button>
                            </div>
                            <div>
                                <button id="final-submit" class="btn right grey darken-1" onclick="final_submit(problem_id)">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col s12 grey darken-3">
                <div class="row">
                    <div id="terminal" class="col s12 grey darken-4">
                        <div>
                            <h6 class="white-text">[Task output]</h6>
                            <div class="white-text">output will be appended here</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- dialog hidden -->
<div id="dialog-confirm" title="tutorial message">
    <p></p>
</div>

<!-- dialog hidden -->
<div id="dialog-alert" title="message from server">
    <p></p>
</div>

<!-- Java script -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- jquery ui-->
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<!--Import materialize.js(css)-->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
<!-- import external js files -->
<script src="${pageContext.request.contextPath }/assets/ace/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath }/assets/js/jquery.simple.timer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="/assets/js/jquery.spotlight.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="/assets/js/jquery.spotlight.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath }/assets/js/ide.js" type="text/javascript" charset="utf-8"></script>

<!-- Global variables -->
<script>
    var problem_id; // id of facing ProblemVo

    // manage problem list by following json
    // TODO: load by ajax, move this code to js file
    var problem_json_list = [];
    <c:forEach items="${problemList}" var="problemVo">
    var skeleton_code = '${fn:replace(problemVo.skeletonCode, newline, '\\n')}';
    problem_json_list.push({
        "problem_id":${problemVo.id},
        "skeleton_code": skeleton_code,
        "language_id":${problemVo.languageId},
    });
    </c:forEach>
</script>
</body>
</html>