<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>practice main page</title>
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
    <script src="${pageContext.request.contextPath }/assets/ace/ace.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath }/assets/jquery.simple.timer.js"></script>
    <script>
        //전역변수: 풀어야할 문제의 수
        var number_of_problems = ${problemListOfList.size() };
        //전역변수:  지원자의 id
        var applicant_id = ${authApplicant.id};
        //전역변수(가변)
        var problem_id ;
        //전역변수(가변) 현재 보고있는 problemInfo의 status.index
        var current_k;


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
            editor.$blockScrolling = Infinity;
            var mode;
            editor.setValue(skeleton_code);
            editor.setTheme("ace/theme/monokai");
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

        //k번째 에디터 상의 소스코드 저장
        var save_code = function (k) {
            var editor = ace.edit("editor-" + k);
            var code = editor.getValue();
            //ajax는 비동기식이라 success시 source_code ID를 받아서 save_code()로 리턴하는거 안됨
            //ajax POST
            $.ajax({
                       url: '${pageContext.request.contextPath }/test/save',
                       type: "post",
                       //리턴값은 'success' or 'fail'
                       //dataType: "json",
                       data: {"code": code, "problem_id": problem_id, "applicant_id": applicant_id},
                       success: function (response) {
                           if(response=='fail'){
                               alert('저장 실패');
                           }
                           else if(response=='success'){
                               alert('저장되었습니다');
                           }
                       },
                       error: function (xhr, status, error) {
                           console.error(status + ":" + error);
                       }
                   }
            );
        };

        var run_code = function(k){
            //일단 저장 후 돌림
            //TODO: 저장 후 돌려야 하는데 꼬이는거같다
            //save_code(k);
            test_case_id = $('select[name=test_cases]').get(current_k-1).value;

            //ajax POST
            $.ajax({
                       url: '${pageContext.request.contextPath }/test/run',
                       type: "post",
                       contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                       //dataType: "json",
                       //TODO: auth 권한?
                       data: {"problem_id": problem_id, "applicant_id": applicant_id, "test_case_id": test_case_id},
                       success: function (response) {
                           $("#terminal-"+k).text(response);
                       },
                       error: function (xhr, status, error) {
                           console.error(status + ":" + error);
                       }}
            );
        };


        //최종 제출
        var final_submit = function () {
            var a = confirm('최종 제출하시겠습니까?');
            if (a) {
                location.href = "/result";
            }
            else {
                alert('그래좀더 고민좀 해봐ㅣ라');
            }
        };


        //도움말 함수 호출. spotlight해준다
        var help = function () {
            alert('이것은 test의 도움말 입니다');
            alert('run버튼을 누르면 컴파일 및 실행이됩니다');
            alert('주어진 시간은 ${totalTime}분이며 시간이 지나면 마지막 저장본으로 자동 제출됩니다');
            alert('요이땅');
        };


        //모든 페이지가 로드 되면 창띄워서 물어보고 확인 누르면 타이머가 돌아가며 시작
        //첫번째 문제로 기본 스타트
        $(function () {
            alert('확인을 누르면 시험을 시작합니다');
            select(1);
            $('.timer').startTimer({
                                       onComplete: function () {
                                           alert('시험이 끝났다. 지금 저장본으로 제출한다');
                                           location.href = "/result";
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
                                <option value="0" selected disabled>test case를 선택하세요</option>
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
                <button style="float:right" onclick="final_submit()">최종 제출</button>
            </div>
        </div>

        <div id="terminal" class="selectable" style="background-color:violet; height:30%; width:80%; float:right;">
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