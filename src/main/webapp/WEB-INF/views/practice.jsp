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
    <script src="${pageContext.request.contextPath }/assets/jquery.simple.timer.js"></script>
    <script>
        //전역변수: 풀어야할 문제의 수
        var number_of_problems = ${problemListOfList.size() };


        //k번째 문제로 셋팅. 1부터 시작한다
        var select = function (k) {
            //모든 selectable의 자식들을 hide하고 k번째 문제에 해당되는 것만 show
            for (var i = 1; i <= number_of_problems; i++) {
                $('.selectable > :nth-child(' + i + ')').hide();
            }
            $('.selectable > :nth-child(' + k + ')').show();
            //에디터는 따로 함수를 실행해줘야 렌더링된다
            select_editor(k);
            //전역변수 k값 갱신
            current_k = k;
        };

        var select_editor = function(k, language_id) {
            var problem;
            var skeleton_code;
            //selcet(k)로 접근: 문제1 문제2 버튼을 눌럿을 떄 language_id를 안주므로 첫번째 problem 값으로 셋팅
            if (language_id === undefined) {
                problem = $($("div[data-kth_problem_info=" + k + "]").get(0));
                language_id = problem.data("language_id").toString();
                skeleton_code = problem.data("skeleton_code");
            }
            //language option을 선택하여 problem을 바꿀 떄
            else{
                problem = $("div[data-kth_problem_info=" + k + "][data-language_id=" + language_id + "]");
                if(problem.size()==0){
                    skeleton_code = "제공된 problemVo가 없다. 따라서 문법에 맞게 코딩해도 저장안되며 컴파일도 안됨";
                }
                else{
                    skeleton_code = problem.data("skeleton_code");
                }
            }
            // 에디터 세팅
            var editor = ace.edit("editor-" + k);
            var mode;
            editor.setValue(skeleton_code);
            editor.setTheme("ace/theme/monokai");
            editor.$blockScrolling = Infinity;
            switch (language_id) {
                case '1':
                    mode = "ace/mode/c_cpp";
                    break;
                case '2':
                    mode = "ace/mode/java";
                    editor.getSession().setMode("ace/mode/java");
                    break;
                case '3':
                    mode = "ace/mode/python";
                    editor.getSession().setMode("ace/mode/python");
                    break;
                default:
                    mode = "ace/mode/text";
                    break;
            }
            editor.getSession().setMode(mode);

            //option select값 변화
            $('select[name=language]').get(0).value = language_id;

            //전역변수 k값 갱신
            problem_id = problem.data("problem_id");
        };

        var save_code = function (k) {
            alert(k + '번 문제의 소스코드가 저장되었습니다 \n 는 훼이크고 사실 저장 안됨. practice 니까' )
        };

        var run_code = function(){
            alert(problem_id + '번 문제의 소스코드 컴파일후 실해인데 practice버전이라 구현안함');
        };

        //도움말 함수 호출. spotlight해준다
        var help = function () {
            alert('이것은 튜토리얼 입니다');
            alert('run버튼을 누르면 컴파일 및 실행이됩니다');
            alert('시험보러가기를 누르면 시험을 볼 수 있다');
            alert('친절한 설명');
        };

        //제출 알림만 뜨고 페이지 이동은 없다
        var final_submit = function () {
            alert('이 버튼을 누르면 최종 제출이 되며 테스트가 끝납니다');
        };

        //테스트 페이지로 이동
        var goTest = function () {
            var a = confirm('시험보러간다?');
            if (a) {
                location.href = "/test";
            }
            else {
                alert('그래좀더 고민좀 해봐ㅣ라');
            }
        };

        //TODO: 모든 페이지가 로드된 후에 help() 호출
        $(function () {
            select(1);
            help();
            $('.timer').startTimer({
                                       onComplete: function () {
                                           alert('시험이 끝났다. 지금 저장본으로 제출한다. 이제 시험보러 간다');
                                           location.href = "/test";
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
                    <button onclick="select(${status.index})">문제${status.index}</button>
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

        <div id="workboard"
             style="background-color: #0C090A; width:80%; height:70%; color:white; float:right">
            <h2>workboard</h2>
            <div>
                <div class="btn-workboard timer" data-seconds-left="${totalTime}">남은 시간:</div>
                <div class="btn-workboard">
                    <label>언어 선택</label>
                    <select name="language" onchange="select_editor(current_k, this.value);">
                        <option value="1">C</option>
                        <option value="2" >JAVA</option>
                        <option value="3">PYTHON</option>
                    </select>
                </div>
                <button style="float:right" onclick="goTest()">시험보러 가기</button>
            </div>


            <div class="selectable" style="width: 100%; height:85%">
                <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                    <div id="editor-${status.index}" style="width:100%; height:100%;"></div>
                </c:forEach>
            </div>

            <!-- javascript list로 데이터를 담고 있는게 더 좋아보인다. escape 문제..-->
            <div style="display:none">
                <c:forEach items="${problemListOfList}" var="problemList" varStatus="status">
                    <c:forEach items="${problemList}" var="problemVo">
                        <div data-kth_problem_info="${status.index +1}" data-problem_id="${problemVo.id}" data-skeleton_code='${problemVo.skeletonCode}' data-language_id="${problemVo.languageId}"></div>
                    </c:forEach>
                </c:forEach>
            </div>

            <div>
                <button onclick="help()" class="btn-workboard">도움말(튜토리얼 다시보기)</button>
                <div class="btn-workboard">
                    <form class="selectable">
                        <c:forEach items="${testcaseListOfList}" var="testcaseList">
                            <select name="test_cases">
                                <option selected disabled>test case를 선택하세요</option>
                                <c:forEach items="${testcaseList}" var="testcase">
                                    <option value="${testcase.id}">${testcase.input}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>
                    </form>
                </div>
                <div class="selectable btn-workboard">
                    <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                        <button onclick="save_code(${status.index})">${status.index}번 문제
                            저장
                        </button>
                    </c:forEach>
                </div>
                <div class="selectable btn-workboard">
                    <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                        <button onclick="run_code(${status.index})">${status.index}번 문제 compile & run</button>
                    </c:forEach>
                </div>
                <button style="float:right" onclick="final_submit()" class="btn-workboard">최종 제출
                </button>
            </div>
        </div>

        <div id="terminal" class="selectable"
             style="background-color:violet; height:30%; width:80%; float:right;">
            <c:forEach begin="1" end="${problemInfoList.size()}" varStatus="status">
                <div>
                    <h2>${status.index}번째 문제의 terminal</h2>
                    <div id="terminal-${status.index}">output will be appended here</div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>