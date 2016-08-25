<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>practice main pag</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/0630_favicon_beige.ico">
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .btn-workboard {
            display: inline-block;
        }

        .selectable > * {
            display: none;
        }
    </style>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/ace/ace.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath }/assets/js/jquery.simple.timer.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/practice_page.js"></script>
    <script>
        //전역변수에 대한 정보
        var number_of_problems = ${problemListOfList.size() };
        var applicant_id = ${authApplicant.id}; //시험 보는 사람의 id값
        var problem_id; //현재 풀고 있는 problem의 id값
        var current_k; //전역변수(가변) 현재 보고있는 problemInfo의 status.index
        var problem_json_list = []; //전역변수(가변) 풀고있는 problem list(JSON)
        <c:set var="newline" value="<%= \"\r\n\" %>" />
        <c:forEach items="${problemListOfList}" var="problemList" varStatus="status">
        <c:forEach items="${problemList}" var="problemVo">
        var skeleton_code = '${fn:replace(problemVo.skeletonCode, newline, '\\n')}';
        problem_json_list.push({
            "kth_problem_info":${status.index +1},
            "problem_id":${problemVo.id},
            "skeleton_code": skeleton_code,
            "language_id":${problemVo.languageId},
        })
        </c:forEach>
        </c:forEach>
    </script>
    <script>
        $(function () {
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
</head>
<body>
<div id="IDE" style="height: 100vh">
    <div id="header" style="background-color:grey; height:5%;">
        <h1 style="float:left">codit</h1>
    </div>

    <div style="height:95%">
        <div id="navbar" style="background-color:skyblue; width:20%; height:100%; float:left">
            <h2>navigation bar</h2><br>
            <div>
                <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                    <button onclick="select(${status.index})">Task${status.index}</button>
                </c:forEach>
            </div>
            <div class="selectable">
                <c:forEach items="${problemInfoList}" var="problemInfoVo">
                    <div>
                        <h3>${problemInfoVo.name}</h3>
                        <p>${problemInfoVo.description}</p>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div id="workboard" style="background-color: #0C090A; width:80%; height:70%; color:white; float:right">
            <h2>workboard</h2>
            <div>
                <div class="btn-workboard timer" data-seconds-left="${totalTime}">time left:</div>
                <div class="btn-workboard">
                    <label>choose language</label>
                    <select name="language" onchange="select_editor(current_k, this.value);">
                        <option value="1">C</option>
                        <option value="2">JAVA</option>
                        <option value="3">PYTHON</option>
                    </select>
                </div>
                <button style="float:right" onclick="goTest()">GO TEST</button>
            </div>


            <div id="editor" style="width:100%; height:85%;"></div>

            <div>
                <button onclick="help()" class="btn-workboard">help(review tutorial)</button>
                <div class="btn-workboard">
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
                <div class="selectable btn-workboard">
                    <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                        <button onclick="save_code(${status.index})">SAVE(ctrl+S)</button>
                    </c:forEach>
                </div>
                <div class="selectable btn-workboard">
                    <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                        <button onclick="run_code(${status.index})">RUN(ctrl+R)
                        </button>
                    </c:forEach>
                </div>
                <button style="float:right" onclick="final_submit()" class="btn-workboard">final submit
                </button>
            </div>
        </div>

        <div id="terminal" class="selectable"
             style="background-color:violet; height:30%; width:80%; float:right;">
            <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                <div>
                    <h2>task ${status.index} output</h2>
                    <div id="terminal-${status.index}">output will be appended here</div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>