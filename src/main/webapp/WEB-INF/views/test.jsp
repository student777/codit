<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>test</title>
    <style >
        * { margin: 0;  padding: 0; }
    </style>
</head>
<body>
<div id="IDE" style="height: 100vh">
    <div id="header" style="background-color:grey; height:5%;" >
        <h1>codit</h1>
    </div>
    <div style="height:95%">
        <div id="navbar" style="background-color:skyblue; width:300px; height:100%; float:left">
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

        <div id="editor" style="background-color: #0C090A; height:70%; color:white; ">
            <h2>editor</h2>
            <button style="float:left">compile and run</button>
            <div>남은 시간 16분 30초</div>

            <div style="float:right">
                <form>
                    <select name="cars">
                        <option value="volvo">C</option>
                        <option value="saab">JAVA</option>
                        <option value="fiat">C++</option>
                    </select>
                </form>
            </div>
            <div style="width: 100%; height: 90%">
                <form>
                    <textarea rows="40" cols="200">
some code here...

1]public class HelloWorld{
2]   public static void main(String[] args) {
4]            System.out.println("Hello World!");
5]        }
6]    }
                    </textarea>
                </form>
            </div>
            <div style="width: 100%; height: 10%">
                <button>^^콘솔창 확장</button>
                <button>테스트</button>
                <button>제출</button>

                <button>도움말</button>

                <button><a href="/result">최종 제출</a></button>
                <div style="float:right">
                    <form>
                        <select name="cars">
                            <option value="volvo">1 3 2 1</option>
                            <option value="saab"> 31 3 1 31</option>
                            <option value="fiat">1 4 42 1</option>
                            <option value="audi">123123 123123 123 123 123 123 </option>
                        </select>
                        <br><br>
                    </form>
                </div>
            </div>
        </div>

        <div id="terminal" style="background-color:violet; height:30%;" ">
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
</html>