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
        /*
        $(function() {
            alert('이것은 튜토리얼 입니다');
            alert('ㅇㅇㅇ');
            alert('run버튼을 누르면 컴파일 및 실행이됩니다');
            alert('정말 친절한 설명');
            alert('셤잘봐라');
            var a = confirm('시험 보러 갈테냐?');
            if(a){
                alert('test 페이지로 이동한다')
                location.href='/test';
            }
            else{
                alert('여기서 좀더 개기다가 간다')
            }
        })
        */
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
        <button><a href="/test">시험보러 가기</a></button>
        <button><a href="/test">시험보러 가기</a></button>
        <button><a href="/test">시험보러 가기</a></button>
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


            <div id="editor" style="width: 100%; height:85%">#include &lt;stdio.h&gt;
int main(){
    printf("hello world");
    return 0;
}
            </div>

            <div>
                <button class="btn-workboard">^^콘솔창 확장</button>
                <button class="btn-workboard">테스트</button>
                <button class="btn-workboard">제출</button>
                <button class="btn-workboard">도움말(튜토리얼 다시보기)</button>
                <div class="btn-workboard">
                    <form>
                        <select name="cars">
                            <option value="volvo">1 3 2 1</option>
                            <option value="saab"> 31 3 1 31</option>
                            <option value="fiat">1 4 42 1</option>
                        </select>
                    </form>
                </div>
            </div>
        </div>

        <div id="terminal" style="background-color:violet; height:30%; width:80%; float:right;">
            <h2>terminal</h2>
            <div>
                output here...<br> <br>
                || visible in terminal ||   visible in file   || existing <br>
                Syntax  ||  StdOut  |  StdErr  ||  StdOut  |  StdErr  ||   file    <br>
                =========++==========+==========++==========+==========++=========== <br>
                >     ||    no    |   yes    ||   yes    |    no    || overwrite <br>
                >>    ||    no    |   yes    ||   yes    |    no    ||  append <br>
                ||          |          ||          |          || <br>
                2>     ||   yes    |    no    ||    no    |   yes    || over write <br>
                2>>    ||   yes    |    no    ||    no    |   yes    ||  append <br>
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
</script>
</html>