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
    <title>practice main page</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom-ide.css" media="screen,projection"/>
</head>

<body>
    <div id="wrapper" class="row">
        <div id="header" class="center grey lighten-2">
            <img id="logo" src="${pageContext.request.contextPath}/assets/image/0629_LOGO_FINAL_brown.gif">
        </div>
        <div id="navbar" class="col s4 grey darken-2">
            <div class="row">
                <div id= "tabs" class="col s12 grey darken-2">
                    <ul class="tabs">
                        <li class="tab col s3 grey darken-2 white-text"><a class="active" href="#task1" onclick="select(1)">Task1</a></li>
                        <c:forEach var="no" begin="2" end="${problemInfoList.size()}" step="1">
                            <li class="tab col s3 grey darken-2 white-text"><a href="#task${no}" onclick="select(${no})">Task${no}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <c:forEach items="${problemInfoList}" var="problemInfoVo" varStatus="status">
                    <div id="task${status.count}" class="col s12 grey darken-2 white-text">
                        <h3>${problemInfoVo.name}</h3>
                        <p>${problemInfoVo.description}</p>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div id="right" class="col s8">
            <div class="row">
                <div id="workboard" class="col s12 grey darken-3">
                    <div class="row">
                        <div class="col s12 grey darken-3">
                            <div id="top-bar" class="row">
                                <div id="div-timeleft" class="col s2 no-padding">
                                    <div class="btn grey darken-3 z-depth-0 inline timer" data-seconds-left="${totalTime}">Time Left: </div>
                                </div>
                                <div class="col s2 no-padding">
                                    <div class="btn grey darken-3 z-depth-0 inline right">SELECT LANGUAGE: </div>
                                </div>
                                <div id="div-language" class="input-field col s5 no-padding">
                                    <select name="language" onchange="select_editor(current_k, this.value);">
                                        <option value="1" selected>C</option>
                                        <option value="2">JAVA</option>
                                        <option value="3">PYTHON</option>
                                    </select>
                                </div>

                                    <button class="btn grey darken-1 right" onclick="goTest()">Go Test</button>
                        </div>
                        </div>

                        <div id = "editor" class="col s12 grey darken-4"></div>

                        <div class="col s12 grey darken-3">
                            <div class="row">
                                <div class="col s1 no-padding">
                                    <button class="btn grey darken-1" onclick="help()">Help</button>
                                </div>
                                <div class="col s2 no-padding selectable">
                                    <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                                        <button onclick="save_code(${status.index})" class="btn grey darken-1">SAVE(ctrl+S)</button>
                                    </c:forEach>
                                </div>
                                <div id="div-testcase" class="input-field inline col s2 no-padding">
                                    <form class="selectable">
                                        <c:forEach items="${testcaseListOfList}" var="testcaseList">
                                            <select name="test_cases">
                                                <option value="0" selected disabled>select test case</option>
                                                <c:forEach items="${testcaseList}" var="testcase">
                                                    <option value="${testcase.id}">${testcase.input}</option>
                                                </c:forEach>
                                            </select>
                                        </c:forEach>
                                    </form>
                                </div>
                                <div class="col s2 no-padding selectable">
                                    <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                                        <button onclick="run_code(${status.index})" class="btn grey darken-1">RUN(ctrl+R)</button>
                                    </c:forEach>
                                </div>
                            <button class="btn right grey darken-1" onclick="final_submit()">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col s12 grey darken-3">
                    <div class="row">
                        <div id="terminal" class="col s12 grey darken-4 selectable">
                            <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                                <div>
                                    <h6 class="white-text">[Task ${status.index} output]</h6>
                                    <div id="terminal-${status.index}" class="white-text">output will be appended here</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!-- Java script -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <!--Import materialize.js(css)-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
    <!-- import external js files -->
    <script src="${pageContext.request.contextPath }/assets/ace/ace.js" type="text/javascript"  charset="utf-8"></script>
    <script src="${pageContext.request.contextPath }/assets/js/jquery.simple.timer.js" type="text/javascript"  charset="utf-8"></script>
    <script src="${pageContext.request.contextPath }/assets/js/practice_page.js" type="text/javascript"  charset="utf-8"></script>

    <!-- global 변수 선언-->
    <script>
        //general
        var applicant_id = ${authApplicant.id};
        var number_of_problems = ${problemListOfList.size()};

        //about current problem
        var problem_id; //current problem_id
        var current_k; //전역변수(가변) 현재 보고있는 problemInfo의 status.index
        var problem_json_list = []; //전역변수(가변) 풀고있는 problem list(JSON)

        <c:set var="newline" value="<%= \"\n\" %>" />

        //skeleton code의 '\\n'을 교체, problem list를 jason 형태로 저장
        <c:forEach items="${problemListOfList}" var="problemList" varStatus="status">
            <c:forEach items="${problemList}" var="problemVo">
                var skeleton_code = '${fn:replace(problemVo.skeletonCode, newline, '\\n')}';
                problem_json_list.push({
                                           "kth_problem_info":${status.index +1},
                                           "problem_id":${problemVo.id},
                                           "skeleton_code": skeleton_code,
                                           "language_id":${problemVo.languageId}
                                       })
            </c:forEach>
        </c:forEach>
    </script>

    <!-- document ready되면 초기화 -->
    <script>
        //TODO: 모든 페이지가 로드된 후에 help() 호출
        $(function () {
            $('select').material_select(); //select에 materialize css입히기 위해서

            select(1);

            help();
            $('.timer').startTimer({
                                       onComplete: function () {
                                           alert('go testing?');
                                           location.href = "/test";
                                       }
                                   });
            //ctrl + S
            $(document).bind('keydown', function (e) {
                if (e.ctrlKey && (e.which == 83)) {
                    e.preventDefault();
                    save_code(current_k);
                    return false;
                }
            });
            //ctrl + R
            $(document).bind('keydown', function (e) {
                if (e.ctrlKey && (e.which == 82)) {
                    e.preventDefault();
                    run_code(current_k);
                    return false;
                }
            });
        })
    </script>

</body>
</html>