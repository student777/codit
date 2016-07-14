<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result Page</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .selectable > * {
            display: none;
        }
    </style>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        var render_result = function(response){
            // do something
            // rendering, draw a graph ...
        };
        var get_data = function(){
            $.ajax({
                       url: '${pageContext.request.contextPath }/result/get_data',
                       type: "post",
                       contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                       //dataType: "json",
                       success: function (response) {
                           render_result(response);
                       },
                       error: function (xhr, status, error) {
                           console.error(status + ":" + error);
                       }
                   });
        };
        $(function () {
            get_data();
        })
    </script>
</head>
<body>
${applicantVo.name } 님의 성적
조회주입니다...

<table border="1px">
    <tr>
        <td>문제번호</td>
        <td>소요시간</td>
        <td>메모리사용량</td>
        <td>running time</td>
        <td>점수</td>
    </tr>
    <tr>
        <td>문제 1</td>
        <td>321</td>
        <td>234</td>
        <td>234</td>
        <td>3/5</td>
    </tr>
    <tr>
        <td>문제 2</td>
        <td>123</td>
        <td>234</td>
        <td>234</td>
        <td>5/5</td>
    </tr>
    <tr>
        <td>문제 3</td>
        <td>234</td>
        <td>567</td>
        <td>345</td>
        <td>4/5</td>
    </tr>
    <tr>
        <td>문제 4</td>
        <td>123</td>
        <td>345</td>
        <td>234</td>
        <td>5/5</td>
    </tr>
</table>

<br>
<br>
<br>
<br>

<table>
    <thead>문제 1</thead>
    <tr>
        <td>test_case 번호</td>
        <td>실행 시간</td>
        <td>메모리사용량</td>
        <td>running time</td>
        <td>정답여부</td>
    </tr>
    <tr>
        <td>test_case 1</td>
        <td>321</td>
        <td>234</td>
        <td>234</td>
        <td>o</td>
    </tr>
    <tr>
        <td>test_case 2</td>
        <td>123</td>
        <td>234</td>
        <td>234</td>
        <td>o</td>
    </tr>
    <tr>
        <td>test_case 3</td>
        <td>234</td>
        <td>567</td>
        <td>345</td>
        <td>o</td>
    </tr>
    <tr>
        <td>test_case 4</td>
        <td>123</td>
        <td>345</td>
        <td>234</td>
        <td>x</td>
    </tr>
</table>
</body>
</html>
