<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>practice main pag</title>
    <style >
        * { margin: 0;  padding: 0; }
    </style>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        var help = function() {
            alert('이것은 튜토리얼 입니다');
            alert('run버튼을 누르면 컴파일 및 실행이됩니다');
            alert('정말 친절한 설명');
            alert('셤잘봐라');
            var a = confirm('시험 보러 갈테냐?');
            if(a){
                alert('test 페이지로 이동한다')
                location.href='/test';
            }
            else{
                alert('여기서 좀더 개기다가 간다');
            }
        }

        var submit = function(){
            alert('이 버튼을 누르면 최종 제출이 되며 테스트가 끝납니다');
            var a = confirm('시험 보러 갈테냐?');
            if(a){
                alert('test 페이지로 이동한다')
                location.href='/test';
            }
            else{
                alert('여기서 좀더 개기다가 간다');
            }
        }

        var final_submit = function () {
            var a = confirm('최종 제출하시겠습니까?');
            if(a){
                location.href="/result";
            }
            else{
                alert('그래좀더 고민좀 해봐ㅣ라');
            }
        }
    </script>
    <style>
        .btn-workboard {
            display: inline-block;
        }
    </style>
</head>
<body>
<div id="IDE" style="height: 100vh">
    <div id="header" style="background-color:grey; height:5%;" >
        <h1 style="float:left">codit</h1>
    </div>
    <div style="height:95%">
        <div id="navbar" style="background-color:skyblue; width:20%; height:100%; float:left">
            <h2>navigation bar</h2><br>
            <div>
                <button>문제1</button>
                <button>문제2</button>
                <button>문제3</button>
            </div>
            <div>
                <c:forEach items="${problemInfoVoList}" var="problemInfoVo">
                    <h3>${problemInfoVo.name}</h3>
                    <p>${problemInfoVo.description}</p>
                </c:forEach>
                문제에 대한 설명<br>
                평면 상에 N개의 점이 찍혀있고, 그 점을 집합 P라고 하자. 집합 P의 Vector Matching은 벡터의 집합인데, 모든 벡터는 집합 P 중 한 점에서 시작해서, 또 다른 점에서 끝나는 벡터들의 집합이다. 또, P속의 모든 점은 모두 단 한번만 쓰여야 한다 V에 있는 벡터의 개수는 P에 있는 점의 절반이다.평면 상의 점이 주어졌을 때, 집합 P의 Vector Matching에 있는 벡터들의 합의 길이의 최소값을 출력하는 프로그램을 작성하시오.
            </div>
        </div>

        <div id="workboard" style="background-color: #0C090A; width:80%; height:70%; color:white; float:right">
            <h2>editor-old</h2>
            <div>
                <button class="btn-workboard">compile and run</button>
                <div class="btn-workboard">남은 시간 16분 30초</div>
                <div class="btn-workboard">
                    <form>
                        <select name="cars">
                            <option value="volvo">C</option>
                            <option value="saab">JAVA</option>
                            <option value="fiat">C++</option>
                        </select>
                    </form>
                </div>
            </div>


            <div id="editor" style="width: 100%; height:85%">
                <c:forEach items="${problemList}" var="problemVo">
                    ${problemVo.id}번 문제:
                    ${problemVo.skeleton_code}
                </c:forEach>
                <br>

            </div>

            <div>
                <button class="btn-workboard">^^콘솔창 확장</button>
                <button class="btn-workboard">테스트</button>
                <button onclick="final_submit()">최종 제출</button>
                <button onclick="help()" class="btn-workboard">도움말(튜토리얼 다시보기)</button>
                <div class="btn-workboard">
                    <form>
                        <c:forEach items="${testcaseListList}" var="testcaseList">
                            <select name="cars">
                                <c:forEach items="${testcaseList}" var="testcase">
                                    <option value="${testcase.id}">${testcase.problem_info_id}번째 문제의 테스트케이스: ${testcase.input}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>

                    </form>
                </div>
            </div>
        </div>

        <div id="terminal" style="background-color:violet; height:30%; width:80%; float:right;">
            <h2>terminal</h2>
            <div>
                output here...<br> <br>
            </div>
        </div>
    </div>
</div>
</div>

</body>


<script src="/assets/ace/ace.js" type="text/javascript" charset="utf-8"></script>
<script>
    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/monokai");
    editor.getSession().setMode("ace/mode/c_cpp");
    /*
     페이지가 다 로드된 후에 help함수를 호출하고싶다
     */
</script>
</html>