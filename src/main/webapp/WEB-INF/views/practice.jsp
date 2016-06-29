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
        .btn-workboard {
            display: inline-block;
        }
        .selectable > *{
            display: none;
        }
    </style>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/assets/ace/ace.js" type="text/javascript" charset="utf-8"></script>
    <script>
        //전역변수: 풀어야할 문제의 수
        var number_of_problems = ${problemInfoVoList.size() };

        //도움말 함수 호출. spotlight해준다
        var help = function() {
            alert('이것은 튜토리얼 입니다');
            alert('run버튼을 누르면 컴파일 및 실행이됩니다');
            alert('정말 친절한 설명');
            alert('셤잘봐라');
        }

        //제출 알림만 뜨고 페이지 이동은 없다
        var submit = function(){
            alert('이 버튼을 누르면 최종 제출이 되며 테스트가 끝납니다');
        }

        //테스트 페이지로 이동
        var goTest = function(){
            var a = confirm('시험보러간다?');
            if(a){
                location.href="/test";
            }
            else{
                alert('그래좀더 고민좀 해봐ㅣ라');
            }
        }


        //k번째 문제로 셋팅
        var select = function (k) {
            //모든 selectable의 자식들을 hide하고 k번째 문제에 해당되는 것만 show
            for(var i=1; i <= number_of_problems; i++){
                $('.selectable > :nth-child(' + i + ')').hide() ;
            }
            $('.selectable > :nth-child(' + k + ')' ).show();

            //에디터는 따로 함수를 실행해줘야 렌더링된다
            var editor = ace.edit("editor-" + k);
            editor.setTheme("ace/theme/monokai");
            editor.getSession().setMode("ace/mode/c_cpp");
        }

        //모든 페이지가 로드 되면 창띄워서 물어보고 확인 누르면 튜토리얼 함수 호출
        $(function () {
            select(1);
            help();
        })
    </script>
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
                <c:forEach begin="1" end="${problemInfoVoList.size()}" varStatus="status">
                    <button onclick="select(${status.index})">문제${status.index}</button>
                </c:forEach>
            </div>
            <div class="selectable">
                <c:forEach items="${problemInfoVoList}" var="problemInfoVo">
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
                <button style="float:right" onclick="goTest()">시험보러 가기</button>
            </div>


            <div class="selectable"  style="width: 100%; height:85%">
                <c:forEach items="${problemList}" var="problemVo" varStatus="status">
                    <div id="editor-${status.index + 1}" style="width:100%; height:100%;">${problemVo.skeleton_code}</div>
                </c:forEach>
            </div>

            <div>
                <button class="btn-workboard">^^콘솔창 확장</button>
                <button onclick="help()" class="btn-workboard">도움말(튜토리얼 다시보기)</button>
                <div class="btn-workboard">
                    <form class="selectable">
                        <c:forEach items="${testcaseListList}" var="testcaseList">
                            <select name="cars">
                                <option selected disabled>test case를 선택하세요</option>
                                <c:forEach items="${testcaseList}" var="testcase">
                                    <option value="${testcase.id}">${testcase.input}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>
                    </form>
                </div>
                <button class="btn-workboard">테스트</button>
                <button style="float:right" onclick="submit()" class="btn-workboard">최종 제출</button>
            </div>
        </div>

        <div id="terminal" class="selectable" style="background-color:violet; height:30%; width:80%; float:right;">
            <c:forEach begin="1" end="${problemInfoVoList.size()}" varStatus="status">
                <div>
                    <h2>${status.index}번째 문제의 terminal</h2>
                    <div> output will be appended here...<br> <br></div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</div>

</body>
</html>