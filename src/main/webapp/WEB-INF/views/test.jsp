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
        //전역변수: 현재 보고있는 problem의 status.index
        var current_k = 1;

        //도움말 함수 호출. spotlight해준다
        var help = function () {
            alert('이것은 test의 도움말 입니다');
            alert('run버튼을 누르면 컴파일 및 실행이됩니다');
            alert('주어진 시간은 ${totalTime}분이며 시간이 지나면 마지막 저장본으로 자동 제출됩니다');
            alert('요이땅');
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

        //k번째 문제로 셋팅. 1부터 시작한다
        var select = function (k) {
            //모든 selectable의 자식들을 hide하고 k번째 문제에 해당되는 것만 show
            for (var i = 1; i <= number_of_problems; i++) {
                $('.selectable > :nth-child(' + i + ')').hide();
            }
            $('.selectable > :nth-child(' + k + ')').show();

            //에디터는 따로 함수를 실행해줘야 렌더링된다
            select_editor(k);
            $('select[name=language] option:eq(0)').attr('selected', 'selected');
            //현재 k값 갱신
            current_k = k;
        };

        var select_editor = function(k, language_id){
            if(language_id===undefined){
                //default 언어는 C
                language_id = 1;
            }
            var problem = $("div[data-kth_problem_info="+k+"][data-language_id="+language_id+"]");
            var editor = ace.edit("editor-" + k);
            editor.setValue(problem.data("skeleton_code"));
            editor.setTheme("ace/theme/monokai");
            switch(language_id) {
                case 1:
                    editor.getSession().setMode("ace/mode/c_cpp");
                    break;
                case 2:
                    editor.getSession().setMode("ace/mode/java");
                    break;
                case 3:
                    editor.getSession().setMode("ace/mode/python");
                    break;
                default:
                    editor.getSession().setMode("ace/mode/text");
                    break;
            }

        }

        //k번째 에디터 상의 소스코드 저장
        var save_code = function (k) {
            var editor = ace.edit("editor-" + k);
            var editor_jquery = $("#editor-" + k);
            var code = editor.getValue();
            var applicant_id = ${authApplicant.id};
            var problem_id = editor_jquery.data("problem_id");

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
            //TODO: 해야 하는데 꼬이는거같다
            //save_code(k);

            var editor_jquery = $("#editor-" + k);
            var applicant_id = ${authApplicant.id};
            var problem_id = editor_jquery.data("problem_id");

            //ajax POST
            $.ajax({
                   url: '${pageContext.request.contextPath }/test/run',
                   type: "post",
                   contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                   //dataType: "json",
                   //TODO: auth 권한?
                   data: {"problem_id": problem_id, "applicant_id": applicant_id, },
                   success: function (response) {
                       $("#terminal-"+k).text(response);
                   },
                   error: function (xhr, status, error) {
                       console.error(status + ":" + error);
                   }}
            );
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
                    <select name="language" onchange="select_editor(current_k, this.value)">
                        <option value="1">C</option>
                        <option value="2" >JAVA</option>
                        <option value="3">PYTHON</option>
                    </select>
                </div>
            </div>

            <div class="selectable" style="width: 100%; height:85%">
                <c:forEach items="${problemInfoList}" var="problemVo" varStatus="status">
                    <div id="editor-${status.index + 1}" data-problem_id="" style="width:100%; height:100%;"></div>
                </c:forEach>
            </div>


            <!-- javascript list로 데이터를 담고 있는게 더 좋아보인다. escape 문제..-->
            <div style="display:none">
                <c:forEach items="${problemListOfList}" var="problemList" varStatus="status">
                    <c:forEach items="${problemList}" var="problemVo">
                        <div data-kth_problem_info="${status.index +1}" data-problem_id="${problemVo.id}" data-skeleton_code='${problemVo.skeletonCode}' data-language_id="${problemVo.langguageId}"></div>
                    </c:forEach>
                </c:forEach>
            </div>

            <div>
                <button onclick="help()" class="btn-workboard">도움말(튜토리얼 다시보기)</button>
                <div class="btn-workboard">
                    <form class="selectable">
                        <c:forEach items="${testcaseListOfList}" var="testcaseList">
                            <select name="cars">
                                <option selected disabled>test case를 선택하세요</option>
                                <c:forEach items="${testcaseList}" var="testcase">
                                    <option value="${testcase.id}">${testcase.input}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>
                    </form>
                </div>
                <div class="selectable btn-workboard">
                    <c:forEach items="${problemInfoList}" var="problemVo" varStatus="status">
                        <button onclick="save_code(${status.index + 1})">${status.index + 1}번 문제
                            저장
                        </button>
                    </c:forEach>
                </div>
                <div class="selectable btn-workboard">
                    <c:forEach items="${problemInfoList}" var="problemVo" varStatus="status">
                        <button onclick="run_code(${status.index + 1})">${status.index + 1}번 문제 compile & run</button>
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